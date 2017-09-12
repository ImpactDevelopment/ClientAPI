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

import clientapi.util.annotation.Label;
import clientapi.value.IValue;
import clientapi.value.annotation.BooleanValue;
import clientapi.value.annotation.NumberValue;
import clientapi.value.holder.ValueHolder;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Brady
 * @since 9/11/2017 11:24 AM
 */
public class ValueTest {

    @Test
    public void test() {
        TestHolder holder = new TestHolder();

        assertEquals("Test ValueHolder should have exactly 1 value", holder.getValues().size(), 1);

        IValue boolean_value;
        assertNotNull("IValue with a valid id should evaluate to non null", (boolean_value = holder.getValue("boolean_value")));
        assertEquals("BooleanValue should resolve to false if undefined", boolean_value.getValue(), false);
        assertEquals("Test BooleanValue should have exactly 1 child value", boolean_value.getValues().size(), 1);

        IValue child_value;
        assertNotNull("Child value shouldn't be null", (child_value = boolean_value.getValues().get(0)));
        assertNotNull("Child value should have a non-null parent", child_value.getParent());
        assertEquals("Child value's parent id should match the Test BooleanValue id", child_value.getParent(), boolean_value.getId());
        assertEquals("Child value shouldn't have any children", child_value.getValues().size(), 0);
        assertEquals("Child value's value should be equal to its field value", child_value.getValue(), 5);
    }

    private class TestHolder extends ValueHolder {

        //@formatter:off

        @Label(name = "Boolean Value", id = "boolean_value", description = "An example boolean value")
        @BooleanValue
        private boolean bool;

            @Label(name = "Child Value", parent = "boolean_value", id = "child_value", description = "An example child value")
            @NumberValue(min = 0, max = 10)
            private int child = 5;

        //@formatter:on
    }
}
