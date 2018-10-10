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

package clientapi.load.argument;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper for argument parsing
 *
 * @author Brady
 * @since 10/9/2018
 */
public final class Arguments {

    /**
     * Parses a list of raw arguments into a list of joined classifier and singular ones.
     *
     * @param args The arguments to parse
     * @return The parsed arguments
     */
    public static List<Argument> parse(List<String> args) {
        List<Argument> argsOut = new ArrayList<>();

        String classifier = null;

        for (String arg : args) {
            if (arg.startsWith("-")) {
                if (classifier != null) {
                    argsOut.add(new ClassifiedArgument(classifier, ""));
                    classifier = null;
                } else if (arg.contains("=")) {
                    argsOut.add(new ClassifiedArgument(arg.substring(0, arg.indexOf('=')), arg.substring(arg.indexOf('=') + 1)));
                } else {
                    classifier = arg;
                }
            } else {
                if (classifier != null) {
                    argsOut.add(new ClassifiedArgument(classifier, arg));
                    classifier = null;
                } else {
                    argsOut.add(new SingularArgument(arg));
                }
            }
        }

        return argsOut;
    }

    /**
     * Joins a list of arguments back into a string list format
     *
     * @param args The arguments
     * @return The joined arguments
     */
    public static List<String> join(List<Argument> args) {
        List<String> argsOut = new ArrayList<>();
        args.forEach(argument -> argument.addToList(argsOut));
        return argsOut;
    }
}
