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

package clientapi.command.executor.parser.impl;

import clientapi.command.executor.ExecutionContext;
import clientapi.command.executor.parser.ArgumentParser;
import org.apache.commons.lang3.math.NumberUtils;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Brady
 * @since 10/20/2017
 */
public final class NumberParser implements ArgumentParser<Number> {

    @Override
    public final Number parse(ExecutionContext context, Type type, String raw) {
        if (NumberUtils.isCreatable(raw)) {
            return NumberUtils.createNumber(raw);
        }
        return null;
    }

    @Override
    public final boolean isTarget(Type type) {
        if (!(type instanceof Class)) {
            return false;
        }

        Class c = (Class) type;
        // Check all NumberUtils#createNumber(String) supported types
        // Integer -> BigInteger
        // Float -> BigDecimal
        return Integer.class.isAssignableFrom(c)
                || Long.class.isAssignableFrom(c)
                || BigInteger.class.isAssignableFrom(c)
                || Float.class.isAssignableFrom(c)
                || Double.class.isAssignableFrom(c)
                || BigDecimal.class.isAssignableFrom(c)
                || Integer.TYPE.isAssignableFrom(c)
                || Long.TYPE.isAssignableFrom(c)
                || Float.TYPE.isAssignableFrom(c)
                || Double.TYPE.isAssignableFrom(c);
    }
}
