package org.blbulyandavbulyan.oeadl.gui.dialogs.simpletypeeditor;

import org.blbulyandavbulyan.oeadl.gui.componentgenerator.ComponentAndValueGetter;
import org.blbulyandavbulyan.oeadl.interfaces.SetVisibleAndAddOkActionAndGetValueAndDisposeInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class SimpleTypeEditorDialog extends JDialog implements SetVisibleAndAddOkActionAndGetValueAndDisposeInterface {
    protected ComponentAndValueGetter componentAndValueGetter;
    protected JButton okButton;
    protected JButton cancelButton;
    protected Object value;
    public SimpleTypeEditorDialog(Window parent, ComponentAndValueGetter componentAndValueGetter, ResourceBundle uiResourceBundle){
        super(parent);
        okButton = new JButton(uiResourceBundle.getString("oeadl_swing.buttons.ok"));
        cancelButton = new JButton(uiResourceBundle.getString("oeadl_swing.buttons.cancel"));
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

    @Override
    public void addOkAction(ActionListener l) {
        okButton.addActionListener(l);
    }
}
