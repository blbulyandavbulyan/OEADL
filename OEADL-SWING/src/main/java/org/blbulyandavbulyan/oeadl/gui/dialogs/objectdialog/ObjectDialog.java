
package org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;
import org.blbulyandavbulyan.oeadl.exceptions.invalidclass.UnsupportedClassException;
import org.blbulyandavbulyan.oeadl.gui.interfaces.GetValue;
import org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel.FieldPanel;
import org.blbulyandavbulyan.oeadl.reflection.ProcessingClass;
import org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent.FieldComponent;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Function;

public abstract class ObjectDialog extends JDialog implements GetValue {
    /**
     * Коллекция, содержащая поля для обработки моей библиотекой (отображение или редактирование)
     * <br>
     * This collection contains fields for processing in my library (displaying or editing)
     * */
    protected Collection<Field> fieldsForProcessing;
    /**
     * Функция конвертор, которая позволяет получить локализованное имя для поля или класса<br>
     * This function is a convertor, it allows to get localized name for the field or class
     * @since 1.0.0
     * */
    protected Function<String, String> localizedNameGetter;
    /**
     * Объект, который будет использоваться для отображения или редактирования
     * <br>
     * This object uses for displaying or editing
     * @since 1.0.1
     * */
    protected Object processingObject;
    protected Class<?> objectClass;
    /**
     * Контейнер, который является основным для данного диалога или его наследников
     * <br>
     * This container is main for this dialog or its inheritors
     * @since 1.0.0
     * */
    protected JPanel rootPanel;
    protected Collection<FieldPanel> fieldPanels;
    /**
     * @param parent Родительское окно для данного диалога.<br> The parent window for this dialog. {@link Dialog#Dialog(Window)}
     * @param processingObject Объект, который является экземпляром класса objectClass и будет использоваться для отображения или редактирования.<br>
     *                         The object, that is instance of the objectClass and will use for displaying or editing.
     * @param localizedNameGetter {@link ObjectDialog#localizedNameGetter}
     * */
    protected ObjectDialog(Window parent, Object processingObject, Function<String, String> localizedNameGetter){
        super(parent);
        if(!processingObject.getClass().isAnnotationPresent(OEADLProcessingClass.class))throw new UnsupportedClassException(processingObject.getClass());
        fieldPanels = new LinkedList<>();
        String dialogTitle = localizedNameGetter.apply(processingObject.getClass().getAnnotation(OEADLProcessingClass.class).localizedClassNamePropertyKey());
        if(dialogTitle == null || dialogTitle.isBlank())dialogTitle = processingObject.getClass().getName();
        this.setTitle(dialogTitle);
        this.objectClass = processingObject.getClass();
        rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        this.localizedNameGetter = localizedNameGetter;
        this.processingObject = processingObject;
        fieldsForProcessing = new ArrayList<>(ProcessingClass.getAllAnnotatedFieldsInOEADLProcessingClass(objectClass, OEADLField.class));
        this.getContentPane().add(rootPanel);
    }
//    protected void addFieldPanel(Field field)
    public Object getValue(){
        return processingObject;
    }
}
