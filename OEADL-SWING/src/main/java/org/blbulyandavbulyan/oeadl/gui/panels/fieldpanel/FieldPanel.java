
package org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GenerateObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GetResourceBundleByClass;
import org.blbulyandavbulyan.oeadl.interfaces.GetValue;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UnsupportedFieldException;
import org.blbulyandavbulyan.oeadl.gui.componentgenerator.ComponentAndValueGetter;
import org.blbulyandavbulyan.oeadl.gui.componentgenerator.ComponentGenerator;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ResourceBundle;
import java.util.function.Function;

public abstract class FieldPanel extends JPanel implements GetValue {
    protected Window parent;
    protected Field field;
    protected Object processingObjectInThisFieldPanel;

    protected Function<String, String> localizedNameGetter;
    protected ComponentAndValueGetter componentAndValueGetter;
    protected Class<? extends ObjectDialog> objectDialogClass;
    public FieldPanel(ObjectDialog parent, Field field, Object processingObjectInThisFieldPanel, ComponentGenerator componentGenerator, String showObjectDialogText, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass, ResourceBundle uiResourceBundle){
        if(!field.isAnnotationPresent(OEADLField.class))throw new UnsupportedFieldException(field);
        this.parent = parent;
        this.field = field;
        this.processingObjectInThisFieldPanel = processingObjectInThisFieldPanel;
        this.componentAndValueGetter = componentGenerator.generateComponentAndValueGetter(parent, field, processingObjectInThisFieldPanel, showObjectDialogText, generateObjectDialog, getResourceBundleByClass, uiResourceBundle);
        this.add(componentAndValueGetter.getDisplayableComponent());
    }
    public Window getParentWindow(){
        return parent;
    }
    public Field getField() {
        return field;
    }


    public ComponentAndValueGetter getFieldComponent() {
        return componentAndValueGetter;
    }
    public Object getValue(){
        return componentAndValueGetter.getValue();
    }
}
