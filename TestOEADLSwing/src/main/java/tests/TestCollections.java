package tests;

import entities.ClassContainsCollection;
import entities.Group;
import entities.User;
import org.blbulyandavbulyan.oeadl.factories.ObjectDisplayerDialogFactory;
import org.blbulyandavbulyan.oeadl.factories.ObjectEditorDialogFactory;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDisplayerDialog;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectEditorDialog;

import java.util.ResourceBundle;

public class TestCollections {
    public static void main(String[] args) {
        ClassContainsCollection classContainsCollection = new ClassContainsCollection();
        ObjectEditorDialogFactory objectEditorDialogFactory = new ObjectEditorDialogFactory();
        objectEditorDialogFactory.addOrReplaceResourceBundle(User.class, ResourceBundle.getBundle("userclasstext"));
        objectEditorDialogFactory.addOrReplaceResourceBundle(Group.class, ResourceBundle.getBundle("groupguitext"));
        ObjectEditorDialog objectEditorDialog = objectEditorDialogFactory.generateObjectDialog(null, classContainsCollection);
        objectEditorDialog.setModal(true);
        objectEditorDialog.setVisible(true);
        ObjectDisplayerDialogFactory objectDisplayerDialogFactory = new ObjectDisplayerDialogFactory();
        objectDisplayerDialogFactory.addOrReplaceResourceBundle(User.class, ResourceBundle.getBundle("userclasstext"));
        objectEditorDialogFactory.addOrReplaceResourceBundle(Group.class, ResourceBundle.getBundle("groupguitext"));
        ObjectDisplayerDialog objectDisplayerDialog = objectDisplayerDialogFactory.generateObjectDialog(null, classContainsCollection);
        objectDisplayerDialog.setVisible(true);
    }
}
