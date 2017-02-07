package me.zero.client.load.inject.transformer.reference.obfuscation;

/**
 * An Object to pair a name with a type of Obfuscation
 *
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public class ObfuscationName {

    /**
     * The Type of Obfuscation this name belongs to
     */
    private Obfuscation type;

    /**
     * The name
     */
    private String name;

    private ObfuscationName(Obfuscation type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * @since 1.0
     *
     * @return The Type of Obfuscation that the Name belongs to
     */
    public Obfuscation getType() {
        return this.type;
    }

    /**
     * @since 1.0
     *
     * @return The Name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Creates a new instance of an ObfuscationName.
     * Used to clean things up in files containing multiple references.
     *
     * @since 1.0
     *
     * @param type Type of obfuscation
     * @param name Name
     * @return An {@code ObfuscationName} containing the type and name
     */
    public static ObfuscationName from(Obfuscation type, String name) {
        return new ObfuscationName(type, name);
    }
}
