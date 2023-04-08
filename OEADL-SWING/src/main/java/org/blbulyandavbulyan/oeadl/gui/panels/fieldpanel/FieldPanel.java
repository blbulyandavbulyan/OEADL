
package org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDialog;
import org.blbulyandavbulyan.oeadl.gui.interfaces.GetValue;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UnsupportedFieldException;
import org.blbulyandavbulyan.oeadl.reflection.ProcessingField;
import org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent.ComponentAndValueGetter;
import org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent.ComponentGenerator;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.function.Function;

public abstract class FieldPanel extends JPanel implements GetValue {
    protected Window parent;
    protected Field field;
    protected Object processingObjectInThisFieldPanel;

    protected Function<String, String> localizedNameGetter;
    protected String fieldNameOrLocalizedFieldName;
    protected ComponentAndValueGetter componentAndValueGetter;
    protected Class<? extends ObjectDialog> objectDialogClass;
    protected boolean panelShowsObjectDialog = false;
    protected ObjectDialog objectDialog = null;
    public FieldPanel(Window parent, Field field, Object processingObjectInThisFieldPanel, Function<String, String> localizedNameGetter, ComponentGenerator componentGenerator, Class<? extends ObjectDialog> objectDialogClass, String showObjectDialogButtonText){
        if(!field.isAnnotationPresent(OEADLField.class))throw new UnsupportedFieldException(field);
        this.parent = parent;
        this.field = field;
        this.processingObjectInThisFieldPanel = processingObjectInThisFieldPanel;
        this.localizedNameGetter = localizedNameGetter;
        this.fieldNameOrLocalizedFieldName = ProcessingField.getLocalizedFieldNameOrGetFieldName(localizedNameGetter, field);
        this.objectDialogClass = objectDialogClass;
        this.componentAndValueGetter = componentGenerator.generateFieldComponent(
                parent, field, processingObjectInThisFieldPanel, localizedNameGetter, objectDialogClass, showObjectDialogButtonText
        );
        this.add(componentAndValueGetter.getDisplayableComponent());
    }
    public Window getParentWindow(){
        return parent;
    }
    public Field getField() {
        return field;
    }

    public Object getProcessingObjectInThisFieldPanel() {
        return processingObjectInThisFieldPanel;
    }
    public Function<String, String> getLocalizedNameGetter() {
        return localizedNameGetter;
    }

    public ComponentAndValueGetter getFieldComponent() {
        return componentAndValueGetter;
    }
    public String getFieldNameOrLocalizedFieldName() {
        return fieldNameOrLocalizedFieldName;
    }

//    protected boolean isPanelShowsObjectDialog(){
//        return panelShowsObjectDialog;
//    }
//    protected ObjectDialog getObjectDialog(){
//        if(isPanelShowsObjectDialog()){
//            return objectDialog;
//        }
//        else throw new FieldPanelDoesntShowObjectDialogException();
//    }
    public Object getValue(){
        return componentAndValueGetter.getValue();
    }
}
