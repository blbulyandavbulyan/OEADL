package org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent;

import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UnsupportedFieldException;
import org.blbulyandavbulyan.oeadl.exceptions.invalidfields.UnsupportedFieldTypeException;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDialog;
import org.blbulyandavbulyan.oeadl.gui.panels.exceptions.FieldPanelWithObjectDialogCreationException;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class FieldToComponent {
    //типы для которых гарантированно применение toString()


    protected static final Class<?> []iterableTypes = {
            Collection.class
    };
    protected static final Class<?> [] objectArrayTypes = {
            Byte[].class, Long[].class, Integer[].class, Double[].class, Float[].class, Short[].class, Character[].class, Boolean[].class, String[].class
    };
    protected Map<Class<?>, FieldAndObjectAndTheirNameToComponentConverter> typeToObjectMapperMap;
    protected FieldToComponent(){
        typeToObjectMapperMap = new HashMap<>();
    }
    public boolean canConvert(Field field){
        return typeToObjectMapperMap.containsKey(field.getType());
    }
    public void removeConverter(Field field){
        typeToObjectMapperMap.remove(field.getType());
    }
    public FieldComponent convert(Field field, String displayingName, Object objectContainingField) {
        Class<?> fieldType = field.getType();
        if(canConvert(field)) return typeToObjectMapperMap.get(fieldType).convertToComponent(field, displayingName, objectContainingField);
        else if(Enum.class.isAssignableFrom(fieldType))
            return typeToObjectMapperMap.get(Enum.class).convertToComponent(field, displayingName, objectContainingField);
        else if(Collection.class.isAssignableFrom(fieldType))
            return typeToObjectMapperMap.get(Collection.class).convertToComponent(field, displayingName, objectContainingField);
        else throw new UnsupportedFieldException(field);
    }
}
