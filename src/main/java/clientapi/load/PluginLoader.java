/*
 * Copyright 2017 ZeroMemes
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

package clientapi.load;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Brady
 * @since 9/8/2017 7:08 PM
 */
class PluginLoader {

    void load(List<String> args) {
        OptionParser parser = new OptionParser();
        parser.allowsUnrecognizedOptions();

        OptionSpec<File> pluginDirOption = parser.accepts("pluginDir").withOptionalArg().ofType(File.class);
        OptionSpec<String> nonOption = parser.nonOptions();

        OptionSet options = parser.parse(args.toArray(new String[0]));
        File pluginDir = options.valueOf(pluginDirOption);
        if (pluginDir == null)
            return;

        // Ensure that the pluginDir argument has been removed. Only do this
        // if the args aren't empty, which would indicate that we're launching
        // with the Forge tweaker, and Forge *should* be the primary tweaker.
        if (!args.isEmpty()) {
            args.clear();
            args.addAll(options.valuesOf(nonOption));
        }

        if (pluginDir.isDirectory())
            // noinspection ConstantConditions
            Arrays.stream(pluginDir.listFiles())
                    .map(this::getJarFile)
                    .filter(Objects::nonNull)
                    .forEach(this::loadPlugin);
    }

    private void loadPlugin(JarFile jarFile) {
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();

        }
    }

    private JarFile getJarFile(File file) {
        try {
            return new JarFile(file);
        } catch (IOException e) {
            return null;
        }
    }
}
