package org.blbulyandavbulyan.oeadl.displayer.panels;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UnsupportedFieldException;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.function.Function;

public abstract class FieldPanel extends JPanel {
    protected Window parent;
    protected Field field;
    protected Object objectForDisplay;
    protected Function<String, String> fieldLocalizedNameGetter;
    protected String localizedFieldName;

    public FieldPanel(Window parent, Field field, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter){
        if(!field.isAnnotationPresent(OEADLField.class))throw new UnsupportedFieldException(field);
        this.parent = parent;
        this.field = field;
        this.objectForDisplay = objectForDisplay;
        this.fieldLocalizedNameGetter = fieldLocalizedNameGetter;
        this.localizedFieldName = fieldLocalizedNameGetter.apply(field.getAnnotation(OEADLField.class).localizedNamePropertyKey());
        if(localizedFieldName == null)localizedFieldName = field.getName();
    }
    public Window getParentWindow(){
        return parent;
    }
    public Field getField() {
        return field;
    }

    public Object getObjectForDisplay() {
        return objectForDisplay;
    }

    public Function<String, String> getFieldLocalizedNameGetter() {
        return fieldLocalizedNameGetter;
    }

    public String getLocalizedFieldName() {
        return localizedFieldName;
    }
}
