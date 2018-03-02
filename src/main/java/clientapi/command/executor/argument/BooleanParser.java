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
 * @since 10/18/2017 11:10 AM
 */
public final class BooleanParser implements ArgumentParser<Boolean> {

    @Override
    public final Boolean parse(ExecutionContext context, Type type, String raw) {
        switch (raw.toLowerCase()) {
            case "true":
            case "yes":
            case "1":
                return true;
            case "false":
            case "no":
            case "0":
                return false;
            default:
                return null;
        }
    }

    @Override
    public final boolean isTarget(Type type) {
        return type instanceof Class && (Boolean.class.isAssignableFrom((Class) type) || Boolean.TYPE.isAssignableFrom((Class) type));
    }
}
