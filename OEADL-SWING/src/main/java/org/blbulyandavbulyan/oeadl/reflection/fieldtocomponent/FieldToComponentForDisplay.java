package org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent;

import javax.swing.*;
import java.util.Collection;

public class FieldToComponentForDisplay extends FieldToComponent{
    protected static final Class<?> []toStringTypes = {
            int.class, long.class, short.class, float.class, double.class, byte.class, boolean.class, char.class,
            Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class,
            String.class, Enum.class
    };
    public FieldToComponentForDisplay(){
        //типы для которых сразу можно использовать метод toString
        FieldAndObjectAndTheirNameToComponentConverter objToLabelUsingToString = ((field, name, obj) -> {
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel(name));
            jPanel.add(new JLabel(obj.toString()));
            return new FieldComponent(jPanel, ()-> obj);
        });

        for(Class<?> toStringType : toStringTypes)
            typeToObjectMapperMap.put(toStringType, objToLabelUsingToString);
        //типы данных для которых мы будем использовать класс JList для отображения(в дальнейшем можем и поменять)
        //Collection и его базовые потомки
        FieldAndObjectAndTheirNameToComponentConverter iterableObjectConverter =  (field, name, obj) ->{
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
            return new FieldComponent(jPanel, ()->obj);
        };
        for(Class<?> iterableType : iterableTypes)
            typeToObjectMapperMap.put(iterableType, iterableObjectConverter);
        for(Class<?> wrappedArrayType : objectArrayTypes)
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
