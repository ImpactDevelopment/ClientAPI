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

import org.objectweb.asm.Handle;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

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

    /**
     * Returns the object representation of a primitive
     * type, if the specified description is a primitive type
     * otherwise, the input value is returned.
     *
     * @param desc The description of the type
     * @return The object representation, if primitive
     */
    default String getObject(String desc) {
        switch (desc) {
            case "B":
                return "java/lang/Byte";
            case "C":
                return "java/lang/Char";
            case "D":
                return "java/lang/Double";
            case "F":
                return "java/lang/Float";
            case "I":
                return "java/lang/Integer";
            case "J":
                return "java/lang/Long";
            case "S":
                return "java/lang/Short";
            case "Z":
                return "java/lang/Boolean";
        }
        return desc;
    }

    /**
     * Returns the {@code Class} name of the specified primitive type description
     *
     * @param desc Primitive type description
     * @return Type class name, null if not primitive
     */
    default String getClassName(String desc) {
        return desc.length() == 1 ? Type.getType(desc).getClassName() : null;
    }

    /**
     * Removes the L prefix and ; suffix from a type descriptor
     *
     * @param desc Type descriptor
     * @return The formatted descriptor
     */
    default String getStrippedDesc(String desc) {
        if (desc.startsWith("L") && desc.endsWith(";"))
            // removes the "L" and ";" from the descriptor
            return desc.substring(1, desc.length() - 1);
        else
            return desc;
    }

    /**
     * Creates a handle for a method
     *
     * @param tag The handle tag
     * @param owner The method container
     * @param method The method
     */
    default Handle createMethodHandle(int tag, ClassNode owner, MethodNode method) {
        return createMethodHandle(tag, owner.name, method.name, method.desc);
    }

    /**
     * Creates a handle for a method
     *
     * @param tag The handle tag
     * @param owner The method container
     * @param method The method
     */
    default Handle createMethodHandle(int tag, String owner, MethodNode method) {
        return createMethodHandle(tag, owner, method.name, method.desc);
    }

    /**
     * Creates a handle for a method
     *
     * @param tag The handle tag
     * @param owner The method container
     * @param method The method name
     * @param desc The method description
     */
    default Handle createMethodHandle(int tag, ClassNode owner, String method, String desc) {
        return createMethodHandle(tag, owner.name, method, desc);
    }

    /**
     * Creates a handle for a method
     *
     * @param tag The handle tag
     * @param owner The method container
     * @param method The method name
     * @param desc The method description
     */
    default Handle createMethodHandle(int tag, String owner, String method, String desc) {
        return new Handle(tag, owner, method, desc);
    }
}
