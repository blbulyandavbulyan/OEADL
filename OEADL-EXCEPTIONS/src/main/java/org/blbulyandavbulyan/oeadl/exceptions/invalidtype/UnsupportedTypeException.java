package org.blbulyandavbulyan.oeadl.exceptions.invalidtype;

public class UnsupportedTypeException extends InvalidTypeException{
    private final Class<?> causeType;
    public UnsupportedTypeException(Class<?> causeType){
        super(String.format("Type(class) %s in the inner map not found!", causeType.getName()));
        this.causeType = causeType;
    }
    public Class<?> getCauseType(){
        return causeType;
    }
}
