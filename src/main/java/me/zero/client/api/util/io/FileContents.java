package me.zero.client.api.util.io;

import java.util.*;

/**
 * Holder for file contents, stores the raw data,
 * which is a single {@code String} and the split
 * data, which holds the raw data split at every
 * line break as a {@code List}.
 *
 * @author Brady
 * @since 5/17/2017 10:21 AM
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
     * Returns the raw contents of the file read,
     *
     *
     * @return
     */
    public final String getRawContents() {
        return this.raw;
    }

    public final List<String> getSplitContents() {
        return this.split;
    }
}
