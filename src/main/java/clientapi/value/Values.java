/*
 * Copyright 2018 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.value;

import clientapi.util.ClientAPIUtils;
import clientapi.util.annotation.Label;
import clientapi.value.annotation.*;
import clientapi.value.annotation.number.*;
import clientapi.value.exception.ValueException;
import clientapi.value.type.resolve.ResolverData;
import clientapi.value.type.resolve.impl.*;
import clientapi.value.type.resolve.impl.number.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class used to discover values from various holders
 *
 * @author Brady
 * @since 2/21/2017
 */
public final class Values {

    /**
     * Holds the various resolvers for resolving values from fields
     */
    private static final Map<Class<?>, ResolverData> RESOLVERS = new HashMap<>();

    /**
     * Cache of objects that have already had their values discovered, prevents
     * creating duplicate children on multiple calls to {@link Values#discover(Object)}
     */
    private static final Map<Object, List<IValue>> DISCOVER_CACHE = new HashMap<>();

    private Values() {}

    static {
        // Default Resolvers
        define(BooleanValue.class, ResolverData.create(new BooleanTypeResolver(), Boolean.class, Boolean.TYPE));
        define(MultiValue.class,   ResolverData.create(new MultiTypeResolver(),   String.class));
        define(StringValue.class,  ResolverData.create(new StringTypeResolver(),  String.class));
        define(EnumValue.class,    ResolverData.create(new EnumTypeResolver(),    Enum.class));
        define(ColorValue.class,   ResolverData.create(new ColorTypeResolver(),   Integer.class, Integer.TYPE));
        define(VoidValue.class,    ResolverData.create(new VoidTypeResolver(),    Void.class));

        define(ByteValue.class,    ResolverData.create(new ByteTypeResolver(),    Byte.class,    Byte.TYPE));
        define(ShortValue.class,   ResolverData.create(new ShortTypeResolver(),   Short.class,   Short.TYPE));
        define(IntegerValue.class, ResolverData.create(new IntegerTypeResolver(), Integer.class, Integer.TYPE));
        define(LongValue.class,    ResolverData.create(new LongTypeResolver(),    Long.class,    Long.TYPE));
        define(FloatValue.class,   ResolverData.create(new FloatTypeResolver(),   Float.class,   Float.TYPE));
        define(DoubleValue.class,  ResolverData.create(new DoubleTypeResolver(),  Double.class,  Double.TYPE));
    }

    /**
     * Discovers all of the Values that an object defines
     *
     * @param holder Object holding values
     * @return A list of all of the discovered values
     */
    public static List<IValue> discover(Object holder) {
        return DISCOVER_CACHE.computeIfAbsent(holder, obj -> {
            // Find all fields that are valid values
            List<IValue> values =  Arrays.stream(obj.getClass().getDeclaredFields())
                    .filter(Values::hasValueAnnotation)
                    .map(field -> getValue(obj, field))
                    .collect(Collectors.toList());

            // Setup the parent values
            values.forEach(value -> {
                IValue parent;
                // Check if the parent target isn't null and if there
                // is a parent target, the corresponding target isn't null
                if (value.getParent() != null && (parent = values.stream().filter(v -> v.getID().equals(value.getParent())).findFirst().orElse(null)) != null)
                    parent.addValue(value);
            });

            // Remove all values that have a parent from the holder values
            values.removeIf(value -> value.getParent() != null);
            return values;
        });
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
        // Values must have a label annotation
        if (!field.isAnnotationPresent(Label.class))
            return null;

        // Find a valid value type annotation, if any
        Annotation a = Arrays.stream(field.getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType().isAnnotationPresent(ValueDefinition.class))
                .findFirst().orElse(null);

        return a != null ? a.annotationType() : null;
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
    private static IValue getValue(Object parent, Field field) {
        Class<? extends Annotation> anno = getValueAnnotation(field);
        if (anno == null)
            throw new ValueException("Value annotation not found for field");

        ResolverData data = RESOLVERS.get(anno);
        if (data == null)
            throw new ValueException("Undefined Resolver for Value Definition");

        if (!data.isResolvable(field.getType()))
            throw new ValueException("Unable to resolve field if type is not supported");

        Object resolved = data.getResolver().resolve(parent, field);
        if (resolved == null)
            throw new ValueException("Outcome of resolver was either null or not a value type");

        return (IValue) resolved;
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
        if (ClientAPIUtils.containsNull(type, data))
            throw new NullPointerException("One or more parameters were null");

        if (RESOLVERS.get(type) != null)
            throw new ValueException("Resolver for type annotation already exists");

        RESOLVERS.put(type, data);
    }
}
