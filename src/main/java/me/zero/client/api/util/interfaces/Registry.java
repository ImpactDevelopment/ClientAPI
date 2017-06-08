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

package me.zero.client.api.util.interfaces;

/**
 * Sort of the offspring of a Manager and a Map.
 *
 * @see me.zero.client.api.manage.Manager
 * @see java.util.Map
 *
 * @author Brady
 * @since 2/9/2017 12:00 PM
 */
public interface Registry<K, V> {

    /**
     * Loads an Object into the Registry
     *
     * @param obj Object being loaded
     */
    void load(K obj);

    /**
     * Unloads an Object from the Registry
     *
     * @param obj Object being unloaded
     */
    void unload(K obj);

    /**
     * Unloads, and then reloads an Object in the Registry
     *
     * @param obj Object being reloaded
     */
    default void reload(K obj) { unload(obj); reload(obj); }

    /**
     * @param obj The key
     * @return The Value in the Registry belonging to the Key
     */
    V getEntry(K obj);

    /**
     * @param obj The key
     * @return Whether or not the Key has a Value in the Registry
     */
    boolean hasEntry(K obj);
}
