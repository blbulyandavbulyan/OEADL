package org.blbulyandavbulyan.oeadl.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OEADLField {
    boolean displayable() default true;//данный параметр отвечает, должно ли отображаться поле в диалоге ObjectDisplayerDialog
    boolean editable() default true;//данный параметр отвечает, доступно ли это поле для редактирования
    String localizedNamePropertyKey() default "";
}
