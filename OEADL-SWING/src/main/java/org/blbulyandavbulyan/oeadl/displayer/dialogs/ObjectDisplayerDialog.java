package org.blbulyandavbulyan.oeadl.displayer.dialogs;


import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.displayer.panels.FieldDisplayPanel;
import org.blbulyandavbulyan.oeadl.exceptions.invalidclass.NoFieldsForDisplayException;

public class ObjectDisplayerDialog extends ObjectDialog {

    protected Collection<Field> displayableFields;
    public ObjectDisplayerDialog(Window parent, Class<?> objectClass, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter){
        super(parent, objectClass, objectForDisplay, fieldLocalizedNameGetter);
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
        this.pack();
    }
    public ObjectDisplayerDialog(Class<?> objectClass, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter){
        this(null, objectClass, objectForDisplay, fieldLocalizedNameGetter);
    }
}
