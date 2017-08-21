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

package clientapi.command.exception.handler;

import clientapi.command.exception.CommandException;

import java.util.function.Consumer;

/**
 * Put in place by developers to hook into when exceptions are thrown.
 * Implementations should have an {@code ExceptionTarget} annotation placed at
 * the type declaration so that the command handler may identify the target
 * exception type. The main purpose of implementing handlers is to provide users
 * with custom messages when an exception is thrown, rather than a default
 * console output performed by the Client API.
 *
 * @see ExceptionTarget
 * @author Brady
 * @since 6/1/2017 2:54 PM
 */
@FunctionalInterface
public interface ExceptionHandler extends Consumer<CommandException> {

	/**
	 * Retrieves the command exception type from the {@code ExceptionTarget}
	 * that belongs to this type.
	 *
	 * @return The command exception type
	 */
	default Class<? extends CommandException> getType() {
		return this.getClass().getAnnotation(ExceptionTarget.class).value();
	}
}
