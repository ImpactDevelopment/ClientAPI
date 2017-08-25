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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

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
                sb.append(
                    Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            return sb.toString();
        } catch (NoSuchAlgorithmException e2) {
            return data;
        }
    }
}
