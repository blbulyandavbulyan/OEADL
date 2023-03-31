package org.blbulyandavbulyan.oeadl.displayer.panels.fieldpanel;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.displayer.dialogs.ObjectEditorDialog;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UneditableFieldException;
import org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent.FieldToComponentForEdit;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.function.Function;

public class FieldEditPanel extends FieldPanel {
    protected static FieldToComponentForEdit fieldToComponentForEdit;
    static {
        fieldToComponentForEdit = new FieldToComponentForEdit();
    }
    protected JLabel jFieldNameLabel;

    public FieldEditPanel(Window parent, Field field, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter, String showObjectDialogButtonText) {
        super(parent, field, objectForDisplay, fieldLocalizedNameGetter, fieldToComponentForEdit, ObjectEditorDialog.class, showObjectDialogButtonText);
        if(!field.getAnnotation(OEADLField.class).editable())throw new UneditableFieldException(field);
        jFieldNameLabel = new JLabel(fieldNameOrLocalizedFieldName);
//        this.fieldComponent = fieldToComponentForEdit.convert(field, )
    }
}
