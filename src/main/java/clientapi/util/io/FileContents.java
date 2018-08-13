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

import java.util.*;

/**
 * Holder for file contents, stores the raw data,
 * which is a single {@code String} and the split
 * data, which holds the raw data split at every
 * line break as a {@code List}.
 *
 * @author Brady
 * @since 5/17/2017
 */
public final class FileContents {

    private final List<String> split;
    private final String raw;

    public FileContents(String contents) {
        Objects.requireNonNull(contents);

        this.raw = contents;
        this.split = new ArrayList<>(Arrays.asList(contents.split("\n")));
    }

    public FileContents(List<String> contents) {
        Objects.requireNonNull(contents);

        StringJoiner joiner = new StringJoiner("\n");
        contents.forEach(joiner::add);
        this.raw = joiner.toString();
        this.split = contents;
    }

    /**
     * @return Returns the raw contents of the file read
     */
    public final String getRawContents() {
        return this.raw;
    }

    /**
     * @return Returns the contents of the file read split by new lines
     */
    public final List<String> getSplitContents() {
        return this.split;
    }
}
