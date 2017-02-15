package me.zero.client.load.transformer.reference.obfuscation;

/**
 * Used to manage obfuscation states
 *
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public enum Obfuscation {

    /**
     * Types of Obfuscation
     */
    MCP, SEARGE, VANILLA, UNKNOWN;

    /**
     * The Current type being used by the Minecraft Process
     */
    private static Obfuscation type = Obfuscation.UNKNOWN;

    static {
        // Determine Obfuscation State
        // Automatically MCP for testing purposes
        type = Obfuscation.MCP;
    }

    /**
     * @since 1.0
     *
     * @return Current type of Obfuscation being used by the Minecraft Process
     */
    public static Obfuscation getType() {
        return type;
    }
}
