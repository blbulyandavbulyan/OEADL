package org.blbulyandavbulyan.oeadl.gui.dialogs.dialogvaluegetter;

import org.blbulyandavbulyan.oeadl.interfaces.GetValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class DialogValueGetter extends JDialog implements GetValue {
    public DialogValueGetter(Window parent) {
        super(parent);
    }
}
