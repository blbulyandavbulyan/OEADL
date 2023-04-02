package org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent;
import javax.swing.*;
import java.util.HashMap;

public class FieldToComponentForEdit extends FieldToComponent {
    public FieldToComponentForEdit(){
        typeToObjectMapperMap.put(Boolean.class, (field, name, obj)->{
            Boolean b = (Boolean) obj;
            JCheckBox jCheckBox = new JCheckBox(name, b);
            return new FieldComponent(jCheckBox, jCheckBox::isVisible);
        });
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
    }
}
