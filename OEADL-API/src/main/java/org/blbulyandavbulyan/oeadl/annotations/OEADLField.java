package org.blbulyandavbulyan.oeadl.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Данная аннотация предназначена для полей, которые нужно будет отображать либо в окне для редактирования, либо в окне для отображения.
 * <br>
 * This annotation is for fields, that you need to display or display and possibly edit in the windows
 * @author David Blbulyan
 * @version 1.0.0
 * @since 1.0.0
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OEADLField {
    boolean displayable() default true;//данный параметр отвечает, должно ли отображаться поле в диалоге ObjectDisplayerDialog
    boolean editable() default true;//данный параметр отвечает, доступно ли это поле для редактирования
    String localizedNamePropertyKey() default "";
}
