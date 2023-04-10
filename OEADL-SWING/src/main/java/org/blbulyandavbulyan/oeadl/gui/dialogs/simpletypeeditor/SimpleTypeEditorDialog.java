package org.blbulyandavbulyan.oeadl.gui.dialogs.simpletypeeditor;

import org.blbulyandavbulyan.oeadl.gui.componentgenerator.ComponentAndValueGetter;
import org.blbulyandavbulyan.oeadl.interfaces.EditorDialogControllingInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class SimpleTypeEditorDialog extends JDialog implements EditorDialogControllingInterface {
    protected ComponentAndValueGetter componentAndValueGetter;
    protected JButton okButton;
    protected JButton cancelButton;
    protected Object value;
    protected Runnable okAction;
    protected Runnable cancelAction;
    public SimpleTypeEditorDialog(Window parent, ComponentAndValueGetter componentAndValueGetter, ResourceBundle uiResourceBundle){
        super(parent);
        okButton = new JButton(uiResourceBundle.getString("oeadl_swing.buttons.ok"));
        cancelButton = new JButton(uiResourceBundle.getString("oeadl_swing.buttons.cancel"));
        value = componentAndValueGetter.getValue();
        this.componentAndValueGetter = componentAndValueGetter;
        JPanel okCancelButtonPanel = new JPanel();
        okCancelButtonPanel.add(okButton);
        okCancelButtonPanel.add(cancelButton);
        JPanel rootPanel = new JPanel();
        rootPanel.add(componentAndValueGetter.getDisplayableComponent());
        rootPanel.add(okCancelButtonPanel);
        getContentPane().add(rootPanel);
        okButton.addActionListener(l->{
            value = componentAndValueGetter.getValue();
            okOrCloseAction();
            doOkAction();
        });
        cancelButton.addActionListener(l->{
            okOrCloseAction();
            doCancelAction();
        });
        this.pack();
    }
    private void okOrCloseAction(){
        if(getParent() == null)dispose();
        else setVisible(false);
    }
    @Override
    public Object getValue() {
        return value;
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
