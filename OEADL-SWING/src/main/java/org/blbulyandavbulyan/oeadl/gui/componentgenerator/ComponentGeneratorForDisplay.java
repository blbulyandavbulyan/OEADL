package org.blbulyandavbulyan.oeadl.gui.componentgenerator;

import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDialog;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDisplayerDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GenerateObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GetResourceBundleByClass;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ComponentGeneratorForDisplay extends ComponentGenerator {
    // FIXME: 11.04.2023 Если в конвертор в этом классе придёт null в качестве obj, то вылетит NullPointerException, такое возможно если поле в классе null
    protected static final Class<?> []toStringTypes = {
            int.class, long.class, short.class, float.class, double.class, byte.class, boolean.class, char.class, Number.class, String.class, Enum.class
    };
    public ComponentGeneratorForDisplay(){
        //типы для которых сразу можно использовать метод toString
        ObjectToComponentAndValueGetterConverter objToLabelUsingToString = (String objectDisplayableName, Object obj, ObjectDialog parent, ResourceBundle uiResourceBundle, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass) -> {
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel(objectDisplayableName));
            jPanel.add(new JLabel(obj.toString()));
            return new ComponentAndValueGetter(jPanel, ()-> obj);
        };

        for(Class<?> toStringType : toStringTypes)
            typeToObjectMapperMap.put(toStringType, objToLabelUsingToString);
        //типы данных для которых мы будем использовать класс JList для отображения(в дальнейшем можем и поменять)
        //Collection и его базовые потомки
        ObjectToComponentAndValueGetterConverter iterableObjectConverter =  (String objectDisplayableName, Object obj, ObjectDialog parent, ResourceBundle uiResourceBundle, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass) ->{
            Object[] objects = null;
            HashMap<Object, ObjectDisplayerDialog> objectToItsDisplayer = new HashMap<>();
            if(obj instanceof Collection){
                objects = ((Collection<Object>) obj).toArray();
            }
            else if(obj instanceof Object[]){
                objects = (Object[]) obj;
            }
            else throw new IllegalArgumentException("argument for this function must be collection or array");
            JPanel jComponentDisplayerPanel = new JPanel(new GridBagLayout());
            JList<Object> objectsJList = new JList<>(objects);
            JLabel jNameLabel = new JLabel(objectDisplayableName);
            JButton showDetailsButton = new JButton(uiResourceBundle.getString("oeadl_swing.buttons.watchdetails"));
            showDetailsButton.addActionListener(actionEvent -> {
                Object selectedObject = objectsJList.getSelectedValue();
                if(selectedObject != null && objectToItsDisplayer.containsKey(selectedObject)){
                    ObjectDisplayerDialog objectDisplayerDialog = objectToItsDisplayer.get(selectedObject);
                    objectDisplayerDialog.setVisible(!objectDisplayerDialog.isVisible());
                }
            });
            objectsJList.addListSelectionListener(listSelectionEvent -> {
                showDetailsButton.setEnabled(!(objectsJList.isSelectionEmpty() || !objectToItsDisplayer.containsKey(objectsJList.getSelectedValue())));
            });
            for (Object elementInArray : objects) {
                if(elementInArray.getClass().isAnnotationPresent(OEADLProcessingClass.class)){
                    objectToItsDisplayer.put(elementInArray, (ObjectDisplayerDialog) generateObjectDialog.generateObjectDialog(parent, elementInArray));
                }
            }
            objectsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            showDetailsButton.setEnabled(false);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            jComponentDisplayerPanel.add(jNameLabel, gbc);
            gbc.gridy = 1;
            jComponentDisplayerPanel.add(new JScrollPane(objectsJList), gbc);
            gbc.gridy = 2;
            jComponentDisplayerPanel.add(showDetailsButton, gbc);
            return new ComponentAndValueGetter(jComponentDisplayerPanel, ()->obj);
        };
        for(Class<?> iterableType : iterableTypes)
            typeToObjectMapperMap.put(iterableType, iterableObjectConverter);
        for(Class<?> wrappedArrayType : objectArrayTypesWhereIsPossibleToStringForElement)
            typeToObjectMapperMap.put(wrappedArrayType, iterableObjectConverter);

        //обработка для Map

        typeToObjectMapperMap.put(AbstractMap.class, (objectDisplayableName, obj, parent, uiResourceBundle, generateObjectDialog, getResourceBundleByClass) -> {
            HashMap<Object, ObjectDisplayerDialog> objectToItsDisplayerDialog = new HashMap<>();
            Map<Object, Object> processingMap = (Map<Object, Object>) obj;
            JComboBox<Object> keySelector = new JComboBox<>();
            CardLayout cardLayout = new CardLayout();
            JPanel valuesPanel = new JPanel(cardLayout);
            keySelector.addActionListener(l->{
                Object selectedKey = keySelector.getSelectedItem();
                if(selectedKey != null)cardLayout.show(valuesPanel, selectedKey.toString());
            });
            keySelector.setEditable(false);
            processingMap.entrySet().forEach((objectObjectEntry -> {
                Object key = objectObjectEntry.getKey();
                Object value = objectObjectEntry.getValue();
                if(value.getClass().isAnnotationPresent(OEADLProcessingClass.class)){
                    JButton watchDetailsButton = new JButton(uiResourceBundle.getString("oeadl_swing.buttons.watchdetails"));
                    ObjectDisplayerDialog objectDisplayerDialog = (ObjectDisplayerDialog) generateObjectDialog.generateObjectDialog(parent, value);
                    watchDetailsButton.addActionListener(l ->{
                        objectDisplayerDialog.setVisible(!objectDisplayerDialog.isVisible());
                    });
                    keySelector.addItem(key);
                    valuesPanel.add(key.toString(), watchDetailsButton);
                }
                else{
                    keySelector.addItem(key);
                    valuesPanel.add(key.toString(), generateComponentAndValueGetterForGivingTypeOrForItsParentClass(
                            parent, "", value, generateObjectDialog,
                            getResourceBundleByClass, uiResourceBundle
                    ).getDisplayableComponent());
                }
            }));
            JPanel mapDisplayPanel = new JPanel();
            mapDisplayPanel.add(keySelector);
            mapDisplayPanel.add(valuesPanel);
            return new ComponentAndValueGetter(mapDisplayPanel, ()->obj);
        });
    }
}
