package org.blbulyandavbulyan.oeadl.exceptions.invalidfields;

import org.blbulyandavbulyan.oeadl.exceptions.OEADLException;

import java.lang.reflect.Field;

public abstract class InvalidFieldException extends OEADLException {
    private final Field field;
    protected InvalidFieldException(Field field, String msg){
        super(msg);
        this.field = field;
    }

    public Field getField() {
        return field;
    }
}