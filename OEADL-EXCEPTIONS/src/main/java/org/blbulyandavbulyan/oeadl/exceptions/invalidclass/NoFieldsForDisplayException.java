package org.blbulyandavbulyan.oeadl.exceptions.invalidclass;

public class NoFieldsForDisplayException extends InvalidClassException{
    public NoFieldsForDisplayException(Class<?> c){
        super(c, String.format("No fields for display in class %s", c.getName()));
    }
}
