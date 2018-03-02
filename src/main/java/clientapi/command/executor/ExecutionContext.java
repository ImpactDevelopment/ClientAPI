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

package clientapi.command.executor;

import clientapi.command.handler.CommandHandler;
import clientapi.command.sender.CommandSender;

/**
 * Provides commands context to the cause of execution. Context
 * is defined by the {@code CommandSender} and {@code CommandHandler}.
 *
 * @author Brady
 * @since 10/18/2017 11:19 AM
 */
public interface ExecutionContext {

    /**
     * @return The {@code CommandSender} responsible for the command execution
     */
    CommandSender sender();

    /**
     * @return The {@code CommandHandler} that carried out command execution
     */
    CommandHandler handler();

    static ExecutionContext of(CommandSender sender, CommandHandler handler) {
        return new Impl(sender, handler);
    }

    /**
     * Implementation of {@code ExecutionContext}, used when
     * creating an instance of {@code ExecutionContext}
     * from {@code ExecutionContext#of(CommandSender, CommandHandler)}
     *
     * @see ExecutionContext#of(CommandSender, CommandHandler)
     */
    class Impl implements ExecutionContext {

        /**
         * {@code CommandSender} responsible for command execution
         */
        private final CommandSender sender;

        /**
         * {@code CommandHandler} that carried out command execution
         */
        private final CommandHandler handler;

        private Impl(CommandSender sender, CommandHandler handler) {
            this.sender = sender;
            this.handler = handler;
        }

        @Override
        public CommandSender sender() {
            return this.sender;
        }

        @Override
        public CommandHandler handler() {
            return this.handler;
        }
    }
}
