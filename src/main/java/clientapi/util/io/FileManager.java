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

package clientapi.util.io;

import clientapi.ClientAPI;
import org.apache.logging.log4j.Level;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Used for File I/O
 *
 * @author Brady
 * @since 2/23/2017 12:00 PM
 */
public final class FileManager {

    private FileManager() {}

    /**
     * Reads from a file and then returns a List
     * containing all of the data from the file.
     *
     * @param file File being read from
     * @return The data contents of the file
     */
    public static FileContents read(String file) {
        List<String> data = new ArrayList<>();

        if (!exists(file)) {
            createFile(file);
            return new FileContents(data);
        }

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null)
                data.add(line);

            bufferedReader.close();
        } catch (IOException ex) {
            ClientAPI.LOGGER.log(Level.WARN, "Unable to read from " + file);
        }

        return new FileContents(data);
    }

    /**
     * Writes data to a file, completely overriding the
     * contents of the file.
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
            ClientAPI.LOGGER.log(Level.WARN, "Unable to write to " + file);
        }
    }

    /**
     * Creates a file from the specified file path
     *
     * @param file The file path
     */
    public static void createFile(String file) {
        try {
            Files.createDirectories(Paths.get(new File(file).getParent()));
        } catch (IOException e) {
            ClientAPI.LOGGER.log(Level.WARN, "Unable to create parent directories", e);
            return;
        }

        try {
            Files.createFile(Paths.get(file));
        } catch (IOException e) {
            ClientAPI.LOGGER.log(Level.WARN, "Unable to create file", e);
        }
    }

    /**
     * Checks if the specified file path belongs
     * to a file.
     *
     * @param file The file path
     * @return Whether or not the file exists
     */
    public static boolean exists(String file) {
        return Files.exists(Paths.get(file));
    }
}
