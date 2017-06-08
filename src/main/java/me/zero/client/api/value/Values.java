/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.api.value;

import me.zero.client.api.exception.ValueException;
import me.zero.client.api.util.ClientUtils;
import me.zero.client.api.util.annotation.Label;
import me.zero.client.api.value.annotation.*;
import me.zero.client.api.value.holder.IValueHolder;
import me.zero.client.api.value.type.resolve.DefaultResolvers;
import me.zero.client.api.value.type.resolve.ResolverData;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class used to discover values from various holders
 *
 * @author Brady
 * @since 2/21/2017 12:00 PM
 */
public final class Values {

    /**
     * Holds the various resolvers for resolving values from fields
     */
    private static final Map<Class<?>, ResolverData> RESOLVERS = new HashMap<>();

    static {
        // Default Resolvers
        define(BooleanValue.class, ResolverData.create(DefaultResolvers.BOOLEAN, Boolean.class, Boolean.TYPE));
        define(NumberValue.class, ResolverData.create(DefaultResolvers.NUMBER));
        define(MultiValue.class, ResolverData.create(DefaultResolvers.MULTI, String.class));
        define(StringValue.class, ResolverData.create(DefaultResolvers.STRING, String.class));
    }

    private Values() {}

    /**
     * Discovers all of the Values in a ValueHolder, then registers them
     *
     * @param holder Holder being scanned
     */
    public static void discover(IValueHolder holder) {
        Arrays.stream(holder.getClass().getDeclaredFields())
                .filter(Values::hasValueAnnotation)
                .forEach(field -> holder.addValue(getValue(holder, field)));
    }

    /**
     * Returns whether or not the specified field
     * has a value annotation or not.
     *
     * @param field Field being checked
     * @return If the field has a value annotation
     */
    private static boolean hasValueAnnotation(Field field) {
        return getValueAnnotation(field) != null;
    }

    /**
     * Gets the class of the value annotation belonging
     * to a field, null if there is none.
     *
     * @param field Field being checked
     * @return The value annotation of the field
     */
    private static Class<? extends Annotation> getValueAnnotation(Field field) {
        if (field.isAnnotationPresent(Label.class)) {
            Annotation a = Arrays.stream(field.getDeclaredAnnotations())
                    .filter(annotation -> annotation.annotationType().isAnnotationPresent(ValueDefinition.class))
                    .findFirst()
                    .orElse(null);
            if (a != null)
                return a.annotationType();
        }
        return null;
    }

    /**
     * Executes checks before using the TypeResolver to
     * get the Value from the Field.
     *
     * @param parent Object containing value field
     * @param field Field representing value
     * @return The resolved field
     */
    @SuppressWarnings("unchecked")
    private static Value getValue(Object parent, Field field) {
        Class<? extends Annotation> anno = getValueAnnotation(field);
        if (anno == null)
            throw new ValueException("Value annotation not found for field");

        ResolverData data = RESOLVERS.get(anno);
        if (data == null)
            throw new ValueException("Undefined Resolver for Value Definition");

        if (!data.isResolvable(field.getType()))
            throw new ValueException("Unable to resolve field if type is not supported");

        Object resolved = data.getResolver().apply(parent, field);
        if (resolved == null || !(resolved instanceof Value))
            throw new ValueException("Outcome of resolver was either null or not a value type");

        return (Value) resolved;
    }

    /**
     * Defines a resolver data object. This method should
     * be called before module instantiation, to ensure
     * that all custom defined value types will be resolved
     *
     * @param type The target type annotation
     * @param data The resolver data for the annotation
     */
    public static void define(Class<?> type, ResolverData data) {
        if (ClientUtils.containsNull(type, data))
            throw new NullPointerException("One or more parameters were null");

        if (RESOLVERS.get(type) != null)
            throw new ValueException("Resolver for type annotation already exists");

        RESOLVERS.put(type, data);
    }
}
