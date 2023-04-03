package org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent;
import org.blbulyandavbulyan.oeadl.gui.forjtextfield.JTextFieldLimit;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.util.HashMap;

public class FieldToComponentForEdit extends FieldToComponent {
    public FieldToComponentForEdit(){
        typeToObjectMapperMap.put(String.class, ((field, name, obj) -> {
            JTextField jTextField = new JTextField(obj.toString());
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel(name));
            jPanel.add(jTextField);
            return new FieldComponent(jPanel, jTextField::getText);
        }));
        //данная обработка предназначена для отображения JComboBox для enum внутри классов
        typeToObjectMapperMap.put(Enum.class, ((field, name, obj) -> {
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
        typeToObjectMapperMap.put(Number.class, ((field, name, obj) -> {
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
            FieldAndObjectAndTheirNameToComponentConverter fieldComponentForCharacterGenerator = ((field, name, obj) -> {
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
            FieldAndObjectAndTheirNameToComponentConverter fieldComponentForBoolean = (field, name, obj)->{
                Boolean b = (Boolean) obj;
                JCheckBox jCheckBox = new JCheckBox(name, b);
                return new FieldComponent(jCheckBox, jCheckBox::isSelected);
            };
            typeToObjectMapperMap.put(Boolean.class, fieldComponentForBoolean);
            typeToObjectMapperMap.put(boolean.class, fieldComponentForBoolean);
        }
    }
}
