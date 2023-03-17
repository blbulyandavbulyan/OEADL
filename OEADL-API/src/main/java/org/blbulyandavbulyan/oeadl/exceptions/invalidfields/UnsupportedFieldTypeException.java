package org.blbulyandavbulyan.oeadl.exceptions.invalidfields;

import java.lang.reflect.Field;

public class UnsupportedFieldTypeException extends InvalidFieldException{
    protected final Field field;
    public UnsupportedFieldTypeException(Field field){
        super(field, String.format("Unsupported field type %s in class %s", field.getType().getName(), field.getDeclaringClass().getName()));
        this.field = field;
    }

    public Field getField() {
        return field;
    }
    public Class<?> getFieldType(){
        return field.getType();
    }
}
