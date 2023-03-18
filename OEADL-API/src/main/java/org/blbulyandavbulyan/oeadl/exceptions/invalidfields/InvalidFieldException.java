package org.blbulyandavbulyan.oeadl.exceptions.invalidfields;
import org.blbulyandavbulyan.oeadl.exceptions.OEADLException;

import java.lang.reflect.Field;
/**
 * Класс исключений, возникающих в моей библиотеке, если класс переданный для отображения или редактирования имеет поле с неправильно размеченной аннотацией OEADLField
 * <br>
 * This class exception can produce my library if the field in the class, that you provided for displaying or editing has wrong annotation OEADLField
 * @see org.blbulyandavbulyan.oeadl.annotations.OEADLField
 * @author David Blbulyan
 * @since 1.0.0
 * @version 1.0.0
 * */
public abstract class InvalidFieldException extends OEADLException {
    /**
     * Это поле содержит поле, которое вызвало исключение.
     * This field contains the field, which is cause of the exception
     * */
    protected final Field field;
    /**
     *
     * @param field field which produced the exception
     * @param msg a message to describe the error, this param is need for creating {@link OEADLException}
     * @author David Blbulyan
     *
     * */
    protected InvalidFieldException(Field field, String msg){
        super(msg);
        this.field = field;
    }

    public Field getField() {
        return field;
    }
}
