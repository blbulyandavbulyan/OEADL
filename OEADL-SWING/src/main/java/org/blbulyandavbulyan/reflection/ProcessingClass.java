package org.blbulyandavbulyan.reflection;

import org.blbulyandavbulyan.annotations.OEADLProcessingClass;

import java.awt.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class ProcessingClass {
    //данная функция возвращает все поля, помеченные данной аннотацией(в том числе и в родителях класса), поиск полей останавливается, когда родитель равен stopParentForSearchFieldClass
    //данный метод будет брать родителя у текущего класса и проверять, помечен ли он аннотацией OEADLProcessingClass, если да, то будет получать из него поля помеченные аннотацией fieldAnnotatedClass,
    // если нет, то поиск остановится на нём
    public static Collection<Field> getAllAnnotatedFieldsInOEADLProcessingClass(Class<?> classForSearchFields, Class<? extends Annotation> fieldAnnotationCass){
        LinkedList<Field> annotatedFields = new LinkedList<>();
        for(Class<?> currentClass = classForSearchFields; currentClass.isAnnotationPresent(OEADLProcessingClass.class); currentClass = currentClass.getSuperclass()){
            Arrays.stream(currentClass.getDeclaredFields()).filter(field -> field.isAnnotationPresent(fieldAnnotationCass)).peek(field -> field.setAccessible(true)).forEach(annotatedFields::add);
        }
        return annotatedFields;
    }
}
