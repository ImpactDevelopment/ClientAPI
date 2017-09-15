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

package clientapi.load;

import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author Brady
 * @since 9/13/2017 10:24 PM
 */
public final class ValueAccessorTransformer implements IClassTransformer {

    /**
     * Instance of the handle for {@code LambdaMetaFactory#metafactory(MethodHandles.Lookup, String, MethodType, MethodType, MethodHandle, MethodType)}
     */
    private final static Handle METAFACTORY = new Handle(
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
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        ClassNode cn = new ClassNode();
        ClassReader cr = new ClassReader(basicClass);
        cr.accept(cn, 0);

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

        if (fieldCache.isEmpty() || cn.interfaces.contains("clientapi/value/holder/ValueAccessor"))
            return basicClass;

        cn.interfaces.add("clientapi/value/holder/ValueAccessor");

        createFieldGetter(cn);
        createFieldSetter(cn);

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);
        try {
            FileUtils.writeByteArrayToFile(new File("Generated" + System.nanoTime() + ".class"), cw.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cw.toByteArray();
    }

    private void createFieldGetter(ClassNode cn) {
        MethodNode mn = new MethodNode(ACC_PUBLIC | ACC_FINAL, "getFieldGetter", "(Ljava/lang/String;)Ljava/util/function/Supplier;", null, null);

        fieldCache.forEach((id, fn) -> {
            MethodNode handle; {
                // Create lambda bootstrap method
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

                // Add the bootstrap method to the class
                cn.methods.add(handle);
            }

            // Create lambda
            Label skip = new Label();
            mn.visitVarInsn(ALOAD, 1);
            mn.visitLdcInsn(id);
            mn.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
            mn.visitJumpInsn(IFEQ, skip);
            mn.visitVarInsn(ALOAD, 0);
            mn.visitInvokeDynamicInsn("get", "(L" + cn.name + ";)Ljava/util/function/Supplier;",
                    METAFACTORY,
                    Type.getMethodType("()Ljava/lang/Object;"),
                    new Handle(H_INVOKESPECIAL, cn.name, handle.name, handle.desc),
                    Type.getMethodType("()Ljava/lang/Object;")
            );
            mn.visitInsn(ARETURN);
            mn.visitLabel(skip);
        });
        mn.visitInsn(ACONST_NULL);
        mn.visitInsn(ARETURN);

        cn.methods.add(mn);
    }

    private void createFieldSetter(ClassNode cn) {
        MethodNode mn = new MethodNode(ACC_PUBLIC | ACC_FINAL, "getFieldSetter", "(Ljava/lang/String;)Ljava/util/function/Consumer;", null, null);

        fieldCache.forEach((id, fn) -> {
            MethodNode handle; {
                // Create lambda bootstrap method
                handle = new MethodNode(ACC_PRIVATE | ACC_SYNTHETIC, "lambda$getFieldSetter$" + current++, "(Ljava/lang/Object;)V", null, null);

                handle.visitVarInsn(ALOAD, 0);
                handle.visitVarInsn(ALOAD, 1);
                handle.visitTypeInsn(CHECKCAST, format(getObject(fn.desc)));

                // If the field is a primitive type, get the primitive value
                String object = getObject(fn.desc);
                if (!object.equals(fn.desc))
                    handle.visitMethodInsn(INVOKEVIRTUAL, object, getName(fn.desc) + "Value", "()" + fn.desc, false);

                // Set the field value
                handle.visitFieldInsn(PUTFIELD, cn.name, fn.name, fn.desc);
                handle.visitInsn(RETURN);

                // Add the bootstrap method to the class
                cn.methods.add(handle);
            }

            // Create lambda
            Label skip = new Label();
            mn.visitVarInsn(ALOAD, 1);
            mn.visitLdcInsn(id);
            mn.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
            mn.visitJumpInsn(IFEQ, skip);
            mn.visitVarInsn(ALOAD, 0);
            mn.visitInvokeDynamicInsn("accept", "(L" + cn.name + ";)Ljava/util/function/Consumer;",
                    METAFACTORY,
                    Type.getMethodType("(Ljava/lang/Object;)V"),
                    new Handle(H_INVOKESPECIAL, cn.name, handle.name, handle.desc),
                    Type.getMethodType("(Ljava/lang/Object;)V")
            );
            mn.visitInsn(ARETURN);
            mn.visitLabel(skip);
        });
        mn.visitInsn(ACONST_NULL);
        mn.visitInsn(ARETURN);

        cn.methods.add(mn);
    }

    private String getObject(String desc) {
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
        return format(desc);
    }

    private String getName(String desc) {
        switch (desc) {
            case "B":
                return "byte";
            case "C":
                return "char";
            case "D":
                return "double";
            case "F":
                return "float";
            case "I":
                return "int";
            case "J":
                return "long";
            case "S":
                return "short";
            case "Z":
                return "boolean";
        }
        return null;
    }

    private String format(String desc) {
        if (desc.startsWith("L") && desc.endsWith(";"))
            // removes the "L" and ";" from the descriptor
            return desc.substring(1, desc.length() - 1);
        else
            return desc;
    }
}
