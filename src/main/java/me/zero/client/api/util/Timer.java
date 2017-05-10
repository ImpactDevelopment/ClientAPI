package me.zero.client.api.util;

import me.zero.client.api.value.type.NumberType;

/**
 * Basic timer to keep track of tasks
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/24/2017 12:00PM
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
