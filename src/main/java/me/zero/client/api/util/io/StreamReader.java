package me.zero.client.api.util.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringJoiner;

/**
 * Reads from an InputStream
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/15/2017 12:00PM
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
     * Reads the stream and returns the output
     *
     * @since 1.0
     *
     * @return The stream's output
     */
    public final String read() {
        StringJoiner joiner = new StringJoiner("\n");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = br.readLine()) != null)
                joiner.add(line);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return joiner.toString();
    }
}
