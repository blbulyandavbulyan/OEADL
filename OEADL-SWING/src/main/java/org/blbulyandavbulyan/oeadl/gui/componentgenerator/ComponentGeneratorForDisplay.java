package org.blbulyandavbulyan.oeadl.gui.componentgenerator;

import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GenerateObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GetResourceBundleByClass;

import javax.swing.*;
import java.util.Collection;
import java.util.ResourceBundle;

public class ComponentGeneratorForDisplay extends ComponentGenerator {
    protected static final Class<?> []toStringTypes = {
            int.class, long.class, short.class, float.class, double.class, byte.class, boolean.class, char.class, Number.class, String.class, Enum.class
    };
    public ComponentGeneratorForDisplay(){
        //типы для которых сразу можно использовать метод toString
        ObjectToComponentAndValueGetterConverter objToLabelUsingToString = (String objectDisplayableName, Object obj, ObjectDialog parent, ResourceBundle uiResourceBundle, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass) -> {
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel(objectDisplayableName));
            jPanel.add(new JLabel(obj.toString()));
            return new ComponentAndValueGetter(jPanel, ()-> obj);
        };

        for(Class<?> toStringType : toStringTypes)
            typeToObjectMapperMap.put(toStringType, objToLabelUsingToString);
        //типы данных для которых мы будем использовать класс JList для отображения(в дальнейшем можем и поменять)
        //Collection и его базовые потомки
        ObjectToComponentAndValueGetterConverter iterableObjectConverter =  (String objectDisplayableName, Object obj, ObjectDialog parent, ResourceBundle uiResourceBundle, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass) ->{
            Object[] objects = null;
            if(obj instanceof Collection){
                objects = ((Collection<Object>) obj).toArray();
            }
            else if(obj instanceof Object[]){
                objects = (Object[]) obj;
            }
            else throw new IllegalArgumentException("argument for this function must be collection or array");
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel(objectDisplayableName));
            jPanel.add(new JList<>(objects));
            return new ComponentAndValueGetter(jPanel, ()->obj);
        };
        for(Class<?> iterableType : iterableTypes)
            typeToObjectMapperMap.put(iterableType, iterableObjectConverter);
        for(Class<?> wrappedArrayType : objectArrayTypesWhereIsPossibleToStringForElement)
            typeToObjectMapperMap.put(wrappedArrayType, iterableObjectConverter);
    }
}
