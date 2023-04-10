
package org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.exceptions.OEADLException;
import org.blbulyandavbulyan.oeadl.interfaces.GenerateObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GetResourceBundleByClass;
import org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel.FieldEditPanel;
import org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel.FieldPanel;
import org.blbulyandavbulyan.oeadl.interfaces.SetVisibleAndAddOkActionAndGetValueAndDisposeInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Этот класс предназначен для отображения объектов с возможностью редактирования
 * <br>
 * This class is for displaying and editing objects
 * @author David Blbulyan
 * @version 1.0.0
 * @since 1.0.0
 * */
public class ObjectEditorDialog extends ObjectDialog implements SetVisibleAndAddOkActionAndGetValueAndDisposeInterface {

    protected Collection<Field> fieldsForEdit;
    protected JButton okButton;
    protected JButton cancelButton;

    public ObjectEditorDialog(Window parent, Object objectForEditing, ResourceBundle uiResourceBundle, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass) {
        super(parent, objectForEditing, uiResourceBundle, generateObjectDialog, getResourceBundleByClass);
        fieldsForProcessing.forEach(
                field -> {
                    OEADLField oeadlField = field.getAnnotation(OEADLField.class);
                    if(oeadlField.editable()){
                        try {
                            FieldEditPanel fieldEditPanel = new FieldEditPanel(this, field, field.get(objectForEditing), uiResourceBundle, generateObjectDialog, getResourceBundleByClass);
                            fieldPanels.add(fieldEditPanel);
                            rootPanel.add(fieldEditPanel);
                        } catch (IllegalAccessException e) {
                            throw new OEADLException(e);
                        }
                    }
                    else if(oeadlField.displayable()){

                    }
                }
        );
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
        });
        cancelButton.addActionListener(l->{
            okCancelAction();
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
    public void addOkAction(ActionListener l) {
        okButton.addActionListener(l);
    }
}
