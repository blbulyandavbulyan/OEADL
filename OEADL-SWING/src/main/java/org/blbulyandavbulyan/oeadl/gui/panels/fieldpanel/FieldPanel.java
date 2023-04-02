
package org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDialog;
import org.blbulyandavbulyan.oeadl.gui.panels.exceptions.FieldPanelDoesntShowObjectDialogException;
import org.blbulyandavbulyan.oeadl.gui.panels.exceptions.FieldPanelWithObjectDialogCreationException;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UnsupportedFieldException;
import org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent.FieldComponent;
import org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent.FieldToComponent;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

public abstract class FieldPanel extends JPanel{
    protected Window parent;
    protected Field field;
    protected Object processingObjectInThisFieldPanel;

    protected Function<String, String> localizedNameGetter;
    protected String fieldNameOrLocalizedFieldName;
    protected FieldComponent fieldComponent;
    protected Class<? extends ObjectDialog> objectDialogClass;
    protected boolean panelShowsObjectDialog = false;
    protected ObjectDialog objectDialog = null;
    public FieldPanel(Window parent, Field field, Object processingObjectInThisFieldPanel, Function<String, String> localizedNameGetter, FieldToComponent fieldToComponent, Class<? extends ObjectDialog> objectDialogClass, String showObjectDialogButtonText){
        if(!field.isAnnotationPresent(OEADLField.class))throw new UnsupportedFieldException(field);
        this.parent = parent;
        this.field = field;
        this.processingObjectInThisFieldPanel = processingObjectInThisFieldPanel;
        this.localizedNameGetter = localizedNameGetter;
        this.fieldNameOrLocalizedFieldName = localizedNameGetter.apply(field.getAnnotation(OEADLField.class).localizedNamePropertyKey());
        this.objectDialogClass = objectDialogClass;
        if(fieldNameOrLocalizedFieldName == null || fieldNameOrLocalizedFieldName.isBlank()) fieldNameOrLocalizedFieldName = field.getName();
        this.fieldComponent = generateFieldComponent(
                parent, field, processingObjectInThisFieldPanel, localizedNameGetter, fieldToComponent, objectDialogClass, showObjectDialogButtonText
        );
        this.add(fieldComponent.getDisplayableComponent());
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

    public FieldComponent getFieldComponent() {
        return fieldComponent;
    }
    public String getFieldNameOrLocalizedFieldName() {
        return fieldNameOrLocalizedFieldName;
    }
    private FieldComponent generateFieldComponent(Window parent, Field field, Object processingObject, Function<String, String> fieldLocalizedNameGetter, FieldToComponent fieldToComponent, Class<? extends ObjectDialog> objectDialogClass, String showObjectDialogButtonText){
        Class<?> fieldType = field.getType();
        if(fieldType.isAnnotationPresent(OEADLProcessingClass.class)){
            try{
                ObjectDialog objectDialog = objectDialogClass.getDeclaredConstructor(Window.class, Object.class, Function.class)
                        .newInstance(parent, processingObject, fieldLocalizedNameGetter);
                JButton jWatchObject = new JButton(showObjectDialogButtonText);
                jWatchObject.addActionListener(l-> objectDialog.setVisible(!objectDialog.isVisible()));
                panelShowsObjectDialog = true;
                this.objectDialog = objectDialog;
                JPanel jPanel = new JPanel();
                jPanel.add(new JLabel(fieldNameOrLocalizedFieldName));
                jPanel.add(jWatchObject);
                return new FieldComponent(jPanel, objectDialog);
            }
            catch(NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
                throw new FieldPanelWithObjectDialogCreationException(e, objectDialogClass);
            }
        }
        else{
            return fieldToComponent.convert(field, fieldNameOrLocalizedFieldName, processingObject);
        }
    }
    protected boolean isPanelShowsObjectDialog(){
        return panelShowsObjectDialog;
    }
    protected ObjectDialog getObjectDialog(){
        if(isPanelShowsObjectDialog()){
            return objectDialog;
        }
        else throw new FieldPanelDoesntShowObjectDialogException();
    }
}
