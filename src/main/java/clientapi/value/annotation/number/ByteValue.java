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

package clientapi.value.annotation.number;

import clientapi.value.annotation.ValueDefinition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Brady
 * @since 9/1/2018
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ValueDefinition
public @interface ByteValue {

    /**
     * The minimum value that this value can go to.
     *
     * @return The min value
     */
    byte min();

    /**
     * The maximum value that this value can go to.
     *
     * @return The max value
     */
    byte max();

    /**
     * The interval at which this value will change. If
     * the interval is set to 0, no interval will be used.
     *
     * @return The value change interval
     */
    byte interval() default 0;
}
