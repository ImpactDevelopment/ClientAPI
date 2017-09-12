/*
 * Copyright 2017 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.util.security;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * Used to hash data with a defined algorithm
 *
 * @author Brady
 * @since 2/25/2017 12:00 PM
 */
public final class Hash {

    /**
     * Cache of all Hash instances
     */
    private static final Map<String, Hash> HASH_CACHE = new HashMap<>();

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
     * @param algorithm Algorithm name
     * @return Hash object
     */
    public static Hash of(String algorithm) {
        return HASH_CACHE.computeIfAbsent(algorithm, Hash::new);
    }

    /**
     * Hashes a string with the algorithm
     *
     * @param data String being hashed
     * @param format The function used to interpret the hashed bytes
     * @return Hashed string in specified format, null if algorithm isn't valid
     */
    public final <T> T hash(String data, Function<byte[], T> format) {
        return hash(data.getBytes(), format);
    }

    /**
     * Hashes a piece of data with the algorithm
     *
     * @param data Data being hashed
     * @param format The function used to interpret the hashed bytes
     * @return Hashed data in specified format, null if algorithm isn't valid
     */
    public final <T> T hash(byte[] data, Function<byte[], T> format) {
        Objects.requireNonNull(format);

        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            return format.apply(md.digest(data));
        } catch (NoSuchAlgorithmException e2) {
            return null;
        }
    }

    /**
     * Contains hash return type formats.
     */
    public interface Format {

        /**
         * Converts the hashed bytes into a string containing the bytes as hex values
         */
        Function<byte[], String> HEX_STRING_FORMAT = DatatypeConverter::printHexBinary;

        /**
         * Returns the hashed bytes raw
         */
        Function<byte[], byte[]> RAW_FORMAT = bytes -> bytes;
    }
}
