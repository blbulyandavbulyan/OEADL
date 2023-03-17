package org.blbulyandavbulyan.oeadl.exceptions.invalidclass;

import org.blbulyandavbulyan.oeadl.exceptions.OEADLException;

public abstract class InvalidClassException extends OEADLException {
    protected Class<?> invalidClass;
    public InvalidClassException(Class<?> c, String msg){
        super(msg);
        this.invalidClass = c;
    }
}
