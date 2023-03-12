package org.blbulyandavbulyan.displayer.windows;

import org.blbulyandavbulyan.annotations.Displayable;

import javax.swing.*;
import java.util.function.Function;

import org.blbulyandavbulyan.displayer.panels.FieldNameAndValuePanel;
import org.blbulyandavbulyan.reflection.ProcessingClass;

public class ObjectDisplayerWindow<T> extends JFrame {
    protected JPanel rootPanel;
    public ObjectDisplayerWindow(Class<T> objectClass, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter){
        rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        ProcessingClass.getAllAnnotatedFieldsInOEADLProcessingClass(objectClass, Displayable.class).forEach(field -> {
            try {
                String fieldName = fieldLocalizedNameGetter.apply(field.getAnnotation(Displayable.class).localizedNamePropertyKey());
                if(fieldName == null)fieldName = field.getName();
                String fieldValue = field.get(objectForDisplay).toString();
                FieldNameAndValuePanel fieldNameAndValuePanel = new FieldNameAndValuePanel(fieldName, fieldValue);
                rootPanel.add(fieldNameAndValuePanel);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        this.getContentPane().add(rootPanel);
    }
}
