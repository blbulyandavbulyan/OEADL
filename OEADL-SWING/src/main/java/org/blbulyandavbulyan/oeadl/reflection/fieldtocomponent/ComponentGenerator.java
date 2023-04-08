package org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent;

import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UnsupportedFieldException;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDialog;
import org.blbulyandavbulyan.oeadl.gui.panels.exceptions.FieldPanelWithObjectDialogCreationException;
import org.blbulyandavbulyan.oeadl.reflection.ProcessingField;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class ComponentGenerator {
    //типы для которых гарантированно применение toString()
    protected static final Class<?> []iterableTypes = {
            Collection.class
    };
    protected static final Class<?> [] objectArrayTypesWhereIsPossibleToStringForElement = {
            Number[].class, Character[].class, String[].class, Boolean[].class
    };

    protected Map<Class<?>, FieldAndObjectAndTheirNameToComponentConverter> typeToObjectMapperMap;
    protected ComponentGenerator(){
        typeToObjectMapperMap = new HashMap<>();
    }
    protected boolean canConvert(Field field){
        return typeToObjectMapperMap.containsKey(field.getType());
    }
    public void removeConverter(Field field){
        typeToObjectMapperMap.remove(field.getType());
    }
    public ComponentAndValueGetter generateFieldComponent(Window parent, Field field, Object processingObject, Function<String, String> fieldLocalizedNameGetter, Class<? extends ObjectDialog> objectDialogClass, String showObjectDialogButtonText){
        Class<?> fieldType = field.getType();
        String fieldName = ProcessingField.getLocalizedFieldNameOrGetFieldName(fieldLocalizedNameGetter, field);
        if(canConvert(field))
            return typeToObjectMapperMap.get(fieldType).convertToComponentAndValueGetter(fieldName, fieldLocalizedNameGetter, processingObject, parent);
        else if(fieldType.isAnnotationPresent(OEADLProcessingClass.class)){
            try{
                ObjectDialog objectDialog = objectDialogClass.getDeclaredConstructor(Window.class, Object.class, Function.class)
                        .newInstance(parent, processingObject, fieldLocalizedNameGetter);
                JButton jWatchObject = new JButton(showObjectDialogButtonText);
                jWatchObject.addActionListener(l-> objectDialog.setVisible(!objectDialog.isVisible()));
                JPanel jPanel = new JPanel();
                jPanel.add(new JLabel(fieldName));
                jPanel.add(jWatchObject);
                return new ComponentAndValueGetter(jPanel, objectDialog);
            }
            catch(NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
                throw new FieldPanelWithObjectDialogCreationException(e, objectDialogClass);
            }
        }
        else if(Enum.class.isAssignableFrom(fieldType))
            return typeToObjectMapperMap.get(Enum.class).convertToComponentAndValueGetter(fieldName, fieldLocalizedNameGetter, processingObject, parent);
        else if(Collection.class.isAssignableFrom(fieldType))
            return typeToObjectMapperMap.get(Collection.class).convertToComponentAndValueGetter(fieldName, fieldLocalizedNameGetter, processingObject, parent);
        else if(Number[].class.isAssignableFrom(fieldType))
            return typeToObjectMapperMap.get(Number[].class).convertToComponentAndValueGetter(fieldName, fieldLocalizedNameGetter, processingObject, parent);
        else if(Number.class.isAssignableFrom(fieldType))
            return typeToObjectMapperMap.get(Number.class).convertToComponentAndValueGetter(fieldName, fieldLocalizedNameGetter, processingObject, parent);
        else throw new UnsupportedFieldException(field);
    }
}
