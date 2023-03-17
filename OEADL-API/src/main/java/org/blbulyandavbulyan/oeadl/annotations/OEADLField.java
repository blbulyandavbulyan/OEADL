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
    /**
     * This parameter needs, if you want to display the annotated field in your class
     * <br>
     * Этот параметр нужен, если вы хотите чтобы поле помеченное аннотацией этой аннотацией отображалось
     * */
    boolean displayable() default true;
    /**
     * This parameter needs, if you want to enable editing the annotated field in your class
     * <br>
     * Этот параметр нужен, если вы хотите чтобы данное поле было доступно для редактирования
     * */
    boolean editable() default true;//данный параметр отвечает, доступно ли это поле для редактирования
    /**
     * Этот параметр должен содержать ключ, для получения локализованного имени поля.
     * <br>
     * This parameter must contain the property key, which uses for getting localized field name in localizedFieldGetter
     * */
    String localizedNamePropertyKey() default "";
}
