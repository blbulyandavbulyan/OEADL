package tests.editinganddisplaying;

import entities.Group;
import entities.User;
import org.blbulyandavbulyan.oeadl.factories.ObjectDisplayerDialogFactory;
import org.blbulyandavbulyan.oeadl.factories.ObjectEditorDialogFactory;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDisplayerDialog;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectEditorDialog;

import java.util.ResourceBundle;

public class TestUserClass {
    public static void main(String[] args) {
        ObjectEditorDialogFactory objectEditorDialogFactory = new ObjectEditorDialogFactory();
        objectEditorDialogFactory.addOrReplaceResourceBundle(User.class, ResourceBundle.getBundle("userclasstext"));
        objectEditorDialogFactory.addOrReplaceResourceBundle(Group.class, ResourceBundle.getBundle("groupguitext"));
        User user = new User(1L, "test", "test@gmail.com", new Group(1L, 2, "users", false));
        ObjectEditorDialog objectEditorDialog = objectEditorDialogFactory.generateObjectDialog(null, user);
        objectEditorDialog.setModal(true);
        objectEditorDialog.setVisible(true);
        ObjectDisplayerDialogFactory objectDisplayerDialogFactory = new ObjectDisplayerDialogFactory();
        objectDisplayerDialogFactory.addOrReplaceResourceBundle(User.class, ResourceBundle.getBundle("userclasstext"));
        ObjectDisplayerDialog objectDisplayerDialog = objectDisplayerDialogFactory.generateObjectDialog(null, user);
        objectDisplayerDialog.setVisible(true);
    }
}
