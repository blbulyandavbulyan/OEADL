package org.blbulyandavbulyan.oeadl.displayer.panels.exceptions;

import org.blbulyandavbulyan.oeadl.exceptions.OEADLException;

public abstract class FieldPanelException extends OEADLException {
    public FieldPanelException(Throwable cause, String message){
        super(cause, message);
    }
}
