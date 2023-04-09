package org.blbulyandavbulyan.oeadl.factories;

import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDialog;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDisplayerDialog;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectEditorDialog;

import java.awt.*;
import java.util.ResourceBundle;

public class ObjectDisplayerDialogFactory extends ObjectDialogFactory{
    protected ObjectDisplayerDialogFactory(ResourceBundle uiTextRb) {
        super(ObjectDisplayerDialog.class, uiTextRb);
    }
    public ObjectDisplayerDialogFactory() {
        super(ObjectDisplayerDialog.class);
    }

    @Override
    public ObjectDisplayerDialog generateObjectDialog(Window parent, Object processingObject) {
        return (ObjectDisplayerDialog) super.generateObjectDialog(parent, processingObject);
    }
}
