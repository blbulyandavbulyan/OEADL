package org.blbulyandavbulyan.oeadl.exceptions.invalidfields;

import java.lang.reflect.Field;
/**
 * Этот класс предназначен для исключений, которые будут выброшены, если поле помеченное аннотацией OEADLField имеет не поддерживаемый тип данных
 * <br>
 * This class is for exception, which will throw, if the field annotated OEADLField has unsupported type
 * @author David Blbulyan
 * @since 1.0.0
 * @version 1.0.0
 * @see org.blbulyandavbulyan.oeadl.annotations.OEADLField
 * */
public class UnsupportedFieldTypeException extends InvalidFieldException{
    /**
     * @param field {@link InvalidFieldException#field}
     * */
    public UnsupportedFieldTypeException(Field field){
        super(field, String.format("Unsupported field type %s in class %s", field.getType().getName(), field.getDeclaringClass().getName()));
    }
    /**
     * Этот метод возвращает тип поля в классе, к которому это поле относится
     * This method returns the type of the field in the class, where this field is declared.
     * @see Field#getType()
     * */
    public Class<?> getFieldType(){
        return field.getType();
    }
}
