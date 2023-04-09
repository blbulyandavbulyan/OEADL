package org.blbulyandavbulyan.oeadl.factories;

import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDialog;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDisplayerDialog;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectEditorDialog;

import java.awt.*;
import java.util.ResourceBundle;

public class ObjectEditorDialogFactory extends ObjectDialogFactory{
    public ObjectEditorDialogFactory(ResourceBundle uiTextRb) {
        super(ObjectEditorDialog.class, uiTextRb);
    }
    public ObjectEditorDialogFactory(){
        super(ObjectEditorDialog.class);
    }
    @Override
    public ObjectEditorDialog generateObjectDialog(Window parent, Object processingObject) {
        return (ObjectEditorDialog) super.generateObjectDialog(parent, processingObject);
    }
}
