package org.blbulyandavbulyan.oeadl.exceptions.invalidclass;

import org.blbulyandavbulyan.oeadl.exceptions.OEADLException;
/**
 * Этот класс предназначен для исключений, которые бросаются в случае когда класс, переданный для отображения или редактирования в мою библиотеку имеет не правильную структуру
 * <br>
 * This class is for exceptions, which is throwing, if the class is given in my library for displaying or editing has wrong structure
 * @author David Blbulyan
 * @version 1.0.0
 * @since 1.0.0
 * */
public abstract class InvalidClassException extends OEADLException {
    /**
     * Класс, вызвавший исключение
     * <br>
     * The class, which is cause of the exception
     * */
    protected Class<?> invalidClass;
    /**
     * @param c {@link InvalidClassException#invalidClass}
     * @param msg {@link OEADLException#OEADLException(String)}
     * */
    public InvalidClassException(Class<?> c, String msg){
        super(msg);
        this.invalidClass = c;
    }
}
