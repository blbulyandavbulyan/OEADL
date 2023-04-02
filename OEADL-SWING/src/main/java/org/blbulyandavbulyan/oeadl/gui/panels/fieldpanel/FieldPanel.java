package org.blbulyandavbulyan.oeadl.displayer.panels.fieldpanel;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;
import org.blbulyandavbulyan.oeadl.displayer.dialogs.ObjectDialog;
import org.blbulyandavbulyan.oeadl.displayer.panels.exceptions.FieldPanelWithObjectDialogCreationException;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UnsupportedFieldException;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UnsupportedFieldTypeException;
import org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent.FieldToComponent;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

public abstract class FieldPanel extends JPanel {
    protected Window parent;
    protected Field field;
    protected Object processingObejct;
    protected Function<String, String> localizedNameGetter;
    protected String fieldNameOrLocalizedFieldName;
    protected Component fieldComponent;
    protected Class<? extends ObjectDialog> objectDialogClass;
    public FieldPanel(Window parent, Field field, Object processingObejct, Function<String, String> localizedNameGetter, FieldToComponent fieldToComponent, Class<? extends ObjectDialog> objectDialogClass, String showObjectDialogButtonText){
        if(!field.isAnnotationPresent(OEADLField.class))throw new UnsupportedFieldException(field);
        this.parent = parent;
        this.field = field;
        this.processingObejct = processingObejct;
        this.localizedNameGetter = localizedNameGetter;
        this.fieldNameOrLocalizedFieldName = localizedNameGetter.apply(field.getAnnotation(OEADLField.class).localizedNamePropertyKey());
        this.objectDialogClass = objectDialogClass;
        if(fieldNameOrLocalizedFieldName == null || fieldNameOrLocalizedFieldName.isBlank()) fieldNameOrLocalizedFieldName = field.getName();
        this.fieldComponent = generateFieldComponent(parent, field, processingObejct, localizedNameGetter, fieldToComponent, objectDialogClass, showObjectDialogButtonText);

    }
    public Window getParentWindow(){
        return parent;
    }
    public Field getField() {
        return field;
    }

    public Object getProcessingObejct() {
        return processingObejct;
    }

    public Function<String, String> getLocalizedNameGetter() {
        return localizedNameGetter;
    }

    public Class<? extends ObjectDialog> getObjectDialogClass() {
        return objectDialogClass;
    }

    public Component getFieldComponent() {
        return fieldComponent;
    }
    public String getFieldNameOrLocalizedFieldName() {
        return fieldNameOrLocalizedFieldName;
    }
    private Component generateFieldComponent(Window parent, Field field, Object processingObject, Function<String, String> fieldLocalizedNameGetter, FieldToComponent fieldToComponent, Class<? extends ObjectDialog> objectDialogClass, String showObjectDialogButtonText){
        Class<?> fieldType = field.getType();
        if(fieldType.isAnnotationPresent(OEADLProcessingClass.class)){
            JButton jWatchObject = new JButton(showObjectDialogButtonText);
            try{
                ObjectDialog objectDialog = objectDialogClass.getDeclaredConstructor(Window.class, Object.class, Function.class)
                        .newInstance(parent, processingObject, fieldLocalizedNameGetter);
                jWatchObject.addActionListener(l-> objectDialog.setVisible(!objectDialog.isVisible()));
            }
            catch(NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
                throw new FieldPanelWithObjectDialogCreationException(e, objectDialogClass);
            }
            return jWatchObject;
        }
        else{
            if(fieldToComponent.canConvert(field)){
                return fieldToComponent.convert(field, fieldNameOrLocalizedFieldName, processingObject);
            }
            else throw new UnsupportedFieldTypeException(field);
        }
    }
}
