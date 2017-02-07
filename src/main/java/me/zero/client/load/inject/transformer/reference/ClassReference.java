package me.zero.client.load.inject.transformer.reference;

import javassist.bytecode.Descriptor;
import me.zero.client.load.inject.transformer.reference.obfuscation.Obfuscation;
import me.zero.client.load.inject.transformer.reference.obfuscation.ObfuscationName;

import java.lang.reflect.Type;

/**
 * Type of {@code Reference} designed for Classes.
 *
 * @see me.zero.client.load.inject.transformer.reference.MethodReference
 *
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public final class ClassReference extends Reference {

    public ClassReference(Type type) {
        this(from(type));
    }

    public ClassReference(ObfuscationName[] names) {
        super(names);
    }

    /**
     * @since 1.0
     *
     * @return Class
     */
    public String getDescriptor() {
        return Descriptor.of(this.getName());
    }

    /**
     * Creates an Array of {@code ObfuscationNames} for
     * each obfuscation type based on the Type inputted.
     *
     * @since 1.0
     *
     * @see me.zero.client.load.inject.transformer.reference.obfuscation.ObfuscationName
     *
     * @param type The Type
     * @return An Array of ObfuscationNames
     */
    private static ObfuscationName[] from(Type type) {
        Obfuscation[] types = Obfuscation.values();
        ObfuscationName[] names = new ObfuscationName[types.length];
        for (int i = 0; i < types.length; i++)
            names[i] = ObfuscationName.from(types[i], type.getTypeName());
        return names;
    }
}
