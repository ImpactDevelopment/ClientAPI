package me.zero.client.load.transformer.reference;

import me.zero.client.load.transformer.reference.obfuscation.ObfuscationName;

/**
 * Type of {@code Reference} designed for Classes.
 *
 * @see me.zero.client.load.transformer.reference.ClassReference
 * @see me.zero.client.load.transformer.reference.MethodReference
 *
 * @since 1.0
 *
 * Created by Brady on 2/22/2017.
 */
public final class FieldReference extends Reference {

    public FieldReference(ObfuscationName[] names) {
        super(names);
    }

    /**
     * Creates method body for return statement.
     *
     * @since 1.0
     */
    public String createReturn() {
        return String.format("return this.%s;", this.getName());
    }
}
