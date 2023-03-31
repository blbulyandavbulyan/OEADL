package org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent;

import org.blbulyandavbulyan.oeadl.exceptions.OEADLException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.lang.reflect.Field;

public class FieldToComponentForEdit extends FieldToComponent {
    public FieldToComponentForEdit(){
        typeToObjectMapperMap.put(Boolean.class, (field, name, obj)->{
            Boolean b = (Boolean) obj;
            JCheckBox jCheckBox = new JCheckBox(name, b);
//            jCheckBox.addActionListener(l->{
//                try {
//                    field.set(obj, jCheckBox.isSelected());
//                } catch (IllegalAccessException e) {
//                    throw new OEADLException(e);
//                }
//            });
            return jCheckBox;
        });
        typeToObjectMapperMap.put(String.class, ((field, name, obj) -> {
            JTextField jTextField = new JTextField(obj.toString());
//            jTextField.addActionListener(l-> {
//
//            });
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel(name));
            jPanel.add(jTextField);
            return jPanel;
        }));
    }
}
