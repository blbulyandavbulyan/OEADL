
package org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.exceptions.OEADLException;
import org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel.FieldDisplayPanel;
import org.blbulyandavbulyan.oeadl.interfaces.GenerateObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GetResourceBundleByClass;
import org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel.FieldEditPanel;
import org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel.FieldPanel;
import org.blbulyandavbulyan.oeadl.interfaces.EditorDialogControllingInterface;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Этот класс предназначен для отображения объектов с возможностью редактирования
 * <br>
 * This class is for displaying and editing objects
 * @author David Blbulyan
 * @version 1.0.0
 * @since 1.0.0
 * */
public class ObjectEditorDialog extends ObjectDialog implements EditorDialogControllingInterface {

    protected Collection<Field> fieldsForEdit;
    protected JButton okButton;
    protected JButton cancelButton;
    protected Runnable okAction;
    protected Runnable cancelAction;
    public ObjectEditorDialog(Window parent, Object objectForEditing, ResourceBundle uiResourceBundle, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass) {
        super(parent, objectForEditing, uiResourceBundle, generateObjectDialog, getResourceBundleByClass);
        fieldsForProcessing.forEach(
                field -> {
                    try {
                        OEADLField oeadlField = field.getAnnotation(OEADLField.class);
                        Object fieldValue = field.get(objectForEditing);
                        if(oeadlField.editable()){
                            FieldEditPanel fieldEditPanel = new FieldEditPanel(this, field, fieldValue, uiResourceBundle, generateObjectDialog, getResourceBundleByClass);
                            fieldPanels.add(fieldEditPanel);
                        }
                        else if(oeadlField.displayable()){
                            // TODO: 11.04.2023 Добавить обработку поля только для отображения здесь
                            FieldDisplayPanel fieldDisplayPanel = new FieldDisplayPanel(this, field, fieldValue, uiResourceBundle, generateObjectDialog, getResourceBundleByClass);
                            fieldPanels.add(fieldDisplayPanel);
                        }
                    }
                    catch (IllegalAccessException e){
                        throw new OEADLException(e);
                    }

                }
        );
        fieldPanels = fieldPanels.stream().sorted((fP1, fP2) -> {
            if(fP1.getClass().equals(fP2.getClass()))return 0;
            else if(fP1 instanceof FieldEditPanel)return 1;
            else return -1;
        }).collect(Collectors.toList());
        fieldPanels.forEach(fieldPanel -> rootPanel.add(fieldPanel));
        JPanel okCancelPanel = new JPanel();
        okButton = new JButton(uiResourceBundle.getString("oeadl_swing.buttons.ok"));
        cancelButton = new JButton(uiResourceBundle.getString("oeadl_swing.buttons.cancel"));
        okButton.addActionListener(e -> {
            for(FieldPanel fieldPanel : fieldPanels){
                if(fieldPanel.getField().getAnnotation(OEADLField.class).editable()){
                    try {
                        fieldPanel.getField().set(objectForEditing, fieldPanel.getFieldComponent().getValue());
                    } catch (IllegalAccessException ex) {
                        throw new OEADLException(ex);
                    }
                }

            }
            okCancelAction();
            doOkAction();
        });
        cancelButton.addActionListener(l->{
            okCancelAction();
            doCancelAction();
        });
        okCancelPanel.add(okButton);
        okCancelPanel.add(cancelButton);
        rootPanel.add(okCancelPanel);
        this.pack();
    }
    private void okCancelAction(){
        if(getParent() == null)dispose();
        else setVisible(false);
    }

    @Override
    public void setAfterOkButtonPressedAction(Runnable action) {
        this.okAction = action;
    }
    @Override
    public void setActionAfterCancelButtonPressed(Runnable action) {
        this.cancelAction = action;
    }
    private void doOkAction(){
        if(okAction != null)okAction.run();
    }
    private void doCancelAction(){
        if(cancelAction != null)cancelAction.run();
    }
}
