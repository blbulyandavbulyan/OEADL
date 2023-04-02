package tests.displaytest;

import classesfordisplay.ClassUsesPrimitiveArrays;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDisplayerDialog;

import javax.swing.*;

public class Test4 {
    public static void main(String[] args) {
        ClassUsesPrimitiveArrays classContainsOnlyWrappers = new ClassUsesPrimitiveArrays();
        ObjectDisplayerDialog objectDisplayerDialog = new ObjectDisplayerDialog(classContainsOnlyWrappers, (key)->null);
        objectDisplayerDialog.setVisible(true);
        objectDisplayerDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
