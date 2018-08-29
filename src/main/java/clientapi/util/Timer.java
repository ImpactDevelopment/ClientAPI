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

package clientapi.util;

import clientapi.value.type.NumberType;

/**
 * Basic timer to keep track of tasks
 *
 * @author Brady
 * @since 2/24/2017
 */
public final class Timer {

    private long prevMS;

    public final void reset() {
        prevMS = getJVMTime();
    }

    public final long getDiff() {
        return getJVMTime() - prevMS;
    }

    public final boolean delay(long milliseconds) {
        return getJVMTime() >= prevMS + milliseconds;
    }

    public final boolean delay(NumberType milliseconds) {
        return delay(milliseconds.getValue().intValue());
    }

    public final boolean delay(float milliSec) {
        return (float) (getJVMTime() - this.prevMS) >= milliSec;
    }

    public final boolean speed(float speed) {
        speed = Math.max(0, speed);
        return getJVMTime() >= prevMS + (long) (1000 / speed);
    }

    public final boolean speed(NumberType speed) {
        return this.speed(speed.getValue().floatValue());
    }

    /**
     * Returns the running JVM's high-resolution time source, in
     * milliseconds. This should not be compared with values returned
     * by {@link System#currentTimeMillis()} because this is <i>not</i>
     * representitive of the current unix time.
     *
     * @return The running JVM's high-resolution time source, in milliseconds.
     */
    public static long getJVMTime() {
        return System.nanoTime() / 1000000L; // 1E6 is a double and I don't want to cast
    }
}
