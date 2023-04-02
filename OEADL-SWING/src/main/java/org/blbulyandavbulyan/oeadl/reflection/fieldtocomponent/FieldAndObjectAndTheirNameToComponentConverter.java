package org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent;

import java.awt.*;
import java.lang.reflect.Field;


public interface FieldAndObjectAndTheirNameToComponentConverter {
    FieldComponent convertToComponent(Field field, String name, Object obj);
}
