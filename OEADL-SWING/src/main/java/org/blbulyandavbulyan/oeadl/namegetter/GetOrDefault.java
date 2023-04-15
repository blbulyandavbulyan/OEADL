package org.blbulyandavbulyan.oeadl.namegetter;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class GetOrDefault {
    public static String getStringOrDefault(String strForCheck, String defaultValue){
        return strForCheck == null || strForCheck.isBlank() ? defaultValue : strForCheck;
    }
    public static String getFromRbOrDefault(ResourceBundle rb, String key, String defaultValue){
        try{
            if(rb != null)return rb.getString(key);
            else return defaultValue;
        }
        catch (MissingResourceException e){
            return defaultValue;
        }
    }
}
