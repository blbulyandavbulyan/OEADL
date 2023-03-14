package org.blbulyandavbulyan.displayer.panels;

import org.blbulyandavbulyan.annotations.OEADLField;
import org.blbulyandavbulyan.annotations.OEADLProcessingClass;
import org.blbulyandavbulyan.displayer.dialogs.ObjectDisplayerDialog;
import org.blbulyandavbulyan.exceptions.invalidfields.UndisplayableFieldException;
import org.blbulyandavbulyan.exceptions.invalidfields.UnsupportedFieldTypeException;
import org.blbulyandavbulyan.reflection.FieldToComponentForDisplay;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.function.Function;

public class FieldDisplayPanel extends JPanel {
    static protected FieldToComponentForDisplay fieldToComponentForDisplay;
    static {
        fieldToComponentForDisplay = new FieldToComponentForDisplay();
    }
    protected JLabel jFieldNameLabel;
    protected Component fieldDisplayComponent;
    public FieldDisplayPanel(Window parent, Field field, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter) throws IllegalAccessException {
        if(!field.getAnnotation(OEADLField.class).displayable())throw new UndisplayableFieldException(field);
        String fieldName = fieldLocalizedNameGetter.apply(field.getAnnotation(OEADLField.class).localizedNamePropertyKey());
        if(fieldName == null)fieldName = field.getName();
        this.jFieldNameLabel = new JLabel(fieldName);
        this.fieldDisplayComponent = getFieldDisplayComponent(parent, field, objectForDisplay, fieldLocalizedNameGetter);
        this.add(jFieldNameLabel);
        this.add(fieldDisplayComponent);
    }
    private static Component getFieldDisplayComponent(Window parent, Field field, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter) throws IllegalAccessException {
        Class<?> fieldType = field.getType();
        if(fieldType.isAnnotationPresent(OEADLProcessingClass.class)){
            JButton jWatchObject = new JButton("Просмотреть детали");
            ObjectDisplayerDialog objectDisplayerDialog = new ObjectDisplayerDialog(parent, field.getType(), field.get(objectForDisplay), fieldLocalizedNameGetter);
            jWatchObject.addActionListener(l->{
                objectDisplayerDialog.setVisible(!objectDisplayerDialog.isVisible());
            });
            return jWatchObject;
        }
        else{
            if(fieldToComponentForDisplay.canConvert(field)){
                return fieldToComponentForDisplay.convert(field, objectForDisplay);
            }
        }
        throw new UnsupportedFieldTypeException(field);
    }
}
