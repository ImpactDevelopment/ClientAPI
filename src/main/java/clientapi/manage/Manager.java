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

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Manager used to store arrays of information.
 * Just a way to keep things consistent.
 *
 * @see Loadable
 * @see Saveable
 *
 * @author Brady
 * @since 1/19/2017 12:00 PM
 */
public abstract class Manager<T> implements Collection<T>, Loadable, Saveable {

    /**
     * The list of all of the entries that this Manager contains
     */
    private final List<T> data = new ArrayList<>();

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
     * Resets all of the data in this manager
     */
    public final void reset(){
        this.data.clear();
    }
    /**
     * Adds multiple entries into the data array
     *
     * @param data The entries
     */
    @SafeVarargs
    protected final void addData(T... data) {
        this.data.addAll(Arrays.asList(data));
    }

    /**
     * Adds a list of entries into the data array
     *
     * @param data The entries
     */
    protected final void addData(List<T> data) {
        this.data.addAll(data);
    }

    /**
     * Removes an entry, if present, from the data array
     *
     * @param data The entry
     */
    protected final void removeData(T data) {
        this.data.remove(data);
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
                c -> this.data.stream().filter(data -> data.getClass().equals(c)).findFirst().orElse(null));
    }

    /**
     * @return The name of this manager
     */
    public final String getName() {
        return this.name;
    }

    @Override
    public final int size() {
        return this.data.size();
    }

    @Override
    public final boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public final boolean contains(Object o) {
        return this.data.contains(o);
    }

    @Override
    public final Object[] toArray() {
        return this.data.toArray();
    }

    @Override
    public final <T1> T1[] toArray(T1[] a) {
        return this.data.toArray(a);
    }

    @Override
    public final boolean add(T t) {
        return this.data.add(t);
    }

    @Override
    public final boolean remove(Object o) {
        return this.data.remove(o);
    }

    @Override
    public final boolean containsAll(Collection<?> c) {
        return this.data.containsAll(c);
    }

    @Override
    public final boolean addAll(Collection<? extends T> c) {
        return this.data.addAll(c);
    }

    @Override
    public final boolean removeAll(Collection<?> c) {
        return this.data.removeAll(c);
    }

    @Override
    public final boolean retainAll(Collection<?> c) {
        return this.data.retainAll(c);
    }

    @Override
    public final void clear() {
        this.data.clear();
    }

    @Override
    public final Iterator<T> iterator() {
        return this.data.iterator();
    }
}
