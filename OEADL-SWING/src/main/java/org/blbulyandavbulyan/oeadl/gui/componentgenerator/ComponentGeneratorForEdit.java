package org.blbulyandavbulyan.oeadl.gui.componentgenerator;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;
import org.blbulyandavbulyan.oeadl.gui.dialogs.dialogvaluegetter.DialogValueGetter;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDialog;
import org.blbulyandavbulyan.oeadl.gui.dialogs.simpletypeeditor.SimpleTypeEditorDialog;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectEditorDialog;
import org.blbulyandavbulyan.oeadl.gui.forjtextfield.JTextFieldLimit;
import org.blbulyandavbulyan.oeadl.interfaces.GenerateObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GetResourceBundleByClass;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

public class ComponentGeneratorForEdit extends ComponentGenerator {
    public ComponentGeneratorForEdit(){
        initTypeToObjectMapper();

    }
    private void initTypeToObjectMapper(){
        typeToObjectMapperMap.put(String.class, ((String objectDisplayableName, Object obj, ObjectDialog parent, ResourceBundle uiResourceBundle, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass) -> {
            JTextField jTextField = new JTextField(obj.toString());
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel(objectDisplayableName));
            jPanel.add(jTextField);
            return new ComponentAndValueGetter(jPanel, jTextField::getText);
        }));
        //данная обработка предназначена для отображения JComboBox для enum внутри классов
        typeToObjectMapperMap.put(Enum.class, ((String objectDisplayableName, Object obj, ObjectDialog parent, ResourceBundle uiResourceBundle, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass) -> {
            Enum<?> passedEnumerationValue = (Enum<?>) obj;
            Class<? extends Enum> enumClass = passedEnumerationValue.getClass();
            JComboBox<String> enumerationCombobox = new JComboBox<>();
            HashMap<String, Enum> converter = new HashMap<>();
            for (Object enumerationValue : enumClass.getEnumConstants()) {
                enumerationCombobox.addItem(enumerationValue.toString());
                converter.put(enumerationValue.toString(), (Enum) enumerationValue);
            }
            enumerationCombobox.setEditable(false);
            enumerationCombobox.setSelectedItem(passedEnumerationValue.toString());
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel(objectDisplayableName));
            jPanel.add(enumerationCombobox);
            return new ComponentAndValueGetter(jPanel, ()->converter.get(enumerationCombobox.getItemAt(enumerationCombobox.getSelectedIndex())));
        }));
        //обработка чисел (всех наследников класса Number)
        typeToObjectMapperMap.put(Number.class, ((String objectDisplayableName, Object obj, ObjectDialog parent, ResourceBundle uiResourceBundle, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass) -> {
            NumberFormatter numberFormatter = new NumberFormatter();
            numberFormatter.setValueClass(obj.getClass());
            numberFormatter.setAllowsInvalid(false);
            numberFormatter.setCommitsOnValidEdit(true);
            JFormattedTextField jFormattedTextField = new JFormattedTextField(numberFormatter);
            jFormattedTextField.setValue(obj);
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel(objectDisplayableName));
            jPanel.add(jFormattedTextField);
            return new ComponentAndValueGetter(jPanel, jFormattedTextField::getValue);
        }));
        {
            //обработка для char и класса обёртки Character
            ObjectToComponentAndValueGetterConverter fieldComponentForCharacterGenerator = ((String objectDisplayableName, Object obj, ObjectDialog parent, ResourceBundle uiResourceBundle, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass) -> {
                JTextField jTextField = new JTextField();
                jTextField.setDocument(new JTextFieldLimit(1));
                jTextField.setText(obj.toString());
                JPanel jPanel = new JPanel();
                jPanel.add(new JLabel(objectDisplayableName));
                jPanel.add(jTextField);
                return new ComponentAndValueGetter(jPanel, ()->jTextField.getText().charAt(0));
            });
            typeToObjectMapperMap.put(Character.class, fieldComponentForCharacterGenerator);
            typeToObjectMapperMap.put(char.class, fieldComponentForCharacterGenerator);
        }
        {
            //обработка для boolean и класса обёртки Boolean
            ObjectToComponentAndValueGetterConverter fieldComponentForBoolean = (String objectDisplayableName, Object obj, ObjectDialog parent, ResourceBundle uiResourceBundle, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass)->{
                Boolean b = (Boolean) obj;
                JCheckBox jCheckBox = new JCheckBox(objectDisplayableName, b);
                return new ComponentAndValueGetter(jCheckBox, jCheckBox::isSelected);
            };
            typeToObjectMapperMap.put(Boolean.class, fieldComponentForBoolean);
            typeToObjectMapperMap.put(boolean.class, fieldComponentForBoolean);
        }
        // TODO: 03.04.2023 добавить здесь обработку для коллекций, добавить возможность обработки коллекций из смешанных типов
        typeToObjectMapperMap.put(Collection.class, ((String objectDisplayableName, Object obj, ObjectDialog parent, ResourceBundle uiResourceBundle, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass) -> {
            Map<Object, DialogValueGetter> objectToDialogValueGetter = new HashMap<>();
            Collection<Object> elements = (Collection<Object>) obj;
            JList<Object> objectJList = new JList<>(elements.toArray());
            int counter = 1;
            for (Object element : elements){
                DialogValueGetter dialogValueGetter = null;
                if(element.getClass().isAnnotationPresent(OEADLProcessingClass.class)){
                    dialogValueGetter = generateObjectDialog.generateObjectDialog(parent, element);
                }
                else if(typeToObjectMapperMap.containsKey(element.getClass())){
                    dialogValueGetter = new SimpleTypeEditorDialog(
                            parent,
                            typeToObjectMapperMap.get(element.getClass()).convertToComponentAndValueGetter(String.format("Элемент %d", counter), element, parent, uiResourceBundle, generateObjectDialog, getResourceBundleByClass),
                            uiResourceBundle
                    );
                }
                objectToDialogValueGetter.put(element, dialogValueGetter);
                counter++;
            }
            JPanel componentEditorPanel = new JPanel();
            componentEditorPanel.setLayout(new BoxLayout(componentEditorPanel, BoxLayout.Y_AXIS));
            componentEditorPanel.add(objectJList);
            JPanel modifyCollectionButtonsPanel = new JPanel();
            // TODO: 08.04.2023 Добавить кнопки здесь для редактирования, удаления и добавления элемента в коллекции
            JButton addButton = new JButton(uiResourceBundle.getString("oeadl_swing.buttons.add"));
            JButton removeButton = new JButton(uiResourceBundle.getString("oeadl_swing.buttons.delete"));
            JButton editButton = new JButton(uiResourceBundle.getString("oeadl_swing.buttons.edit"));
            modifyCollectionButtonsPanel.add(addButton);
            modifyCollectionButtonsPanel.add(removeButton);
            modifyCollectionButtonsPanel.add(editButton);
            componentEditorPanel.add(modifyCollectionButtonsPanel);
            objectJList.setSelectionMode(SINGLE_SELECTION);
            addButton.addActionListener(l->{

            });
            removeButton.addActionListener(l->{
                Object selectedObject = objectJList.getSelectedValue();
                objectToDialogValueGetter.get(selectedObject).dispose();
                objectToDialogValueGetter.remove(selectedObject);
                objectJList.remove(objectJList.getSelectedIndex());
            });
            editButton.addActionListener(l->{

            });
            return new ComponentAndValueGetter(componentEditorPanel, ()->{
                try {
                    Collection abstractCollection = elements.getClass().getConstructor().newInstance();
                    for(int i = 0; i < objectJList.getModel().getSize(); i++){
                        Object objectFromJlist = objectJList.getModel().getElementAt(i);
                        abstractCollection.add(objectToDialogValueGetter.get(objectFromJlist).getValue());
                    }
                    return abstractCollection;
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            });
        }));
    }
}
