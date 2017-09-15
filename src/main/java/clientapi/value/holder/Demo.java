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

package clientapi.value.holder;

import clientapi.util.annotation.Label;
import clientapi.value.annotation.BooleanValue;
import clientapi.value.annotation.NumberValue;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Brady
 * @since 9/14/2017 7:28 AM
 */
public class Demo {

    @Label(name = "Test Value", id = "test", description = "test")
    @BooleanValue
    private boolean test;

    @Label(name = "Object Boolean", id = "test2", description = "test2")
    @BooleanValue
    private Boolean test2;

    @Label(name = "Float meme", id = "test3", description = "test3")
    @NumberValue(min = 0, max = 10)
    private float test3;

    public Consumer<Object> getFieldSetter(String id) {
        if (id.equals("test")) {
            return value -> this.test = (boolean) value;
        }
        if (id.equals("test2")) {
            return value -> this.test2 = (Boolean) value;
        }
        if (id.equals("test3")) {
            return value -> this.test3 = (float) value;
        }
        return null;
    }

    public Supplier<Object> getFieldGetter(String id) {
        if (id.equals("test")) {
            return () -> test;
        }
        if (id.equals("test2")) {
            return () -> test2;
        }
        if (id.equals("test3")) {
            return () -> test3;
        }
        return null;
    }
}

