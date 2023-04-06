package org.blbulyandavbulyan.oeadl.reflection;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;

import java.lang.reflect.Field;
import java.util.function.Function;

public class ProcessingField {
    public static String getLocalizedFieldNameOrGetFieldName(Function<String, String> localizedFieldNameGetter, Field field){
        String fieldName = localizedFieldNameGetter.apply(field.getAnnotation(OEADLField.class).localizedNamePropertyKey());
        if(fieldName == null || fieldName.isBlank())fieldName = field.getName();
        return fieldName;
    }
}
