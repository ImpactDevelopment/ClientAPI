package me.zero.client.api.transformer.reference;

import me.zero.client.api.exception.UnexpectedOutcomeException;
import me.zero.client.api.transformer.reference.obfuscation.Obfuscation;
import me.zero.client.api.transformer.reference.obfuscation.ObfuscationName;

/**
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public abstract class Reference {

    private final ObfuscationName[] names;

    Reference(ObfuscationName[] names) {
        this.names = names;
    }

    public ObfuscationName[] getNames() {
        return this.names;
    }

    public final String getName() {
        for (ObfuscationName name : this.getNames()) {
            if (name.getType() == Obfuscation.getType()) {
                return name.getName();
            }
        }
        throw new UnexpectedOutcomeException("Name not found for current Obfuscation state");
    }
}
