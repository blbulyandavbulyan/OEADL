package org.blbulyandavbulyan.oeadl.gui.dialogs;


import java.awt.*;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.gui.panels.fieldpanel.FieldDisplayPanel;
import org.blbulyandavbulyan.oeadl.exceptions.invalidclass.NoFieldsForDisplayException;

public class ObjectDisplayerDialog extends ObjectDialog {
    /**
     * Эта коллекция содержит все поля, которые помечены в классе objectClass аннотацией {@link OEADLField} и у которой параметр {@link OEADLField#displayable()} true
     * <br>
     * This collection contains all the fields, which are annotated {@link OEADLField} and {@link OEADLField#displayable()} is true
     * */
    protected Collection<Field> displayableFields;
    /**
     * @see ObjectDialog#ObjectDialog(Window, Object, Function)
     * */
    public ObjectDisplayerDialog(Window parent, Object objectForDisplay, Function<String, String> localizedNameGetter){
        super(parent, objectForDisplay, localizedNameGetter);
        displayableFields = fieldsForProcessing.stream().filter(field -> field.getAnnotation(OEADLField.class).displayable()).collect(Collectors.toList());
        if(!displayableFields.isEmpty()){
            displayableFields.forEach(field -> {
                FieldComponent fieldComponent =
            });
            displayableFields.forEach(
                    field -> {
                        try {
                            //TODO исправить данное место, убрать жёстко заданный русский текст для кнопки
                            FieldDisplayPanel fieldDisplayPanel = new FieldDisplayPanel(this, field, field.get(objectForDisplay), localizedNameGetter, "Посмотреть детали");
                            rootPanel.add(fieldDisplayPanel);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
        else throw new NoFieldsForDisplayException(objectClass);
        this.pack();
    }
    /**
     * Этот конструктор аналогичен уже определённому, за исключением того что parent будет равен null
     * <br>
     * This constructor is similar to the already defined, except that the parent parameter will be null
     * @see ObjectDisplayerDialog#ObjectDisplayerDialog(Window, Object, Function) ObjectDisplayerDialog(parent, objectForDisplay, fieldLocalizedNameGetter)
     *
     * */
    public ObjectDisplayerDialog(Object objectForDisplay, Function<String, String> fieldLocalizedNameGetter){
        this(null, objectForDisplay, fieldLocalizedNameGetter);
    }
}
