package org.blbulyandavbulyan.displayer.windows;

import org.blbulyandavbulyan.annotations.Displayable;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

import org.blbulyandavbulyan.annotations.OEADLProcessingClass;
import org.blbulyandavbulyan.displayer.panels.FieldDisplayPanel;
import org.blbulyandavbulyan.reflection.ProcessingClass;

public class ObjectDisplayerWindow extends JDialog {
    protected JPanel rootPanel;
    public ObjectDisplayerWindow(Window parent, Class<?> objectClass, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter){
        super(parent);
        rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        ProcessingClass.getAllAnnotatedFieldsInOEADLProcessingClass(objectClass, Displayable.class).forEach(field -> {
            try {
                FieldDisplayPanel fieldDisplayPanel = new FieldDisplayPanel(parent, field, objectForDisplay, fieldLocalizedNameGetter);
                rootPanel.add(fieldDisplayPanel);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        this.getContentPane().add(rootPanel);
        this.pack();
    }
    public ObjectDisplayerWindow(Class<?> objectClass, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter){
        this(null, objectClass, objectForDisplay, fieldLocalizedNameGetter);
    }
}
