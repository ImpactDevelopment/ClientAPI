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

package clientapi.command.exception.handler;

import clientapi.command.exception.CommandException;

/**
 * Put in place by developers to hook into when exceptions
 * are thrown. The main purpose of implementing handlers is
 * to provide users with custom messages when an exception
 * is thrown, rather than a default console output performed
 * by ClientAPI.
 *
 * @author Brady
 * @since 6/1/2017
 */
public interface ExceptionHandler {

    /**
     * Handles a thrown {@link CommandException} of a valid target type.
     *
     * @see ExceptionHandler#isTarget
     *
     * @param exception Exception that was thrown
     */
    void handle(CommandException exception);

    /**
     * Checks if a given {@link CommandException} type should be
     * accepted by this {@link ExceptionHandler}.
     *
     * @param type The type of a {@link CommandException}
     *
     * @return The command exception type
     */
    boolean isTarget(Class<? extends CommandException> type);
}
