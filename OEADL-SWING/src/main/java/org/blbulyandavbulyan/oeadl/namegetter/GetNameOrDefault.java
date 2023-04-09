package org.blbulyandavbulyan.oeadl.namegetter;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class GetNameOrDefault {
    private Function<String, String> nameGetter;
    public GetNameOrDefault(Function<String, String> nameGetter){
        this.nameGetter = nameGetter;
    }
    public String getNameOrDefault(String key, String defaultValue){
        if(nameGetter != null){
            String name = nameGetter.apply(key);
            if(name == null || name.isBlank()){
                return defaultValue;
            }
            else return name;
        }
        return defaultValue;
    }
    public static String getNameOrDefault(ResourceBundle rb, String key, String defaultValue){
        try{
            if(rb != null)return rb.getString(key);
            else return defaultValue;
        }
        catch (MissingResourceException e){
            return defaultValue;
        }
    }
}
