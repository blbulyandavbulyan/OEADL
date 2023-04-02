package tests.displaytest;

import classesfordisplay.ClassContainsOnlyPrimitives;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDisplayerDialog;

import javax.swing.*;

public class Test2 {
    public static void main(String[] args) {
        ClassContainsOnlyPrimitives classContainsOnlyPrimitives = new ClassContainsOnlyPrimitives();
        ObjectDisplayerDialog objectDisplayerDialog = new ObjectDisplayerDialog(classContainsOnlyPrimitives, (key)->null);
        objectDisplayerDialog.setVisible(true);
        objectDisplayerDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
