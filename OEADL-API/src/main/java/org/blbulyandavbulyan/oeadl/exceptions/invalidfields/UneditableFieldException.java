package org.blbulyandavbulyan.oeadl.exceptions.invalidfields;

import java.lang.reflect.Field;

public class UneditableFieldException extends InvalidFieldException{
    public UneditableFieldException(Field field) {
        super(field, String.format("Uneditable field %s in class %s", field.getName(), field.getDeclaringClass().getName()));
    }
}
