package org.blbulyandavbulyan.displayer.panels;

import org.blbulyandavbulyan.annotations.Displayable;
import org.blbulyandavbulyan.annotations.OEADLProcessingClass;
import org.blbulyandavbulyan.displayer.windows.ObjectDisplayerWindow;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.Function;

public class FieldDisplayPanel extends JPanel {
    protected static Map<Class<?>, Function<Object, Component>> fieldTypeToComponentMapperMap = new HashMap<>();
    static {
        //конвертор для примитивных типов
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
                java.util.List.class, java.util.ArrayList.class, java.util.Collection.class, java.util.LinkedList.class, String[].class
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
//        Class<?> []primitiveArrayTypes = {
//                byte[].class, long[].class, int[].class, double[].class, float[].class, short[].class, char[].class, boolean[].class
//        };
//        for(Class<?> arrayType : primitiveArrayTypes){
//            fieldTypeToComponentMapperMap.put(arrayType, (obj)-> {
//
//            });
//        }
        Class<?> []wrappedArrayTypes = {
                Byte[].class, Long[].class, Integer[].class, Double[].class, Float[].class, Short[].class, Character[].class, Boolean[].class
        };
        for(Class<?> wrappedArrayType : wrappedArrayTypes)
            fieldTypeToComponentMapperMap.put(wrappedArrayType, (o -> new JList<>((Object[])o)));


    }
    protected JLabel jFieldNameLabel;
    protected Component fieldDisplayComponent;
    public FieldDisplayPanel(Window parent, Field field, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter) throws IllegalAccessException {
        String fieldName = fieldLocalizedNameGetter.apply(field.getAnnotation(Displayable.class).localizedNamePropertyKey());
        if(fieldName == null)fieldName = field.getName();
        this.jFieldNameLabel = new JLabel(fieldName);
        this.fieldDisplayComponent = getFieldDisplayComponent(parent, field, objectForDisplay, fieldLocalizedNameGetter);
        this.add(jFieldNameLabel);
        this.add(fieldDisplayComponent);
    }
    private static Component getFieldDisplayComponent(Window parent, Field field, Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter) throws IllegalAccessException {
        Class<?> fieldType = field.getType();
        if(fieldType.isAnnotationPresent(OEADLProcessingClass.class)){
            JButton jWatchObject = new JButton("Просмотреть детали");
            ObjectDisplayerWindow objectDisplayerWindow = new ObjectDisplayerWindow(parent, field.getType(), field.get(objectForDisplay), fieldLocalizedNameGetter);
            jWatchObject.addActionListener(l->{
                objectDisplayerWindow.setVisible(!objectDisplayerWindow.isVisible());
            });
            return jWatchObject;
        }
        else{
            //если в нашем map есть соответсвующий маппер для объекта данного типа, то получаем и применяем его
            if(fieldTypeToComponentMapperMap.containsKey(fieldType)){
                return fieldTypeToComponentMapperMap.get(fieldType).apply(field.get(objectForDisplay));
            }
            //else if(field.getAnnotation(Displayable.class))
        }
        throw new IllegalArgumentException();
    }
}
