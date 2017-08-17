/*
 * Copyright 2017 ZeroMemes
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

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

/**
 * @author Brady
 * @since 7/22/2017 10:16 PM
 */
public final class Query extends GLObject {

    private final int target;

    public Query(int target) {
        this.target = target;
    }

    @Override
    protected int nativeGen() {
        return glGenQueries();
    }

    @Override
    protected void nativeDelete() {
        glDeleteQueries(id());
    }

    /**
     * Marks the starting bounds of the query scope
     */
    public final void start() {
        glBeginQuery(target, id());
    }

    /**
     * Marks the stopping bounds of the query scope
     */
    public final void stop() {
        glEndQuery(target);
    }

    /**
     * Returns the result of the query, the result
     * will vary based on the query target.
     *
     * @return The result of the query.
     */
    public final int getResult() {
        return glGetQueryObjecti(target, GL_QUERY_RESULT);
    }

    /**
     * @return Whether or not the result of the query is available yet
     */
    public final boolean isResultAvailable() {
        return glGetQueryObjecti(target, GL_QUERY_RESULT_AVAILABLE) == GL_TRUE;
    }
}
