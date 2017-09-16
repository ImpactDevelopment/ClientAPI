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

package clientapi.load.transform;

import org.objectweb.asm.tree.ClassNode;

/**
 * Basic class transformer. Can optionally target specific classes. Only
 * used to perform code injections that Mixins are incapable of.
 *
 * @author Brady
 * @since 9/15/2017 9:39 PM
 */
public interface ITransformer {

    /**
     * Transforms the specified {@code ClassNode}
     *
     * @param cn The target {@code ClassNode}
     */
    void transform(ClassNode cn);

    /**
     * Returns the target class names. If none are returned,
     * then it is assumed that the transformer is targetting
     * all classes.
     *
     * @return Target class names
     */
    default String[] getTargets() {
        return new String[0];
    }
}
