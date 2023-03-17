package org.blbulyandavbulyan.oeadl.displayer.dialogs;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.reflection.ProcessingClass;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

public abstract class ObjectDialog extends JDialog {
    protected Collection<Field> fieldsForProcessing;
    protected Function<String, String> localizedFieldNameGetter;
    protected Object processingObject;
    protected JPanel rootPanel;
    protected ObjectDialog(Window parent, Class<?> objectClass, Object processingObject, Function<String, String> fieldLocalizedNameGetter){
        super(parent);
        this.localizedFieldNameGetter = fieldLocalizedNameGetter;
        this.processingObject = processingObject;
        rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        fieldsForProcessing = new ArrayList<>(ProcessingClass.getAllAnnotatedFieldsInOEADLProcessingClass(objectClass, OEADLField.class));
        this.getContentPane().add(rootPanel);
        this.pack();
    }
}
