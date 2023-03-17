package org.blbulyandavbulyan.oeadl.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Данная аннотация предназначена для полей, которые нужно будет отображать либо в окне для редактирования, либо в окне для отображения
 * @author David Blbulay
 * @version
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OEADLField {
    boolean displayable() default true;//данный параметр отвечает, должно ли отображаться поле в диалоге ObjectDisplayerDialog
    boolean editable() default true;//данный параметр отвечает, доступно ли это поле для редактирования
    String localizedNamePropertyKey() default "";
}
