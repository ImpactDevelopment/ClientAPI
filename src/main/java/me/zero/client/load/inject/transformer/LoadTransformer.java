package me.zero.client.load.inject.transformer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Put on transformers to ensure loading.
 *
 * Only used within clients to tag classes
 * that extend Transformer. Just a way of
 * "registering" the transformers.
 *
 * @since 1.0
 *
 * Created by Brady on 2/8/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LoadTransformer {}
