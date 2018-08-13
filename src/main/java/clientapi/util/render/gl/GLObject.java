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

package clientapi.util.render.gl;

/**
 * Representation of an Object in OpenGL
 *
 * @author Brady
 * @since 7/22/2017
 */
public abstract class GLObject {

    /**
     * ID indicating that generation was attempted, but it was unsuccessful.
     */
    public static final int UNABLE_TO_GENERATE = 0;

    /**
     * ID indicating that the object has not yet been generated.
     */
    public static final int NOT_GENERATED = -1;

    private int id = NOT_GENERATED;

    /**
     * Generates this OpenGL Object. Return value reflects
     * whether or not the operation was a success or not.
     *
     * @return Whether or not the operation was a success.
     *         May return 'false' if the object has already
     *         been generated or the object was unable to generate.
     */
    public final boolean gen() {
        return !isGen() && (id = nativeGen()) != UNABLE_TO_GENERATE;
    }

    /**
     * Called by GLObject#gen() to generate this object.
     *
     * @return The ID of the (possibly) created object
     */
    protected abstract int nativeGen();

    /**
     * Deletes this object from memory. This should be called
     * when the application is shutting down to efficiently
     * garbage collect memory.
     *
     * @return Whether or not the operation was a success
     */
    public boolean delete() {
        if (!isGen())
            return false;

        id = NOT_GENERATED;
        nativeDelete();
        return true;
    }

    /**
     * Called by GLObject#delete() to delete this object from memory.
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
