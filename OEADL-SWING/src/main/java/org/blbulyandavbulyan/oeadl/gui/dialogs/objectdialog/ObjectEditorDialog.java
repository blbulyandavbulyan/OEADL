package org.blbulyandavbulyan.oeadl.gui.dialogs;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel.FieldEditPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
    protected Map<Field, Component> fieldAndEditorComponentMapper;
    protected JButton okButton;
    protected JButton cancelButton;

    /**
     * @see ObjectDialog#ObjectDialog(Window, Object, Function) ObjectDialog(parent, objectClass, processingObject, fieldLocalizedNameGetter)
     * */
    protected ObjectEditorDialog(Window parent, Object objectForEditing, Function<String, String> fieldLocalizedNameGetter) {
        super(parent, objectForEditing, fieldLocalizedNameGetter);
        fieldAndEditorComponentMapper = new HashMap<>();
        fieldsForEdit = fieldsForProcessing.stream().filter(field -> field.getAnnotation(OEADLField.class).editable()).collect(Collectors.toList());
        fieldsForEdit.forEach(
                field -> {
                    //TODO исправить данное место, убрать жёстко заданный русский текст для кнопки
                    FieldEditPanel fieldEditPanel = new FieldEditPanel(parent, field, objectForEditing, fieldLocalizedNameGetter, "Редактировать объект");

                    rootPanel.add(fieldEditPanel);
                }
        );
        JPanel okCancelPanel = new JPanel();
        okButton = new JButton("Ок");
        cancelButton = new JButton("Отмена");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (var fieldComponentEntry : fieldAndEditorComponentMapper.entrySet()) {
                    JTextField jTextField;
                    JCheckBox jCheckBox;
                    fieldComponentEntry.getKey().set(processingObject, fieldComponentEntry.getValue());
                }
            }
        });

        okCancelPanel.add(okButton);
        okCancelPanel.add(cancelButton);

        rootPanel.add(okCancelPanel);
        this.pack();
    }
    public Object getValue(){
        return null;
    }
}
