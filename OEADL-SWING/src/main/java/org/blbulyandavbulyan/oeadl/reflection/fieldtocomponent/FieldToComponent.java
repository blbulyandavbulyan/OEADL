package org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent;

import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UnsupportedFieldException;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class FieldToComponent {
    //типы для которых гарантированно применение toString()
    protected static final Class<?> []toStringTypes = {
            int.class, long.class, short.class, float.class, double.class, byte.class, boolean.class, char.class,
            Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class,
            String.class
    };

    protected static final Class<?> []iterableTypes = {
            java.util.List.class, java.util.ArrayList.class, java.util.Collection.class, java.util.LinkedList.class
    };
    protected static final Class<?> [] objectArrayTypes = {
            Byte[].class, Long[].class, Integer[].class, Double[].class, Float[].class, Short[].class, Character[].class, Boolean[].class, String[].class
    };
    protected Map<Class<?>, ObjectAndItsNameToComponentConverter> typeToObjectMapperMap;
    protected FieldToComponent(){
        typeToObjectMapperMap = new HashMap<>();
    }
    public boolean canConvert(Field field){
        return typeToObjectMapperMap.containsKey(field.getType());
    }
    public void removeConverter(Field field){
        typeToObjectMapperMap.remove(field.getType());
    }
    public Component convert(Field field, String displayingName, Object objectContainingField) {
        if(canConvert(field)) return typeToObjectMapperMap.get(field.getType()).convert(field, displayingName, objectContainingField);
        else throw new UnsupportedFieldException(field);
    }
}
