package org.blbulyandavbulyan.oeadl.annotations;

import java.lang.annotation.*;
/**
 * Эта аннотация предназначена для классов, которые будут обрабатываться моей библиотекой для отображения их полей или отображения и редактирования
 * This annotation is for classes for displaying or editing in my library
 * @since 1.0.0
 * @version 1.0.0
 * @author David Blbulyan
 *
 * */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OEADLProcessingClass {
}
