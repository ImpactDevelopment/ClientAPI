package me.zero.client.api.transformer.reference.obfuscation;

/**
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public enum Obfuscation {

    MCP, SEARGE, VANILLA, UNKNOWN;

    private static Obfuscation type = Obfuscation.UNKNOWN;

    static {
        // Determine Obfuscation State
    }

    public static Obfuscation getType() {
        return type;
    }
}
