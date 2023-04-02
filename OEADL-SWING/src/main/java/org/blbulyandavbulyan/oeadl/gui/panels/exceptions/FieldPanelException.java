package org.blbulyandavbulyan.oeadl.gui.panels.exceptions;

import org.blbulyandavbulyan.oeadl.exceptions.OEADLException;

public abstract class FieldPanelException extends OEADLException {
    public FieldPanelException(Throwable cause, String message){
        super(cause, message);
    }

    public FieldPanelException(String msg) {
        super(msg);
    }
}
