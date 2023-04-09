package org.blbulyandavbulyan.oeadl.exceptions.componentgenerator;

public class ConvertorForClassDoesntExists extends ComponentGeneratorException{
    public ConvertorForClassDoesntExists(Class<?> causeClass) {
        super(String.format("Convertor for class: \"%s\" doesn't exists!", causeClass.getName()));
    }
}
