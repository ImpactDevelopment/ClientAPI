/*
 * Copyright 2017 ZeroMemes
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

package clientapi.api.util;

import clientapi.api.value.type.NumberType;

/**
 * Basic timer to keep track of tasks
 *
 * @author Brady
 * @since 2/24/2017 12:00 PM
 */
public final class Timer {

    private long prevMS;

    public final void reset() {
        prevMS = System.currentTimeMillis();
    }

    public final long getDiff() {
        return getTime() - prevMS;
    }

    public final boolean delay(long milliseconds) {
        return getTime() >= prevMS + milliseconds;
    }

    public final boolean delay(NumberType milliseconds) {
        return delay(milliseconds.getValue().intValue());
    }

    public final boolean delay(float milliSec) {
        return (float) (getTime() - this.prevMS) >= milliSec;
    }

    public final boolean speed(float speed) {
        speed = Math.max(0, speed);
        return getTime() >= prevMS + (long) (1000 / speed);
    }

    public final boolean speed(NumberType speed) {
        return this.speed(speed.getValue().floatValue());
    }

    private long getTime() {
        return System.currentTimeMillis();
    }
}
