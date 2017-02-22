package me.zero.client.load.transformer.reference;

import me.zero.client.api.exception.UnexpectedOutcomeException;
import me.zero.client.load.transformer.reference.obfuscation.Obfuscation;
import me.zero.client.load.transformer.reference.obfuscation.ObfuscationName;

import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * The base for References
 *
 * @see me.zero.client.load.transformer.reference.ClassReference
 * @see me.zero.client.load.transformer.reference.MethodReference
 * @see me.zero.client.load.transformer.reference.FieldReference
 *
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
abstract class Reference {

    /**
     * The names for this reference
     */
    private final ObfuscationName[] names;

    Reference(ObfuscationName[] names) {
        this.names = names;
    }

    /**
     * @since 1.0
     *
     * @return Names belonging to this reference
     */
    public ObfuscationName[] getNames() {
        return this.names;
    }

    /**
     * Gets the name based on the {@code Obfuscation} State of the code
     *
     * @since 1.0
     *
     * @see me.zero.client.load.transformer.reference.obfuscation.Obfuscation
     *
     * @return
     */
    public final String getName() {
        return this.getName(Obfuscation.getType());
    }

    /**
     * Gets the name based from the specified obfuscation state
     *
     * @since 1.0
     *
     * @see me.zero.client.load.transformer.reference.obfuscation.Obfuscation
     *
     * @return
     */
    public final String getName(Obfuscation obfuscation) {
        String name = Arrays.stream(this.getNames()).filter(obfName -> obfName.getType() == obfuscation).findFirst().orElse(null).getName();
        if (name == null)
            throw new UnexpectedOutcomeException("Name not found for Obfuscation state");
        return name;
    }

    /**
     * Creates an Array of {@code ObfuscationNames} for
     * each obfuscation type based on the Type inputted.
     *
     * @since 1.0
     *
     * @see me.zero.client.load.transformer.reference.obfuscation.ObfuscationName
     *
     * @param type The Type
     * @return An Array of ObfuscationNames
     */
    protected static ObfuscationName[] from(Type type) {
        Obfuscation[] types = Obfuscation.values();
        ObfuscationName[] names = new ObfuscationName[types.length];
        for (int i = 0; i < types.length; i++)
            names[i] = ObfuscationName.from(types[i], type.getTypeName());
        return names;
    }
}
