package org.blbulyandavbulyan.displayer.dialogs;


import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.blbulyandavbulyan.annotations.OEADLField;
import org.blbulyandavbulyan.displayer.panels.FieldDisplayPanel;
import org.blbulyandavbulyan.exceptions.invalidclass.NoFieldsForDisplayException;

public class ObjectDisplayerDialog extends ObjectDialog {
    protected Collection<Field> displayableFields;
    public ObjectDisplayerDialog(Window parent, Class<?> objectClass, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter){
        super(parent, objectClass, objectForDisplay, fieldLocalizedNameGetter);
        rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        displayableFields = fieldsForProcessing.stream().filter(field -> field.getAnnotation(OEADLField.class).displayable()).collect(Collectors.toList());
        if(!displayableFields.isEmpty()){
            displayableFields.forEach(
                    field -> {
                        try {
                            FieldDisplayPanel fieldDisplayPanel = new FieldDisplayPanel(parent, field, objectForDisplay, fieldLocalizedNameGetter);
                            rootPanel.add(fieldDisplayPanel);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
        else throw new NoFieldsForDisplayException(objectClass);
        this.getContentPane().add(rootPanel);
        this.pack();
    }
    public ObjectDisplayerDialog(Class<?> objectClass, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter){
        this(null, objectClass, objectForDisplay, fieldLocalizedNameGetter);
    }
}
