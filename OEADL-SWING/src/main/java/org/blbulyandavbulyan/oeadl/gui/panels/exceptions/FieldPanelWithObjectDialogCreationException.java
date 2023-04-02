package org.blbulyandavbulyan.oeadl.gui.panels.exceptions;

public class FieldPanelWithObjectDialogCreationException extends FieldPanelException{
    public FieldPanelWithObjectDialogCreationException(Throwable cause, Class<?> causeClass){
        super(cause, String.format("Error creating ObjectDialog using child class %s, details: %s", causeClass.getName(), cause.getMessage()));
    }
}
