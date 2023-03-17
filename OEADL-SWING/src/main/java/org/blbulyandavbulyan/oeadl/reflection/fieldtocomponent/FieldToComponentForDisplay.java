package org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.function.Function;

public class FieldToComponentForDisplay extends FieldToComponent{
    public FieldToComponentForDisplay(){
        //типы для которых сразу можно использовать метод toString
        Function<Object, Component> objToLabelUsingToString = o -> new JLabel(o.toString());
        for(Class<?> toStringType : toStringTypes)
            typeToObjectMapperMap.put(toStringType, objToLabelUsingToString);
        //типы данных для которых мы будем использовать класс JList для отображения(в дальнейшем можем и поменять)
        //Collection и его базовые потомки
        Function<Object, Component> iterableObjectConverter =  obj->{
            if(obj instanceof Collection){
                Collection<Object> objects = (Collection<Object>) obj;
                return new JList<>(objects.toArray());
            }
            else throw new IllegalArgumentException("argument for this function must be collection or array");
        };
        for(Class<?> iterableType : iterableTypes)
            typeToObjectMapperMap.put(iterableType, iterableObjectConverter);

        for(Class<?> wrappedArrayType : objectArrayTypes)
            typeToObjectMapperMap.put(wrappedArrayType, (o -> new JList<>((Object[])o)));
        //        Class<?> []primitiveArrayTypes = {
//                byte[].class, long[].class, int[].class, double[].class, float[].class, short[].class, char[].class, boolean[].class
//        };
//        for(Class<?> arrayType : primitiveArrayTypes){
//            fieldTypeToComponentMapperMap.put(arrayType, (obj)-> {
//
//            });
//        }
    }
    public Component convert(Field field, Object objectContainsField){
        try{
            return typeToObjectMapperMap.get(field.getType()).apply(field.get(objectContainsField));
        }
        catch (IllegalAccessException e){
            //вряд ли мы сюда попадём, т.к. мы делаем  setAccessible(true) для всех полей
            throw new RuntimeException(e);
        }
    }
}
