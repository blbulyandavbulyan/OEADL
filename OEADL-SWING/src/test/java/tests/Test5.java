package tests;

import classesfordisplay.ClassUsesWrappedArrays;
import org.blbulyandavbulyan.oeadl.displayer.dialogs.ObjectDisplayerDialog;

import javax.swing.*;

public class Test5 {
    public static void main(String[] args) {
        ClassUsesWrappedArrays classContainsOnlyWrappers = new ClassUsesWrappedArrays();
        ObjectDisplayerDialog objectDisplayerDialog = new ObjectDisplayerDialog(classContainsOnlyWrappers, (key)->null);
        objectDisplayerDialog.setVisible(true);
        objectDisplayerDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
