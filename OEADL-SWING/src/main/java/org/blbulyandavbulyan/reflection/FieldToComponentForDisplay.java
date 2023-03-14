package org.blbulyandavbulyan.reflection;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FieldToComponentForDisplay implements FieldToComponent{
    protected Map<Class<?>, Function<Object, Component>> fieldTypeToComponentMapperMap = new HashMap<>();
    public FieldToComponentForDisplay(){
        String s = "dd";
        Class<?> []toStringTypes = {
                int.class, long.class, short.class, float.class, double.class, byte.class, boolean.class, char.class,
                Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class,
                String.class
        };
        //типы для которых сразу можно использовать метод toString
        Function<Object, Component> objToLabelUsingToString = o -> new JLabel(o.toString());
        for(Class<?> toStringType : toStringTypes)
            fieldTypeToComponentMapperMap.put(toStringType, objToLabelUsingToString);
        //типы данных для которых мы будем использовать класс JList для отображения(в дальнейшем можем и поменять)
        //Collection и его базовые потомки
        Class<?> []iterableTypes = {
                java.util.List.class, java.util.ArrayList.class, java.util.Collection.class, java.util.LinkedList.class
        };
        Function<Object, Component> iterableObjectConverter =  obj->{
            if(obj instanceof Collection){
                Collection<Object> objects = (Collection<Object>) obj;
                return new JList<>(objects.toArray());
            }
            else throw new IllegalArgumentException("argument for this function must be collection or array");
        };
        for(Class<?> iterableType : iterableTypes)
            fieldTypeToComponentMapperMap.put(iterableType, iterableObjectConverter);
        Class<?> []basicObjectTypes = {
                Byte[].class, Long[].class, Integer[].class, Double[].class, Float[].class, Short[].class, Character[].class, Boolean[].class, String[].class
        };
        for(Class<?> wrappedArrayType : basicObjectTypes)
            fieldTypeToComponentMapperMap.put(wrappedArrayType, (o -> new JList<>((Object[])o)));
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
            return fieldTypeToComponentMapperMap.get(field.getType()).apply(field.get(objectContainsField));
        }
        catch (IllegalAccessException e){
            //вряд ли мы сюда попадём, т.к. мы делаем  setAccessible(true) для всех полей
            throw new RuntimeException(e);
        }
    }
    public boolean canConvert(Field field){
        return fieldTypeToComponentMapperMap.containsKey(field.getType());
    }
}
