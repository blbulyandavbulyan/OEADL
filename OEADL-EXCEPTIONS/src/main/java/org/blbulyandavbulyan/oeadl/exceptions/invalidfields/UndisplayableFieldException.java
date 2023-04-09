package org.blbulyandavbulyan.oeadl.exceptions.invalidfields;


import java.lang.reflect.Field;
/**
 * Этот класс описывает исключение, которые возникают, если поле для отображения имеет аннотацию OEADLField с параметром displayable() false
 * <br>
 * This class describes the exception, which throwing, if the field for displaying has OEADLField annotation with the parameter displayable() is false
 * @author David Blbulyan
 * @see org.blbulyandavbulyan.oeadl.annotations.OEADLField
 * @version 1.0.0
 * @since 1.0.0
 * */
public class UndisplayableFieldException extends InvalidFieldException{
    /**
     * @param field  {@link InvalidFieldException#field}
     * @see org.blbulyandavbulyan.oeadl.annotations.OEADLField
     * */
    public UndisplayableFieldException(Field field) {
        super(field, String.format("Undisplayable field %s in class %s", field.getType().getName(), field.getDeclaringClass().getName()));
    }
}
