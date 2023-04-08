package org.blbulyandavbulyan.oeadl.reflection.fieldtocomponent;

import java.awt.*;
import java.util.function.Function;


public interface FieldAndObjectAndTheirNameToComponentConverter {
    ComponentAndValueGetter convertToComponentAndValueGetter(String displayableName, Function<String, String> localizedFieldNameGetter, Object obj, Window parent);
}
