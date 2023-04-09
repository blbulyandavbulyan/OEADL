
package org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog;


import java.awt.*;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.exceptions.OEADLException;
import org.blbulyandavbulyan.oeadl.factories.ObjectDisplayerDialogFactory;
import org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel.FieldDisplayPanel;
import org.blbulyandavbulyan.oeadl.exceptions.invalidclass.NoFieldsForDisplayException;
import org.blbulyandavbulyan.oeadl.interfaces.GenerateObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GetResourceBundleByClass;

public class ObjectDisplayerDialog extends ObjectDialog {
    /**
     * Эта коллекция содержит все поля, которые помечены в классе objectClass аннотацией {@link OEADLField} и у которой параметр {@link OEADLField#displayable()} true
     * <br>
     * This collection contains all the fields, which are annotated {@link OEADLField} and {@link OEADLField#displayable()} is true
     * */
    protected Collection<Field> displayableFields;

    public ObjectDisplayerDialog(Window parent, Object objectForDisplay, ResourceBundle uiResourceBundle, GenerateObjectDialog generateObjectDialog, GetResourceBundleByClass getResourceBundleByClass){
        super(parent, objectForDisplay, uiResourceBundle, generateObjectDialog, getResourceBundleByClass);
        displayableFields = fieldsForProcessing.stream().filter(field -> field.getAnnotation(OEADLField.class).displayable()).collect(Collectors.toList());
        if(!displayableFields.isEmpty()){
            displayableFields.forEach(
                    field -> {
                        try {
                            //TODO исправить данное место, убрать жёстко заданный русский текст для кнопки
                            FieldDisplayPanel fieldDisplayPanel = new FieldDisplayPanel(this, field, field.get(objectForDisplay), uiResourceBundle, generateObjectDialog, getResourceBundleByClass);
                            rootPanel.add(fieldDisplayPanel);
                        } catch (IllegalAccessException e) {
                            throw new OEADLException(e);
                        }
                    }
            );
        }
        else throw new NoFieldsForDisplayException(objectClass);
        this.pack();
    }
}
