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
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brady
 * @since 9/13/2017 10:24 PM
 */
public final class ValueAccessorTransformer implements IClassTransformer {

    private final Map<String, FieldNode> fieldCache = new HashMap<String, FieldNode>();

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        ClassNode cn = new ClassNode();
        ClassReader cr = new ClassReader(basicClass);
        cr.accept(cn, 0);

        // Clear the cache for the new class
        fieldCache.clear();

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

        if (fieldCache.size() > 0) {
            cn.interfaces.add("clientapi/value/holder/ValueAccessor");

            MethodNode mn = new MethodNode(Opcodes.ACC_PUBLIC, "getFieldValue", "(Ljava/lang/String;)Ljava/lang/Object;", null, null);

            fieldCache.forEach((id, fn) -> {
                LabelNode match = new LabelNode();

                mn.instructions.add(new VarInsnNode(Opcodes.ALOAD, 1));
                mn.instructions.add(new LdcInsnNode(id));
                mn.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false));
                mn.instructions.add(new JumpInsnNode(Opcodes.IFEQ, match));
                mn.instructions.add(new LabelNode());

                mn.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
                mn.instructions.add(new FieldInsnNode(Opcodes.GETFIELD, cn.name, fn.name, fn.desc));

                String valueOf = null;
                switch (fn.desc) {
                    case "B":
                        valueOf = "java/lang/Byte";
                        break;
                    case "C":
                        valueOf = "java/lang/Char";
                        break;
                    case "D":
                        valueOf = "java/lang/Double";
                        break;
                    case "F":
                        valueOf = "java/lang/Float";
                        break;
                    case "I":
                        valueOf = "java/lang/Integer";
                        break;
                    case "J":
                        valueOf = "java/lang/Long";
                        break;
                    case "S":
                        valueOf = "java/lang/Short";
                        break;
                    case "Z":
                        valueOf = "java/lang/Boolean";
                }
                if (valueOf != null)
                    mn.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, valueOf, "valueOf", "(" + fn.desc + ")L" + valueOf + ";", false));

                mn.instructions.add(new InsnNode(Opcodes.ARETURN));
                mn.instructions.add(match);

            });
            mn.instructions.add(new InsnNode(Opcodes.ACONST_NULL));
            mn.instructions.add(new InsnNode(Opcodes.ARETURN));

            cn.methods.add(mn);

            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            cn.accept(cw);
            return cw.toByteArray();
        }

        return basicClass;
    }
}
