package tests.displaytest;

import classesfordisplay.ClassContainsCollection;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDisplayerDialog;

import java.util.LinkedList;

public class TestCollections {
    public static void main(String[] args) {
        LinkedList<Integer> testCollection = new LinkedList<>();
        testCollection.add(123);
        testCollection.add(22);
        testCollection.add(13);
        ClassContainsCollection classContainsCollection = new ClassContainsCollection(testCollection);
        ObjectDisplayerDialog objectDisplayerDialog = new ObjectDisplayerDialog(classContainsCollection, (k)->null);
        objectDisplayerDialog.setVisible(true);
    }
}
