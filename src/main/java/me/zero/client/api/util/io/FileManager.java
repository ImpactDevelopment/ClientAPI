package me.zero.client.api.util.io;

import me.zero.client.api.util.logger.Level;
import me.zero.client.api.util.logger.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Used for File I/O
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/23/2017 12:00PM
 */
public final class FileManager {

    private FileManager() {}

    /**
     * Reads from a file and then returns a List
     * containing all of the data from the file.
     *
     * @since 1.0
     *
     * @param file File being read from
     * @return The list of data
     */
    public static List<String> read(String file) {
        List<String> data = new ArrayList<>();

        if (!exists(file)) {
            createFile(file);
            return data;
        }

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null)
                data.add(line);

            bufferedReader.close();
        } catch (IOException ex) {
            Logger.instance.log(Level.WARNING, "Unable to read from " + file);
        }

        return data;
    }

    /**
     * Writes data to a file, completely overriding the
     * contents of the file.
     *
     * @since 1.0
     *
     * @param data Data being written
     * @param file File being written to
     */
    public static void write(List<String> data, String file) {
        if (!exists(file)) {
            createFile(file);
        }
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String piece : data) {
                bw.write(piece);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            Logger.instance.log(Level.WARNING, "Unable to write to " + file);
        }
    }

    /**
     * Creates a file from the specified file path
     *
     * @since 1.0
     *
     * @param file The file path
     */
    public static void createFile(String file) {
        File theFile = new File(file);
        Path path = Paths.get(file);
        Path parent = Paths.get(theFile.getParent());
        if (!Files.exists(parent)) {
            try {
                Files.createDirectory(parent);
            } catch (IOException e) {
                Logger.instance.log(Level.WARNING, "Unable to create " + theFile.getParent());
            }
        }
        try {
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
            Logger.instance.log(Level.WARNING, "Unable to create " + file);
        }
    }

    /**
     * Checks if the specified file path belongs
     * to a file.
     *
     * @since 1.0
     *
     * @param file The file path
     * @return Whether or not the file exists
     */
    public static boolean exists(String file) {
        return Files.exists(Paths.get(file));
    }
}
