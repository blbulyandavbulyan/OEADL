package org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent;

import java.awt.*;
import java.lang.reflect.Field;

public interface ObjectAndItsNameToComponentConverter {
    Component convertToComponent(Field field, String name, Object obj);
}
