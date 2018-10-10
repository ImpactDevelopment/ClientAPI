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

package clientapi.load.argument;

import java.util.List;

/**
 * A standard launch argument
 *
 * @author Brady
 * @since 10/9/2018
 */
public interface Argument {

    /**
     * Adds the "components" of this argument to the specified list.
     *
     * @see ClassifiedArgument#addToList(List)
     * @see SingularArgument#addToList(List)
     *
     * @param arguments The argument list
     */
    void addToList(List<String> arguments);

    /**
     * Returns whether or not this argument conflicts with the specified one. In the context
     * of singular arguments, this means that the singular argument itself matches, in the context
     * of classified arguments, this means that the classifier matches.
     *
     * @param argument Another argument
     * @return Whether or not this argument conflicts
     */
    boolean conflicts(Argument argument);
}
