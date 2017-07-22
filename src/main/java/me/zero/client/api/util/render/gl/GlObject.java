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

package me.zero.client.api.util.render.gl;

/**
 * Representation of an Object in OpenGL
 *
 * @author Brady
 * @since 7/22/2017 4:18 PM
 */
public abstract class GlObject {

    public static final int UNABLE_TO_GENERATE = 0;
    public static final int NOT_GENERATED = -1;

    private int id = NOT_GENERATED;

    /**
     * Generates this OpenGL Object. Return value reflects
     * whether or not the operation was a success or not.
     *
     * @return Whether or not the operation was a success.
     *         May return 'false' if
     */
    public final boolean gen() {
        return !isGen() && (id = nativeGen()) != UNABLE_TO_GENERATE;
    }

    /**
     * Called by GlObject#gen() to generate this object.
     *
     * @return The ID of the (possibly) created object
     */
    protected abstract int nativeGen();

    /**
     * Deletes this object from memory.
     *
     * @return Whether or not the operation was a success
     */
    public final boolean delete() {
        if (!isGen())
            return false;

        id = NOT_GENERATED;
        nativeDelete();
        return true;
    }

    /**
     * Called by GlObject#delete() to delete this object from memory.
     */
    protected abstract void nativeDelete();

    /**
     * @return The ID of this object
     */
    public final int id() {
        return this.id;
    }

    /**
     * @return Whether or not the object has been successfully generated
     */
    public final boolean isGen() {
        return id > 0;
    }
}
