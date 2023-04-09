package org.blbulyandavbulyan.oeadl.exceptions.componentgenerator;

public class ClassConvertorAlreadyExistsException extends ComponentGeneratorException{
    private Class<?> causeClass;
    public ClassConvertorAlreadyExistsException(Class<?> causeClass){
        super(String.format("Convertor for class: \"%s\" already exists!", causeClass.getName()));
        this.causeClass = causeClass;
    }

}
