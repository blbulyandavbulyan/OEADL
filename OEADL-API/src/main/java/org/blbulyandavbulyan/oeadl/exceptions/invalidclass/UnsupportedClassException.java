package org.blbulyandavbulyan.oeadl.exceptions.invalidclass;

/**
 * Это исключение бросается в случае если класс, который передали моей библиотеке для отображения или редактирования не помечен аннотацией OEADLProcessingClass
 * @author David Blbulyan
 * @version 1.0.0
 * @since 1.0.1
 */
public class UnsupportedClassException extends InvalidClassException{
    /**
     * @param c   {@link InvalidClassException#invalidClass}
     */
    public UnsupportedClassException(Class<?> c) {
        super(c, String.format("This class: %s must have annotation OEADLProcessingClass", c.getName()));
    }
}
