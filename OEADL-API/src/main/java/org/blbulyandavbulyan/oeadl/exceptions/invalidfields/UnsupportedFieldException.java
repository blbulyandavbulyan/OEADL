package org.blbulyandavbulyan.oeadl.exceptions.invalidfields;

import java.lang.reflect.Field;

public class UnsupportedFieldException extends InvalidFieldException{
    public UnsupportedFieldException(Field field) {
        super(field, String.format("Unsupported field %s in class %s, the field was given as a parameter", field.getName(), field.getDeclaringClass().getName()));
    }
}
