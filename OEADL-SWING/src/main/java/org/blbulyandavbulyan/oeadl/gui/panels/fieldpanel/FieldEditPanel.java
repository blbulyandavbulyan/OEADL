package org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDialog;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UneditableFieldException;
import org.blbulyandavbulyan.oeadl.interfaces.GenerateObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GetResourceBundleByClass;
import org.blbulyandavbulyan.oeadl.interfaces.GetValue;
import org.blbulyandavbulyan.oeadl.gui.componentgenerator.ComponentGeneratorForEdit;

import java.lang.reflect.Field;
import java.util.ResourceBundle;
import java.util.function.Function;

public class FieldEditPanel extends FieldPanel implements GetValue {
    protected static ComponentGeneratorForEdit fieldToComponentForEdit;
    static {
        fieldToComponentForEdit = new ComponentGeneratorForEdit();
    }
    public FieldEditPanel(ObjectDialog parent, Field field, Object objectForDisplay, ResourceBundle uiResourceBundle, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass){
        super(parent, field, objectForDisplay, fieldToComponentForEdit, uiResourceBundle.getString("oeadl_swing.buttons.edit"), generateObjectDialog, getResourceBundleByClass, uiResourceBundle);
        if(!field.getAnnotation(OEADLField.class).editable())throw new UneditableFieldException(field);
    }
}
