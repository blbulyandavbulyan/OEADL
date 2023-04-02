
package org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.exceptions.OEADLException;
import org.blbulyandavbulyan.oeadl.gui.interfaces.GetValue;
import org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel.FieldEditPanel;
import org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel.FieldPanel;

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
public class ObjectEditorDialog extends ObjectDialog implements GetValue{

    protected Collection<Field> fieldsForEdit;
    protected JButton okButton;
    protected JButton cancelButton;

    /**
     * @see ObjectDialog#ObjectDialog(Window, Object, Function) ObjectDialog(parent, objectClass, processingObject, fieldLocalizedNameGetter)
     * */
    public ObjectEditorDialog(Window parent, Object objectForEditing, Function<String, String> fieldLocalizedNameGetter) {
        super(parent, objectForEditing, fieldLocalizedNameGetter);
        fieldsForEdit = fieldsForProcessing.stream().filter(field -> field.getAnnotation(OEADLField.class).editable()).collect(Collectors.toList());
        fieldsForEdit.forEach(
                field -> {
                    //TODO исправить данное место, убрать жёстко заданный русский текст для кнопки

                    try {
                        FieldEditPanel fieldEditPanel = new FieldEditPanel(parent, field, field.get(objectForEditing), fieldLocalizedNameGetter, "Редактировать объект");
                        fieldPanels.add(fieldEditPanel);
                        rootPanel.add(fieldEditPanel);
                    } catch (IllegalAccessException e) {
                        throw new OEADLException(e);
                    }
                }
        );
        JPanel okCancelPanel = new JPanel();
        okButton = new JButton("Ок");
        cancelButton = new JButton("Отмена");
        okButton.addActionListener(e -> {
            for(FieldPanel fieldPanel : fieldPanels){
                try {
                    fieldPanel.getField().set(objectForEditing, fieldPanel.getFieldComponent().getValue());
                } catch (IllegalAccessException ex) {
                    throw new OEADLException(ex);
                }
            }
            okCancelAction();
        });
        cancelButton.addActionListener(l->{
            okCancelAction();
        });
        okCancelPanel.add(okButton);
        okCancelPanel.add(cancelButton);
        rootPanel.add(okCancelPanel);
        this.pack();
    }
    public ObjectEditorDialog(Object objectForEditing, Function<String, String> fieldLocalizedNameGetter){
        this(null, objectForEditing, fieldLocalizedNameGetter);
    }
    private void okCancelAction(){
        if(getParent() == null)dispose();
        else setVisible(false);
    }
}
