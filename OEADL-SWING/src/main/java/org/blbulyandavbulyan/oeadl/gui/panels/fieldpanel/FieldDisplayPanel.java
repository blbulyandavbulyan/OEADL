package org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDisplayerDialog;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UndisplayableFieldException;
import org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent.FieldToComponentForDisplay;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.function.Function;

public class FieldDisplayPanel extends FieldPanel {
    static protected FieldToComponentForDisplay fieldToComponentForDisplay;
    static {
        fieldToComponentForDisplay = new FieldToComponentForDisplay();
    }
    public FieldDisplayPanel(Window parent, Field field, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter, String showObjectDialogButtonText) throws IllegalAccessException {
        super(parent, field, objectForDisplay, fieldLocalizedNameGetter, fieldToComponentForDisplay, ObjectDisplayerDialog.class, showObjectDialogButtonText);
        if(!field.getAnnotation(OEADLField.class).displayable())throw new UndisplayableFieldException(field);
//        this.add(fieldComponent.getDisplayableComponent());
    }

}
