package org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDialog;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDisplayerDialog;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UndisplayableFieldException;
import org.blbulyandavbulyan.oeadl.gui.componentgenerator.ComponentGeneratorForDisplay;
import org.blbulyandavbulyan.oeadl.interfaces.GenerateObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GetResourceBundleByClass;

import java.lang.reflect.Field;
import java.util.ResourceBundle;
import java.util.function.Function;

public class FieldDisplayPanel extends FieldPanel {
    static protected ComponentGeneratorForDisplay fieldToComponentForDisplay;
    static {
        fieldToComponentForDisplay = new ComponentGeneratorForDisplay();
    }
    public FieldDisplayPanel(ObjectDialog parent, Field field, Object objectForDisplay, ResourceBundle uiResourceBundle, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass) throws IllegalAccessException {
        super(parent, field, objectForDisplay, fieldToComponentForDisplay, uiResourceBundle.getString("oeadl_swing.buttons.watchdetails"), generateObjectDialog, getResourceBundleByClass, uiResourceBundle);
        if(!field.getAnnotation(OEADLField.class).displayable())throw new UndisplayableFieldException(field);
//        this.add(fieldComponent.getDisplayableComponent());
    }

}
