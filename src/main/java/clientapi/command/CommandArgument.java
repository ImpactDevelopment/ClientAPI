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

package clientapi.command;

import clientapi.util.interfaces.Nameable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

/**
 * @author Brady
 * @since 2/15/2018 6:21 PM
 */
public final class CommandArgument implements Nameable {

    private final String name;
    private final Type type;

    CommandArgument(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    public final Type getType() {
        return this.type;
    }

    public final boolean isOptional() {
        return this.type instanceof ParameterizedType && Optional.class.isAssignableFrom((Class) ((ParameterizedType) type).getRawType());
    }
}
