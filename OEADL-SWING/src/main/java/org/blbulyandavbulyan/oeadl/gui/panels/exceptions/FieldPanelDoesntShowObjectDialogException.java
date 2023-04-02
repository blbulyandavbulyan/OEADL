package org.blbulyandavbulyan.oeadl.gui.panels.exceptions;

public class FieldPanelDoesntShowObjectDialogException extends FieldPanelException{
    public FieldPanelDoesntShowObjectDialogException() {
        super("Field panel doesn't show object dialog, you can't call getObjectDialog function for this object");
    }
}
