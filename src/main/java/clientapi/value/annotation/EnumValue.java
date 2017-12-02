package clientapi.value.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark fields as Enum Values
 *
 * @author Brady
 * @since 12/2/2017 11:53 AM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ValueDefinition
public @interface EnumValue {}
