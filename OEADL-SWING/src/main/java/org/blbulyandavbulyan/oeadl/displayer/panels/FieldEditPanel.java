package org.blbulyandavbulyan.oeadl.displayer.panels;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UneditableFieldException;
import org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent.FieldToComponentForEdit;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.function.Function;

public class FieldEditPanel extends FieldPanel {
    protected FieldToComponentForEdit fieldToComponentForEdit;

    public FieldEditPanel(Window parent, Field field, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter) {
        super(parent, field, objectForDisplay, fieldLocalizedNameGetter);
        fieldToComponentForEdit = new FieldToComponentForEdit();
        if(!field.getAnnotation(OEADLField.class).editable())throw new UneditableFieldException(field);

    }
}
