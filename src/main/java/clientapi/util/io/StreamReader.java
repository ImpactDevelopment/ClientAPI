/*
 * Copyright 2018 ImpactDevelopment
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

package clientapi.util.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Reads the lines returned from an {@code InputStream}.
 *
 * @author Brady
 * @since 2/15/2017
 */
public final class StreamReader {

    /**
     * Stream being read
     */
    private final InputStream stream;

    public StreamReader(InputStream stream) {
        this.stream = stream;
    }

    /**
     * Returns the lines found from the {@code InputStream}. An empty
     * stream will be returned in the case of an {@code IOException} being thrown.
     *
     * @return Stream containing all lines from the {@code InputStream}.
     */
    public final List<String> lines() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            List<String> lines = br.lines().collect(Collectors.toList());
            br.close();
            return lines;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Returns all of the lines from the {@code InputStream}
     * as a single string. New lines are broken up by "\n"
     *
     * @return The stream's output
     */
    public final String all() {
        StringJoiner joiner = new StringJoiner("\n");
        lines().forEach(joiner::add);
        return joiner.toString();
    }
}
