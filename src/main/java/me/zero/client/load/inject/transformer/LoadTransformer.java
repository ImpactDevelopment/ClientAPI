package me.zero.client.load.inject.transformer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation is put on transformer classes
 * to ensure that they get loaded.
 *
 * @since 1.0
 *
 * Created by Brady on 2/8/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LoadTransformer {}
