package org.blbulyandavbulyan.oeadl.exceptions;
/**
 * Это общий класс исключений, которые могут возникнуть при использовании моей библиотеки
 * This is general exceptions class, which my library can throw
 * @author David Blbulyan
 * @since 1.0.0
 * @version 1.0.0
 * */
public class OEADLException extends RuntimeException{
    /**
     *
     * @param msg Сообщение об ошибке для моего класса исключения, оно же будет передано в конструктор RuntimeException. The message about the error, which produce this exception, it will be passed to RuntimeException
     *
     * @see RuntimeException#RuntimeException(String)
     * @author David Blbulyan
     * */
    public OEADLException(String msg){
        super(msg);
    }
    public OEADLException(){

    }
}
