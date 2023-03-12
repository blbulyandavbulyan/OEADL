package org.blbulyandavbulyan.displayer.panels;

import javax.swing.*;

public class FieldNameAndValuePanel extends JPanel {
    protected JLabel fieldNameLabel;
    protected JLabel fieldValueLabel;
    public FieldNameAndValuePanel(String fieldName, String fieldValue){
        fieldNameLabel = new JLabel(fieldName);
        fieldValueLabel = new JLabel(fieldValue);
        this.add(fieldNameLabel);
        this.add(fieldValueLabel);
    }
}
