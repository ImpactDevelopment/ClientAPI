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

package clientapi.load;

import clientapi.load.transform.ITransformer;
import clientapi.load.transform.impl.ValueAccessorTransformer;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementation of {@code IClassTransformer} used to run all {@code ITransformer}s.
 *
 * @author Brady
 * @since 9/15/2017
 */
public final class ClientTransformer implements IClassTransformer {

    /**
     * The instance of {@link ClientTransformer}
     */
    private static ClientTransformer instance;

    /**
     * A {@link List} of all of the transformers
     */
    private final List<ITransformer> transformers = new ArrayList<>();

    public ClientTransformer() {
        // Set the instance
        instance = this;

        // Register the default transformers
        this.transformers.add(new ValueAccessorTransformer());
    }

    @Override
    public final byte[] transform(String name, String transformedName, byte[] basicClass) {
        List<ITransformer> transformers = getTransformers(transformedName);

        if (!transformers.isEmpty()) {
            try {
                ClassNode cn = getClassNode(basicClass);
                if (cn == null)
                    return basicClass;

                // Run all transformers on the Class
                transformers.forEach(transformer -> transformer.transform(cn));

                // Return transformed class bytecode
                ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                cn.accept(cw);
                return cw.toByteArray();
            } catch (Exception ignored) {}
        }

        return basicClass;
    }

    /**
     * Registers all of the specified transformer classpaths.
     *
     * @param transformers The transformer classpaths
     */
    final void registerAll(String... transformers) {
        Arrays.stream(transformers)
                .map(ITransformer::create)
                .filter(Objects::nonNull)
                .forEach(this.transformers::add);
    }

    /**
     * Finds transformers that target the specified class.
     * If a transformer doesn't have any targets, it is assumed
     * that it will accept any class.
     *
     * @param name Target class name
     * @return Valid transformers
     */
    private List<ITransformer> getTransformers(String name) {
        return transformers.stream()
                .filter(transformer -> transformer.getTargets().length == 0 || ArrayUtils.contains(transformer.getTargets(), name))
                .collect(Collectors.toList());
    }

    /**
     * Creates a {@code ClassNode} from specified bytecode
     *
     * @param bytecode Class bytecode
     * @return ClassNode
     */
    private ClassNode getClassNode(byte[] bytecode) {
        if (bytecode == null)
            return null;

        ClassNode cn = new ClassNode();
        new ClassReader(bytecode).accept(cn, 0);
        return cn;
    }

    /**
     * @return The instance of {@link ClientTransformer}
     */
    public static ClientTransformer getInstance() {
        return instance;
    }
}
