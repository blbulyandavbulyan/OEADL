package org.blbulyandavbulyan.oeadl.exceptions.invalidfields;

import java.lang.reflect.Field;
/**
 * Этот класс описывает исключение, которые возникают, если поле для отображения имеет аннотацию OEADLField с параметром editable() false
 * <br>
 * This class describes the exception, which throwing, if the field for displaying has OEADLField annotation with the parameter editable() is false
 * @author David Blbulyan
 * @see org.blbulyandavbulyan.oeadl.annotations.OEADLField
 * @version 1.0.0
 * @since 1.0.0
 * */
public class UneditableFieldException extends InvalidFieldException{
    /**
     * @param field Поле, которое помечено аннотацией OEADLField и у которой параметр editable() false. <br>The field, which contains OEADLField annotation and in this annotation the editable parameter is false
     * @see org.blbulyandavbulyan.oeadl.annotations.OEADLField
     * */
    public UneditableFieldException(Field field) {
        super(field, String.format("Uneditable field %s in class %s", field.getName(), field.getDeclaringClass().getName()));
    }
}
