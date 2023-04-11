package tests.displaying;

import entities.ClassContainsCollection;
import entities.Group;
import entities.User;
import org.blbulyandavbulyan.oeadl.factories.ObjectDisplayerDialogFactory;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDisplayerDialog;

import java.util.ResourceBundle;

public class TestDisplaying {
    public static void main(String[] args) {
        ClassContainsCollection classContainsCollection = new ClassContainsCollection();
        ObjectDisplayerDialogFactory objectDisplayerDialogFactory = new ObjectDisplayerDialogFactory();
        objectDisplayerDialogFactory.addOrReplaceResourceBundle(User.class, ResourceBundle.getBundle("userclasstext"));
        ObjectDisplayerDialog objectDisplayerDialog = objectDisplayerDialogFactory.generateObjectDialog(null, classContainsCollection);
        objectDisplayerDialog.setVisible(true);
    }
}
