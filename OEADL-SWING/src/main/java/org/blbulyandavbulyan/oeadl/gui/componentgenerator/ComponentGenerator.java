package org.blbulyandavbulyan.oeadl.gui.componentgenerator;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UnsupportedFieldException;
import org.blbulyandavbulyan.oeadl.exceptions.componentgenerator.ClassConvertorAlreadyExistsException;
import org.blbulyandavbulyan.oeadl.exceptions.componentgenerator.ConvertorForClassDoesntExists;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GenerateObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GetResourceBundleByClass;
import org.blbulyandavbulyan.oeadl.namegetter.GetNameOrDefault;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;

public abstract class ComponentGenerator {
    //типы для которых гарантированно применение toString()
    protected static final Class<?> []iterableTypes = {
            Collection.class
    };
    protected static final Class<?> [] objectArrayTypesWhereIsPossibleToStringForElement = {
            Number[].class, Character[].class, String[].class, Boolean[].class
    };

    protected Map<Class<?>, ObjectToComponentAndValueGetterConverter> typeToObjectMapperMap;
    protected ComponentGenerator(){
        typeToObjectMapperMap = new HashMap<>();
    }
    protected boolean canConvert(Field field){
        return typeToObjectMapperMap.containsKey(field.getType());
    }
    protected boolean canConvert(Class<?> c){
        return typeToObjectMapperMap.containsKey(c);
    }
    public void removeConverter(Field field){
        typeToObjectMapperMap.remove(field.getType());
    }
    public void removeConverter(Class<?> classForConverting){
        typeToObjectMapperMap.remove(classForConverting);
    }
    public void addConverter(Class<?> classForConverting, ObjectToComponentAndValueGetterConverter converter){
        if(!typeToObjectMapperMap.containsKey(classForConverting)){
            typeToObjectMapperMap.put(classForConverting, converter);
        }
        else throw new ClassConvertorAlreadyExistsException(classForConverting);
    }
    public void replaceConverter(Class<?> classForConverting, ObjectToComponentAndValueGetterConverter converter){
        if(typeToObjectMapperMap.containsKey(classForConverting))
            typeToObjectMapperMap.replace(classForConverting, converter);
        else throw new ConvertorForClassDoesntExists(classForConverting);
    }
    public ComponentAndValueGetter generateFieldComponent(ObjectDialog parent, Field field, Object processingObject, String showObjectDialogButtonText, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass, ResourceBundle uiResourceBundle){
        Class<?> fieldType = field.getType();
        String fieldName = GetNameOrDefault.getNameOrDefault(
                getResourceBundleByClass.getResourceBundleForClass(fieldType), field.getAnnotation(OEADLField.class).localizedNamePropertyKey(), field.getName()
        );
        if(canConvert(field))
            return typeToObjectMapperMap.get(fieldType).convertToComponentAndValueGetter(fieldName, processingObject, parent, uiResourceBundle, generateObjectDialog, getResourceBundleByClass);
        else if(fieldType.isAnnotationPresent(OEADLProcessingClass.class)){
//            try{
                ObjectDialog objectDialog = generateObjectDialog.generateObjectDialog(parent, processingObject);
                JButton jWatchObject = new JButton(showObjectDialogButtonText);
                jWatchObject.addActionListener(l-> objectDialog.setVisible(!objectDialog.isVisible()));
                JPanel jPanel = new JPanel();
                jPanel.add(new JLabel(fieldName));
                jPanel.add(jWatchObject);
                return new ComponentAndValueGetter(jPanel, objectDialog);
//            }
//            catch(NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
//                throw new FieldPanelWithObjectDialogCreationException(e, objectDialogClass);
//            }
        }
        else{
            //нашего типа поля нет в списке доступных и он не помечен аннотацией OEADLProcessingClass, значит попробуем найти его родителя в списке доступных типов
            for(Class<?> currentClass = fieldType; currentClass!= Object.class; currentClass = currentClass.getSuperclass()){
                if(canConvert(currentClass)){
                    return typeToObjectMapperMap.get(currentClass).convertToComponentAndValueGetter(fieldName, processingObject, parent, uiResourceBundle, generateObjectDialog, getResourceBundleByClass);
                }
            }
            //бросаем исключения если ни один родитель нам не подошёл
            throw new UnsupportedFieldException(field);
        }
    }
}
