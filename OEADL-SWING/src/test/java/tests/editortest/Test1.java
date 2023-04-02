
package tests.editortest;

import classesfordisplay.Group;
import classesfordisplay.User;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDisplayerDialog;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectEditorDialog;

import java.util.function.Function;

public class Test1 {
    public static void main(String[] args) {
        User user = new User(1L, "david", "david@gmail.com", new Group(1L, 2, "admins", false));
        ObjectEditorDialog objectEditorDialog = new ObjectEditorDialog(user, s -> null);
        objectEditorDialog.setModal(true);
        objectEditorDialog.setVisible(true);
        ObjectDisplayerDialog objectDisplayerDialog = new ObjectDisplayerDialog(user, s -> null);
        objectDisplayerDialog.setVisible(true);
    }
}
