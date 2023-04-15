
package org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;
import org.blbulyandavbulyan.oeadl.exceptions.invalidclass.UnsupportedClassException;
import org.blbulyandavbulyan.oeadl.gui.dialogs.dialogvaluegetter.DialogValueGetter;
import org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel.FieldPanel;
import org.blbulyandavbulyan.oeadl.interfaces.GenerateObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GetResourceBundleByClass;
import org.blbulyandavbulyan.oeadl.namegetter.GetOrDefault;
import org.blbulyandavbulyan.oeadl.reflection.ProcessingClass;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * Этот класс является абстракцией для классов работы с объектами моей библиотеки.
 * @author David Blbulyan
 * @since 1.0.0
 * @version 1.0.2
 * */
public abstract class ObjectDialog extends DialogValueGetter {
    /**
     * Коллекция, содержащая поля для обработки моей библиотекой (отображение или редактирование)
     * <br>
     * This collection contains fields for processing in my library (displaying or editing)
     * */
    protected Collection<Field> fieldsForProcessing;
    /**
     * Объект, который будет использоваться для отображения или редактирования
     * <br>
     * This object uses for displaying or editing
     * @since 1.0.1
     * */
    protected Object processingObject;
    protected Class<?> objectClass;
    protected JPanel rootPanel;
    protected Collection<FieldPanel> fieldPanels;
    protected ResourceBundle objectClassResourceBundle;
    protected ResourceBundle uiResourceBundle;
    protected GenerateObjectDialog objectDialogFactory;
    protected ObjectDialog(Window parent, Object processingObject, ResourceBundle uiResourceBundle, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass){
        super(parent);
        this.objectDialogFactory = generateObjectDialog;
        this.uiResourceBundle = uiResourceBundle;
        this.objectClassResourceBundle = getResourceBundleByClass.getResourceBundleForClass(processingObject.getClass());
        if(!processingObject.getClass().isAnnotationPresent(OEADLProcessingClass.class))throw new UnsupportedClassException(processingObject.getClass());
        fieldPanels = new LinkedList<>();
        String dialogTitlePropertyKey = GetOrDefault.getStringOrDefault(processingObject.getClass().getAnnotation(OEADLProcessingClass.class).localizedClassNamePropertyKey(), processingObject.getClass().getName());
        String dialogTitle = GetOrDefault.getFromRbOrDefault(
                objectClassResourceBundle,
                dialogTitlePropertyKey,
                processingObject.getClass().getName()
        );
        this.setTitle(dialogTitle);
        this.objectClass = processingObject.getClass();
        rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        this.processingObject = processingObject;
        fieldsForProcessing = new ArrayList<>(ProcessingClass.getAllAnnotatedFieldsInOEADLProcessingClass(objectClass, OEADLField.class));
        this.getContentPane().add(rootPanel);
    }
    public Object getValue(){
        return processingObject;
    }
}
