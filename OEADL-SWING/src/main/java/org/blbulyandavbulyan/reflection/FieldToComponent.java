package org.blbulyandavbulyan.reflection;

import java.awt.*;
import java.lang.reflect.Field;

public interface FieldToComponent {
    boolean canConvert(Field field);
    Component convert(Field field, Object objectContainingField);
}
