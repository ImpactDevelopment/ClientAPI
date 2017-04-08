package me.zero.client.api.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Used to hash data with a defined algorithm
 *
 * @since 1.0
 *
 * Created by Brady on 2/25/2017.
 */
public final class Hash {

    /**
     * Algorithm of this Hash object
     */
    private final String algorithm;

    private Hash(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Creates a new Hash object with the specified algorithm
     *
     * @since 1.0
     *
     * @param algorithm Algorithm name
     * @return Hash object
     */
    public static Hash of(String algorithm) {
        return new Hash(algorithm);
    }

    /**
     * Hashes a piece of data with the algorithm
     *
     * @param data Data being hashed
     * @return Hashed data
     */
    public final String hash(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(data.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes)
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            return sb.toString();
        } catch (NoSuchAlgorithmException e2) {
            return null;
        }
    }
}
