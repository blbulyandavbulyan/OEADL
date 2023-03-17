package org.blbulyandavbulyan.oeadl.displayer.panels;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;
import org.blbulyandavbulyan.oeadl.displayer.dialogs.ObjectDisplayerDialog;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UndisplayableFieldException;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UnsupportedFieldTypeException;
import org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent.FieldToComponentForDisplay;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.function.Function;

public class FieldDisplayPanel extends FieldPanel {
    static protected FieldToComponentForDisplay fieldToComponentForDisplay;
    static {
        fieldToComponentForDisplay = new FieldToComponentForDisplay();
    }
    protected JLabel jFieldNameLabel;
    protected Component fieldDisplayComponent;
    public FieldDisplayPanel(Window parent, Field field, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter) throws IllegalAccessException {
        super(parent, field, objectForDisplay, fieldLocalizedNameGetter);
        if(!field.getAnnotation(OEADLField.class).displayable())throw new UndisplayableFieldException(field);
        this.jFieldNameLabel = new JLabel(localizedFieldName);
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
            else throw new UnsupportedFieldTypeException(field);
        }
    }
}
