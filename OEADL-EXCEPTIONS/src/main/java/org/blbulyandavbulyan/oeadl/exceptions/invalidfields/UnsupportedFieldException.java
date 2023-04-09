package org.blbulyandavbulyan.oeadl.exceptions.invalidfields;

import java.lang.reflect.Field;
/**
 * Этот класс предназначен для исключений, которые будут выброшены, если поле в классе не имеет аннотацию OEADLField, но при этом как-то попало в класс FieldPanel
 * <br>
 * This class needs for throwing exception, if the field, which given in inner class FieldPanel in module OEADL-SWING doesn't have annotation OEADLField, this exception you will never catch, because there is checking for this annotation before creation FieldPanel
 * @see org.blbulyandavbulyan.oeadl.annotations.OEADLField
 * @version 1.0.0
 * @since 1.0.0
 * @author David Blbulyan
 * */
public class UnsupportedFieldException extends InvalidFieldException{
    /**
     * @param field Этот параметр предназначен для поля, которое стало причиной исключения.<br>This param is for field, which is cause of exception
     *
     * */
    public UnsupportedFieldException(Field field) {
        super(field, String.format("Unsupported field: \"%s\" in class: \"%s\", the field was given as a parameter", field.getName(), field.getDeclaringClass().getName()));
    }
}
