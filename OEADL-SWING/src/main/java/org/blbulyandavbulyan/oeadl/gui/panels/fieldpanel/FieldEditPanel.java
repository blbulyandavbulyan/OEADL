package org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectEditorDialog;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UneditableFieldException;
import org.blbulyandavbulyan.oeadl.gui.interfaces.GetValue;
import org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent.FieldToComponentForEdit;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.function.Function;

public class FieldEditPanel extends FieldPanel implements GetValue {
    protected static FieldToComponentForEdit fieldToComponentForEdit;
    static {
        fieldToComponentForEdit = new FieldToComponentForEdit();
    }
    public FieldEditPanel(Window parent, Field field, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter, String showObjectDialogButtonText) {
        super(parent, field, objectForDisplay, fieldLocalizedNameGetter, fieldToComponentForEdit, ObjectEditorDialog.class, showObjectDialogButtonText);
        if(!field.getAnnotation(OEADLField.class).editable())throw new UneditableFieldException(field);
    }
    public Object getValue(){
        return isPanelShowsObjectDialog() ? getObjectDialog().getValue() : fieldComponent.getValue();
    }
}
