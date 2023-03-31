package org.blbulyandavbulyan.oeadl.displayer.dialogs;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.displayer.panels.fieldpanel.FieldEditPanel;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
/**
 * Этот класс предназначен для отображения объектов с возможностью редактирования
 * <br>
 * This class is for displaying and editing objects
 * @author David Blbulyan
 * @version 1.0.0
 * @since 1.0.0
 * */
public class ObjectEditorDialog extends ObjectDialog {
    protected Collection<Field> fieldsForEdit;
    /**
     * @see ObjectDialog#ObjectDialog(Window, Object, Function) ObjectDialog(parent, objectClass, processingObject, fieldLocalizedNameGetter)
     * */
    protected ObjectEditorDialog(Window parent, Object objectForEditing, Function<String, String> fieldLocalizedNameGetter) {
        super(parent, objectForEditing, fieldLocalizedNameGetter);
        fieldsForEdit = fieldsForProcessing.stream().filter(field -> field.getAnnotation(OEADLField.class).editable()).collect(Collectors.toList());
        fieldsForEdit.forEach(
                field -> {
                    //TODO исправить данное место, убрать жёстко заданный русский текст для кнопки
                    FieldEditPanel fieldEditPanel = new FieldEditPanel(parent, field, objectForEditing, fieldLocalizedNameGetter, "Редактировать объект");
                    rootPanel.add(fieldEditPanel);
                }
        );
        this.pack();
    }
}
