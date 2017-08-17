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

package clientapi.util.logger;

/**
 * Implementation of ILogger
 *
 * @see ILogger
 *
 * @author Brady
 * @since 1/21/2017 12:00 PM
 */
public final class Logger implements ILogger {

    private Logger() {}

    /**
     * Instance of the Logger
     */
    public static final Logger instance = new Logger();

    /**
     * Implementation of {@code #log(Level, String)}
     *
     * @see ILogger#log(Level, String)
     *
     * @param level The level
     * @param message The message
     */
    @Override
    public final void log(Level level, String message) {
        System.out.printf("[ClientAPI][%s] %s \n", level.toString(), message);
    }

    /**
     * Implementation of {@code #logf(Level, String, Object...)}
     *
     * @see ILogger#logf(Level, String, Object...)
     *
     * @param level The level
     * @param message The message
     * @param args The format arguments
     */
    @Override
    public final void logf(Level level, String message, Object... args) {
        this.log(level, String.format(message, args));
    }
}
