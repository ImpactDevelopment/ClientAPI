/*
 * Copyright 2017 ImpactDevelopment
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

package clientapi.manage;

import clientapi.util.interfaces.Saveable;
import clientapi.util.interfaces.Loadable;

import java.util.*;

/**
 * Managers are used to store arrays of objects. {@code Manager}
 * extends {@code ArrayList}, allowing full access to the Manager's
 * contents. The usage of the manager is intended to store implementations
 * of the generic argument <T>, ex) in the context of modules or commands.
 * A {@code get(Class)} method is provided, allowing the access of entries
 * by their class type.
 *
 * @see Loadable
 * @see Saveable
 *
 * @param <T> The type of entries that this manager holds
 *
 * @author Brady
 * @since 1/19/2017 12:00 PM
 */
public abstract class Manager<T> extends ArrayList<T> implements Loadable, Saveable {

    /**
     * Cache of classes and their corresponding entries
     */
    private final Map<Class<T>, T> classCache = new HashMap<>();

    /**
     * The name of the Manager
     */
    private final String name;

    public Manager(String name) {
        this.name = name;
    }

    /**
     * Retrieves an entry by the entry's class
     *
     * @param clazz The class
     * @return The retrieved entry
     */
    @SuppressWarnings("unchecked")
    public final <I extends T> I get(Class<I> clazz) {
        if (clazz == null)
            return null;

        return (I) classCache.computeIfAbsent((Class<T>) clazz,
                c -> this.stream().filter(data -> data.getClass().equals(c)).findFirst().orElse(null));
    }

    /**
     * @return The name of this manager
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Adds all of the elements specified
     *
     * @param elements The elements
     * @return If this manager's entries changed as a result of this call
     */
    public final boolean addAll(T... elements) {
        if (elements.length == 0)
            return false;

        return this.addAll(Arrays.asList(elements));
    }
}
