package org.blbulyandavbulyan.oeadl.exceptions.invalidtype;

import org.blbulyandavbulyan.oeadl.exceptions.OEADLException;

public abstract class InvalidTypeException extends OEADLException {
    public InvalidTypeException(String msg) {
        super(msg);
    }
}
