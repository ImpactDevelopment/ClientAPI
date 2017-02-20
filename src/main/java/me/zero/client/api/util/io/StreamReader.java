package me.zero.client.api.util.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Reads from an InputStream
 *
 * @since 1.0
 *
 * Created by Brady on 2/15/2017.
 */
public class StreamReader {

    /**
     * Stream being read
     */
    private InputStream stream;

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
    public String read() {
        BufferedReader br = null;
        String data = "";
        try {
            br = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = br.readLine()) != null) {
                data += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}
