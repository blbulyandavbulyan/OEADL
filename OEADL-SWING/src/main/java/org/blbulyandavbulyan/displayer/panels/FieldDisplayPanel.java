package org.blbulyandavbulyan.displayer.panels;

import org.blbulyandavbulyan.annotations.Displayable;
import org.blbulyandavbulyan.annotations.OEADLProcessingClass;
import org.blbulyandavbulyan.displayer.windows.ObjectDisplayerWindow;
import org.blbulyandavbulyan.exceptions.UnsupportedFieldTypeException;
import org.blbulyandavbulyan.reflection.FieldToComponent;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.Function;

public class FieldDisplayPanel extends JPanel {
    static protected FieldToComponent fieldToComponent;
    static {
        fieldToComponent = new FieldToComponent();
    }
    protected JLabel jFieldNameLabel;
    protected Component fieldDisplayComponent;
    public FieldDisplayPanel(Window parent, Field field, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter) throws IllegalAccessException {
        String fieldName = fieldLocalizedNameGetter.apply(field.getAnnotation(Displayable.class).localizedNamePropertyKey());
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
            ObjectDisplayerWindow objectDisplayerWindow = new ObjectDisplayerWindow(parent, field.getType(), field.get(objectForDisplay), fieldLocalizedNameGetter);
            jWatchObject.addActionListener(l->{
                objectDisplayerWindow.setVisible(!objectDisplayerWindow.isVisible());
            });
            return jWatchObject;
        }
        else{
            if(fieldToComponent.canConvert(field)){
                return fieldToComponent.convert(field, objectForDisplay);
            }
        }
        throw new UnsupportedFieldTypeException(field);
    }
}
