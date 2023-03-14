package org.blbulyandavbulyan.exceptions.invalidclass;

import org.blbulyandavbulyan.exceptions.OEADLException;

public abstract class InvalidClassException extends OEADLException {
    protected Class<?> invalidClass;
    public InvalidClassException(Class<?> c, String msg){
        super(msg);
        this.invalidClass = c;
    }
}
