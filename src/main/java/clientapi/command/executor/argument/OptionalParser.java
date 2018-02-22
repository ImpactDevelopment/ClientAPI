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

package clientapi.command.executor.argument;

import clientapi.command.executor.ExecutionContext;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

/**
 * @author Brady
 * @since 10/18/2017 9:17 PM
 */
public final class OptionalParser implements ArgumentParser<Optional<?>> {

    @Override
    public final Optional<?> parse(ExecutionContext context, Type type, String raw) {
        if (raw.isEmpty()) {
            return Optional.empty();
        }

        if (!(type instanceof ParameterizedType)) {
            // noinspection OptionalAssignedToNull
            return null;
        }

        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (actualTypeArguments.length != 1) {
            // noinspection OptionalAssignedToNull
            return null;
        }

        ArgumentParser<?> parser = context.handler().getParser(parameterizedType.getActualTypeArguments()[0]);
        if (parser == null)
            // noinspection OptionalAssignedToNull
            return null;

        return Optional.ofNullable(parser.parse(context, type, raw));
    }

    @Override
    public final boolean isTarget(Type type) {
        return type instanceof Class ? Optional.class.isAssignableFrom((Class) type)
                : type instanceof ParameterizedType && Optional.class.isAssignableFrom((Class) ((ParameterizedType) type).getRawType());
    }
}
