package me.zero.client.api.transformer.reference;

import me.zero.client.api.exception.UnexpectedOutcomeException;
import me.zero.client.api.transformer.reference.obfuscation.Obfuscation;
import me.zero.client.api.transformer.reference.obfuscation.ObfuscationName;

/**
 * The base for References
 *
 * @see me.zero.client.api.transformer.reference.ClassReference
 * @see me.zero.client.api.transformer.reference.MethodReference
 *
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public abstract class Reference {

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
     * @see me.zero.client.api.transformer.reference.obfuscation.Obfuscation
     *
     * @return
     */
    public final String getName() {
        for (ObfuscationName name : this.getNames()) {
            if (name.getType() == Obfuscation.getType()) {
                return name.getName();
            }
        }
        throw new UnexpectedOutcomeException("Name not found for current Obfuscation state");
    }
}
