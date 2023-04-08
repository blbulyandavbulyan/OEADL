
package org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent;
import org.blbulyandavbulyan.oeadl.gui.interfaces.GetValue;
import java.awt.*;

public class ComponentAndValueGetter implements GetValue{
    protected Component displayableComponent;
    protected GetValue getValue;
    public ComponentAndValueGetter(Component displayableComponent, GetValue getValue){
        this.displayableComponent = displayableComponent;
        this.getValue = getValue;
    }
    public Object getValue(){
        return getValue.getValue();
    }
    public Component getDisplayableComponent(){
        return displayableComponent;
    }
}
