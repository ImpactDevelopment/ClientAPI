/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.api.command.exception;

/**
 * Thrown when a command was expected, but no command headers
 * matched the specified command.
 *
 * @author Brady
 * @since 6/11/2017 3:26 PM
 */
public final class UnknownCommandException extends CommandException {

    public UnknownCommandException() {
        super(null);
    }
}
