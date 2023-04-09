package org.blbulyandavbulyan.oeadl.factories;

import org.blbulyandavbulyan.oeadl.exceptions.OEADLException;
import org.blbulyandavbulyan.oeadl.factories.exceptions.UiTextRbDoesntContainRequireKey;
import org.blbulyandavbulyan.oeadl.gui.dialogs.objectdialog.ObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GenerateObjectDialog;
import org.blbulyandavbulyan.oeadl.interfaces.GetResourceBundleByClass;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class ObjectDialogFactory implements GetResourceBundleByClass, GenerateObjectDialog {
    protected static final ResourceBundle defaultResourceBundle = ResourceBundle.getBundle("oeadl_swing_guitext");
    protected ResourceBundle uiTextRb;
    protected Map<Class<?>, ResourceBundle> classResourceBundleHashMap;
    protected Class<? extends ObjectDialog> objectDialogClass;
    protected ObjectDialogFactory(Class<? extends ObjectDialog> objectDialogClass, ResourceBundle uiTextRb){
        classResourceBundleHashMap = new HashMap<>();
        setUiTextResourceBundle(uiTextRb);
        this.objectDialogClass = objectDialogClass;
    }
    protected ObjectDialogFactory(Class<? extends ObjectDialog> objectDialogClass){
        this(objectDialogClass, defaultResourceBundle);
    }
    public void addOrReplaceResourceBundle(Class<?> c, ResourceBundle rb){
        classResourceBundleHashMap.put(c, rb);
    }
    public ResourceBundle getResourceBundleForClass(Class<?> c){
        return classResourceBundleHashMap.get(c);
    }
    public void removeResourceBundleForClass(Class<?> c){
        classResourceBundleHashMap.remove(c);
    }
    public void setUiTextResourceBundle(ResourceBundle rb){
        defaultResourceBundle.getKeys().asIterator().forEachRemaining(
                (k)->{
                    if(!rb.containsKey(k)){
                        throw new UiTextRbDoesntContainRequireKey(k, rb);
                    }
                }
        );
        this.uiTextRb = rb;
    }
    @Override
    public ObjectDialog generateObjectDialog(Window parent, Object processingObject){
        try {
            return objectDialogClass.getConstructor(Window.class, Object.class, ResourceBundle.class, GenerateObjectDialog.class, GetResourceBundleByClass.class)
                    .newInstance(parent, processingObject, uiTextRb, this, this);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new OEADLException(e);
        }
    }
}
