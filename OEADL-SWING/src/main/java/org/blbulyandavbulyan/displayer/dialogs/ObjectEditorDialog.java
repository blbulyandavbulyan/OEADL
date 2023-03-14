package org.blbulyandavbulyan.displayer.dialogs;

import org.blbulyandavbulyan.annotations.OEADLField;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ObjectEditorDialog extends ObjectDialog {
    protected Collection<Field> fieldsForEdit;
    protected ObjectEditorDialog(Window parent, Class<?> objectClass, Object processingObject, Function<String, String> fieldLocalizedNameGetter) {
        super(parent, objectClass, processingObject, fieldLocalizedNameGetter);
        fieldsForEdit = fieldsForProcessing.stream().filter(field -> field.getAnnotation(OEADLField.class).editable()).collect(Collectors.toList());
        fieldsForEdit.forEach(
                field -> {

                }
        );
    }
}
