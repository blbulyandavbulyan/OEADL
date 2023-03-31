package org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent;

import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UnsupportedFieldException;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.function.Function;

public class FieldToComponentForDisplay extends FieldToComponent{
    public FieldToComponentForDisplay(){
        //типы для которых сразу можно использовать метод toString
        ObjectAndItsNameToComponentConverter objToLabelUsingToString = ((field, name, obj) -> {
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel(name));
            jPanel.add(new JLabel(obj.toString()));
            return jPanel;
        });
        for(Class<?> toStringType : toStringTypes)
            typeToObjectMapperMap.put(toStringType, objToLabelUsingToString);
        //типы данных для которых мы будем использовать класс JList для отображения(в дальнейшем можем и поменять)
        //Collection и его базовые потомки
        ObjectAndItsNameToComponentConverter iterableObjectConverter =  (field, name, obj) ->{
            Object[] objects = null;
            if(obj instanceof Collection){
                objects = ((Collection<Object>) obj).toArray();

            }
            else if(obj instanceof Object[]){
                objects = (Object[]) obj;
            }
            else throw new IllegalArgumentException("argument for this function must be collection or array");
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel(name));
            jPanel.add(new JList<>(objects));
            return jPanel;
        };
        for(Class<?> iterableType : iterableTypes)
            typeToObjectMapperMap.put(iterableType, iterableObjectConverter);

        for(Class<?> wrappedArrayType : objectArrayTypes)
//            typeToObjectMapperMap.put(wrappedArrayType, ((field, name, obj) -> {
//                JList<Object> objectJList = new JList<>((Object[]) obj);
//                JPanel jPanel = new JPanel();
//                jPanel.add(new JLabel(name));
//                jPanel.add(objectJList);
//                return objectJList;
//            }));
            typeToObjectMapperMap.put(wrappedArrayType, iterableObjectConverter);
        //        Class<?> []primitiveArrayTypes = {
//                byte[].class, long[].class, int[].class, double[].class, float[].class, short[].class, char[].class, boolean[].class
//        };
//        for(Class<?> arrayType : primitiveArrayTypes){
//            fieldTypeToComponentMapperMap.put(arrayType, (obj)-> {
//
//            });
//        }
    }
}
