package org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.function.Function;


public interface FieldAndObjectAndTheirNameToComponentConverter {
    FieldComponent convertToComponent(Field field, Function<String, String> localizedFieldNameGetter, Object obj, Window parent);
}
