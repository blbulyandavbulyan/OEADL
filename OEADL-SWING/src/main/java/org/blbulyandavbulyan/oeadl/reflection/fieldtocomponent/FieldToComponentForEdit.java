package org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectEditorDialog;
import org.blbulyandavbulyan.oeadl.gui.forjtextfield.JTextFieldLimit;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import static org.blbulyandavbulyan.oeadl.reflection.ProcessingField.getLocalizedFieldNameOrGetFieldName;
public class FieldToComponentForEdit extends FieldToComponent {
    public FieldToComponentForEdit(){
        initTypeToObjectMapper();

    }
    private void initTypeToObjectMapper(){
        typeToObjectMapperMap.put(String.class, ((field, localizedFieldNameGetter, obj, parent) -> {
            String name = getLocalizedFieldNameOrGetFieldName(localizedFieldNameGetter, field);
            JTextField jTextField = new JTextField(obj.toString());
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel(name));
            jPanel.add(jTextField);
            return new FieldComponent(jPanel, jTextField::getText);
        }));
        //данная обработка предназначена для отображения JComboBox для enum внутри классов
        typeToObjectMapperMap.put(Enum.class, ((field, localizedFieldNameGetter, obj, parent) -> {
            String name = getLocalizedFieldNameOrGetFieldName(localizedFieldNameGetter, field);
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
            jPanel.add(new JLabel(name));
            jPanel.add(enumerationCombobox);
            return new FieldComponent(jPanel, ()->converter.get(enumerationCombobox.getItemAt(enumerationCombobox.getSelectedIndex())));
        }));
        //обработка чисел (всех наследников класса Number)
        typeToObjectMapperMap.put(Number.class, ((field, localizedFieldNameGetter, obj, parent) -> {
            String name = getLocalizedFieldNameOrGetFieldName(localizedFieldNameGetter, field);
            NumberFormatter numberFormatter = new NumberFormatter();
            numberFormatter.setValueClass(obj.getClass());
            numberFormatter.setAllowsInvalid(false);
            numberFormatter.setCommitsOnValidEdit(true);
            JFormattedTextField jFormattedTextField = new JFormattedTextField(numberFormatter);
            jFormattedTextField.setValue(obj);
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel(name));
            jPanel.add(jFormattedTextField);
            return new FieldComponent(jPanel, jFormattedTextField::getValue);
        }));
        {
            //обработка для char и класса обёртки Character
            FieldAndObjectAndTheirNameToComponentConverter fieldComponentForCharacterGenerator = ((field, localizedFieldNameGetter, obj, parent) -> {
                String name = getLocalizedFieldNameOrGetFieldName(localizedFieldNameGetter, field);
                JTextField jTextField = new JTextField();
                jTextField.setDocument(new JTextFieldLimit(1));
                jTextField.setText(obj.toString());
                JPanel jPanel = new JPanel();
                jPanel.add(new JLabel(name));
                jPanel.add(jTextField);
                return new FieldComponent(jPanel, ()->jTextField.getText().charAt(0));
            });
            typeToObjectMapperMap.put(Character.class, fieldComponentForCharacterGenerator);
            typeToObjectMapperMap.put(char.class, fieldComponentForCharacterGenerator);
        }
        {
            //обработка для boolean и класса обёртки Boolean
            FieldAndObjectAndTheirNameToComponentConverter fieldComponentForBoolean = (field, localizedFieldNameGetter, obj, parent)->{
                String name = getLocalizedFieldNameOrGetFieldName(localizedFieldNameGetter, field);
                Boolean b = (Boolean) obj;
                JCheckBox jCheckBox = new JCheckBox(name, b);
                return new FieldComponent(jCheckBox, jCheckBox::isSelected);
            };
            typeToObjectMapperMap.put(Boolean.class, fieldComponentForBoolean);
            typeToObjectMapperMap.put(boolean.class, fieldComponentForBoolean);
        }
        // TODO: 03.04.2023 добавить здесь обработку для коллекций, добавить возможность обработки коллекций из смешанных типов
//        //обработка для Collection
//        typeToObjectMapperMap.put(Collection.class, ((field, name, obj) -> {
//
//        }));
//        typeToObjectMapperMap.put(Collection.class, ((field, name, obj) -> {
//            Map<Object, Dialog> objectHashCodeToDialogMap = new HashMap<>();
//            Collection<Object> elements = (Collection<Object>) obj;
//            JList<Object> objectJList = new JList<>(elements.toArray());
//            for (Object element : elements){
//                Dialog editingDialog = null;
//                if(element.getClass().isAnnotationPresent(OEADLProcessingClass.class)){
//                    ObjectEditorDialog objectEditorDialog = new ObjectEditorDialog(element, null);
//
//                    editingDialog = objectEditorDialog;
//                }
//                else if(typeToObjectMapperMap.containsKey(element.getClass())){
//
//                }
//                objectHashCodeToDialogMap.put(element, editingDialog);
//            }
//        }));
    }
}
