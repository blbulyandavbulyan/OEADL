package tests;

import classesfordisplay.ClassUsesPrimitiveArrays;
import org.blbulyandavbulyan.oeadl.displayer.dialogs.ObjectDisplayerDialog;

import javax.swing.*;

public class Test4 {
    public static void main(String[] args) {
        ClassUsesPrimitiveArrays classContainsOnlyWrappers = new ClassUsesPrimitiveArrays();
        ObjectDisplayerDialog objectDisplayerDialog = new ObjectDisplayerDialog(ClassUsesPrimitiveArrays.class, classContainsOnlyWrappers, (key)->null);
        objectDisplayerDialog.setVisible(true);
        objectDisplayerDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
