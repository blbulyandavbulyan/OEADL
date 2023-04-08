package org.blbulyandavbulyan.oeadl.gui.dialogs.fielddialog;

import org.blbulyandavbulyan.oeadl.gui.dialogs.dialogvaluegetter.DialogValueGetter;
import org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent.ComponentAndValueGetter;

import javax.swing.*;
import java.awt.*;

public class SimpleTypeEditorDialog extends DialogValueGetter {
    protected ComponentAndValueGetter componentAndValueGetter;
    protected JButton okButton;
    protected JButton cancelButton;
    protected Object value;
    public SimpleTypeEditorDialog(Window parent, ComponentAndValueGetter componentAndValueGetter){
        super(parent);
        okButton = new JButton("Ок");
        cancelButton = new JButton("Отмена");
        JPanel okCancelButtonPanel = new JPanel();
        okCancelButtonPanel.add(okButton);
        okCancelButtonPanel.add(cancelButton);
        this.componentAndValueGetter = componentAndValueGetter;
        this.add(componentAndValueGetter.getDisplayableComponent());
        this.add(okCancelButtonPanel);
        value = componentAndValueGetter.getValue();
        okButton.addActionListener(l->{
            value = componentAndValueGetter.getValue();
            okOrCloseAction();
        });
        cancelButton.addActionListener(l->{
            okOrCloseAction();
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
}
