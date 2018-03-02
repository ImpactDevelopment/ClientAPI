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
 * @since 10/20/2017 11:04 AM
 */
public final class CharParser implements ArgumentParser<Character> {

    @Override
    public final Character parse(ExecutionContext context, Type type, String raw) {
        if (raw.length() == 1) {
            return raw.charAt(0);
        }
        return null;
    }

    @Override
    public final boolean isTarget(Type type) {
        return type instanceof Class && (Character.class.isAssignableFrom((Class) type) || Character.TYPE.isAssignableFrom((Class) type));
    }
}
