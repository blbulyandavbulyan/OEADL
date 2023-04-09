package org.blbulyandavbulyan.oeadl.gui.componentgenerator;

import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GenerateObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GetResourceBundleByClass;

import java.awt.*;
import java.util.ResourceBundle;
import java.util.function.Function;


public interface ObjectToComponentAndValueGetterConverter {
    ComponentAndValueGetter convertToComponentAndValueGetter(String objectDisplayableName, Object obj, ObjectDialog parent, ResourceBundle uiResourceBundle, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass);
}
