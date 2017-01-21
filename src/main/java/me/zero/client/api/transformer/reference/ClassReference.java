package me.zero.client.api.transformer.reference;

import javassist.bytecode.Descriptor;
import me.zero.client.api.transformer.reference.obfuscation.Obfuscation;
import me.zero.client.api.transformer.reference.obfuscation.ObfuscationName;

import java.lang.reflect.Type;

/**
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public class ClassReference extends Reference {

    public ClassReference(Type type) {
        this(from(type));
    }

    public ClassReference(ObfuscationName[] names) {
        super(names);
    }

    public String getDescriptor() {
        return Descriptor.of(this.getName());
    }

    private static ObfuscationName[] from(Type type) {
        Obfuscation[] types = Obfuscation.values();
        ObfuscationName[] names = new ObfuscationName[types.length];
        for (int i = 0; i < types.length; i++)
            names[i] = ObfuscationName.from(types[i], type.getTypeName());
        return names;
    }
}
