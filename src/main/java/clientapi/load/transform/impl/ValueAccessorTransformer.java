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

package clientapi.load.transform.impl;

import clientapi.load.transform.ITransformer;
import clientapi.value.holder.ValueAccessor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.objectweb.asm.Opcodes.*;

/**
 * Transforms classes containing Labeled fields to create
 * Consumers and Suppliers to directly access the fields
 * without the need for reflections.
 *
 * @see ValueAccessor
 *
 * @author Brady
 * @since 9/13/2017 10:24 PM
 */
public final class ValueAccessorTransformer implements ITransformer {

    /**
     * Instance of the handle for {@code LambdaMetaFactory#metafactory(MethodHandles.Lookup, String, MethodType, MethodType, MethodHandle, MethodType)}
     */
    private static final Handle METAFACTORY = new Handle(
            H_INVOKESTATIC,
            "java/lang/invoke/LambdaMetafactory",
            "metafactory",
            "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
    );

    /**
     * Map of value ids and their respective field nodes for the current class
     * being "operated" on. Reset after each class is processed.
     */
    private final Map<String, FieldNode> fieldCache = new LinkedHashMap<>();

    /**
     * Current Lambda bootstrap method index. Reset after each class is processed.
     */
    private int current;

    @Override
    public void transform(ClassNode cn) {
        // Don't process the class if it already implements ValueAccessor
        if (cn.interfaces.contains("clientapi/value/holder/ValueAccessor"))
            return;

        // Clear the cache for the new class
        fieldCache.clear();
        current = 0;

        // Assume that any values annotated with @Label should be given accessors
        for (FieldNode fn : cn.fields) {
            if (fn.visibleAnnotations != null) {
                for (AnnotationNode an : fn.visibleAnnotations) {
                    if (an.desc.equals("Lclientapi/util/annotation/Label;")) {
                        for (int i = 0; i < an.values.size(); i++) {
                            if (i % 2 == 0 && an.values.get(i).equals("id"))
                                fieldCache.put(an.values.get(i + 1).toString(), fn);
                        }
                    }
                }
            }
        }

        // Don't process the class if there weren't any labeled fields
        if (fieldCache.isEmpty())
            return;

        // Add the ValueAccessor interface and implement the required methods
        cn.interfaces.add("clientapi/value/holder/ValueAccessor");
        createFieldGetter(cn);
        createFieldSetter(cn);
    }

    /**
     * Creates the field-getter getter method in the specified {@code ClassNode}
     *
     * @param cn The ClassNode
     */
    private void createFieldGetter(ClassNode cn) {
        MethodNode mn = new MethodNode(ACC_PUBLIC | ACC_FINAL, "getFieldGetter", "(Ljava/lang/String;)Ljava/util/function/Supplier;", null, null);

        // Create a check for all labeled fields in the cache
        fieldCache.forEach((id, fn) -> {
            MethodNode handle; {
                // Create lambda handle method
                handle = new MethodNode(ACC_PRIVATE | ACC_SYNTHETIC, "lambda$getFieldGetter$" + current++, "()Ljava/lang/Object;", null, null);

                // Get the field value
                handle.visitVarInsn(ALOAD, 0);
                handle.visitFieldInsn(GETFIELD, cn.name, fn.name, fn.desc);

                // If the field is a primitive type, get the object representation
                String object = getObject(fn.desc);
                if (!object.equals(fn.desc))
                    handle.visitMethodInsn(INVOKESTATIC, object, "valueOf", "(" + fn.desc + ")L" + object + ";", false);

                // Return the value
                handle.visitInsn(ARETURN);

                // Add the handle method to the class
                cn.methods.add(handle);
            }

            // Create label for IF statement jump
            Label skip = new Label();

            // Compare the target value with the expected valu
            mn.visitVarInsn(ALOAD, 1);
            mn.visitLdcInsn(id);
            mn.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);

            // Jump if the input doesn't match the expected value
            mn.visitJumpInsn(IFEQ, skip);

            // Return the getter
            mn.visitVarInsn(ALOAD, 0);
            mn.visitInvokeDynamicInsn("get", "(L" + cn.name + ";)Ljava/util/function/Supplier;",
                    // Define the bootstrap method
                    METAFACTORY,
                    // Fill the remaining 3 args for the method
                    Type.getMethodType("()Ljava/lang/Object;"),
                    createMethodHandle(H_INVOKESPECIAL, cn, handle),
                    Type.getMethodType("()Ljava/lang/Object;")
            );
            mn.visitInsn(ARETURN);

            // Indicate where the IF statement should jump to if it fails
            mn.visitLabel(skip);
        });
        mn.visitInsn(ACONST_NULL);
        mn.visitInsn(ARETURN);

        cn.methods.add(mn);
    }

    /**
     * Creates the field-setter getter method in the specified {@code ClassNode}
     *
     * @see ValueAccessor
     *
     * @param cn The ClassNode
     */
    private void createFieldSetter(ClassNode cn) {
        MethodNode mn = new MethodNode(ACC_PUBLIC | ACC_FINAL, "getFieldSetter", "(Ljava/lang/String;)Ljava/util/function/Consumer;", null, null);

        // Create a check for all labeled fields in the cache
        fieldCache.forEach((id, fn) -> {
            MethodNode handle; {
                // Create lambda handle method
                handle = new MethodNode(ACC_PRIVATE | ACC_SYNTHETIC, "lambda$getFieldSetter$" + current++, "(Ljava/lang/Object;)V", null, null);

                handle.visitVarInsn(ALOAD, 0);
                handle.visitVarInsn(ALOAD, 1);
                handle.visitTypeInsn(CHECKCAST, getStrippedDesc(getObject(fn.desc)));

                // If the field is a primitive type, get the primitive value
                String object = getObject(fn.desc);
                if (!object.equals(fn.desc))
                    handle.visitMethodInsn(INVOKEVIRTUAL, object, getClassName(fn.desc) + "Value", "()" + fn.desc, false);

                // Set the field value
                handle.visitFieldInsn(PUTFIELD, cn.name, fn.name, fn.desc);
                handle.visitInsn(RETURN);

                // Add the handle method to the class
                cn.methods.add(handle);
            }

            // Create label for IF statement jump
            Label skip = new Label();

            // Compare the target value with the expected value
            mn.visitVarInsn(ALOAD, 1);
            mn.visitLdcInsn(id);
            mn.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);

            // Jump if the input doesn't match the expected value
            mn.visitJumpInsn(IFEQ, skip);

            // Return the setter
            mn.visitVarInsn(ALOAD, 0);
            mn.visitInvokeDynamicInsn("accept", "(L" + cn.name + ";)Ljava/util/function/Consumer;",
                    // Define the bootstrap method
                    METAFACTORY,
                    // Fill the remaining 3 args for the method
                    Type.getMethodType("(Ljava/lang/Object;)V"),
                    createMethodHandle(H_INVOKESPECIAL, cn, handle),
                    Type.getMethodType("(Ljava/lang/Object;)V")
            );
            mn.visitInsn(ARETURN);

            // Indicate where the IF statement should jump to if it fails
            mn.visitLabel(skip);
        });
        mn.visitInsn(ACONST_NULL);
        mn.visitInsn(ARETURN);

        cn.methods.add(mn);
    }
}
