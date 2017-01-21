package me.zero.client.api.transformer.reference.obfuscation;

/**
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public class ObfuscationName {

    private Obfuscation type;
    private String name;

    private ObfuscationName(Obfuscation type, String name) {
        this.type = type;
        this.name = name;
    }

    public Obfuscation getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public static ObfuscationName from(Obfuscation type, String name) {
        return new ObfuscationName(type, name);
    }
}
