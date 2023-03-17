package tests;

import classesfordisplay.ClassContainsOnlyPrimitives;
import org.blbulyandavbulyan.oeadl.displayer.dialogs.ObjectDisplayerDialog;

import javax.swing.*;

public class Test2 {
    public static void main(String[] args) {
        ClassContainsOnlyPrimitives classContainsOnlyPrimitives = new ClassContainsOnlyPrimitives();
        ObjectDisplayerDialog objectDisplayerDialog = new ObjectDisplayerDialog(ClassContainsOnlyPrimitives.class, classContainsOnlyPrimitives, (key)->null);
        objectDisplayerDialog.setVisible(true);
        objectDisplayerDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
