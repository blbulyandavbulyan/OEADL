package tests.displaytest;

import classesfordisplay.ClassContainsOnlyWrappers;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDisplayerDialog;

import javax.swing.*;

public class Test3 {
    public static void main(String[] args) {
        ClassContainsOnlyWrappers classContainsOnlyWrappers = new ClassContainsOnlyWrappers();
        ObjectDisplayerDialog objectDisplayerDialog = new ObjectDisplayerDialog(classContainsOnlyWrappers, (key)->null);
        objectDisplayerDialog.setVisible(true);
        objectDisplayerDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}