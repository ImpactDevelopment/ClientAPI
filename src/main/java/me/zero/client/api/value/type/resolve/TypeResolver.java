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

package me.zero.client.api.value.type.resolve;

import me.zero.client.api.value.Value;

import java.lang.reflect.Field;
import java.util.function.BiFunction;

/**
 * Takes in a field and its parent object and
 * attempts to convert it to a usable value object
 *
 * @author Brady
 * @since 2/21/2017 12:00 PM
 */
public interface TypeResolver<T extends Value> extends BiFunction<Object, Field, T> {}
