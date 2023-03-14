package org.blbulyandavbulyan.exceptions.invalidfields;

import java.lang.reflect.Field;

public class UndisplayableFieldException extends InvalidFieldException{
    public UndisplayableFieldException(Field field) {
        super(field, String.format("Undisplayable field %s in class %s", field.getType().getName(), field.getDeclaringClass().getName()));
    }
}
