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

package clientapi.command.executor.argument;

import clientapi.command.executor.ExecutionContext;

import java.lang.reflect.Type;

/**
 * @author Brady
 * @since 10/18/2017 11:05 AM
 */
public interface ArgumentParser<T> {

    /**
     * Returns the resolved/parsed type from a
     * raw string representation.
     *
     * @param context Context behind command execution
     * @param type The expected return type
     * @param raw Type represented in a string
     * @return String resolved to type
     */
    T parse(ExecutionContext context, Type type, String raw);

    /**
     * @param type A type
     * @return Whether or not the specified type is a target for this parser.
     */
    boolean isTarget(Type type);
}
