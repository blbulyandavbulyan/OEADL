package tests.editortest;
import classesfordisplay.ClassContainsEnum;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDisplayerDialog;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectEditorDialog;

public class EnumTest {
    public static void main(String[] args) {
        ClassContainsEnum classContainsEnum = new ClassContainsEnum(ClassContainsEnum.Type.TYPE1, "david");
        ObjectEditorDialog objectEditorDialog = new ObjectEditorDialog(classContainsEnum, (s) -> null);
        objectEditorDialog.setModal(true);
        objectEditorDialog.setVisible(true);
        ObjectDisplayerDialog objectDisplayerDialog = new ObjectDisplayerDialog(classContainsEnum, (s) -> null);
        objectDisplayerDialog.setVisible(true);
    }
}
