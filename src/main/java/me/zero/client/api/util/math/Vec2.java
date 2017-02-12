package me.zero.client.api.util.math;

/**
 * Vector with an X and Y position
 *
 * @since 1.0
 *
 * Created by Brady on 2/12/2017.
 */
public class Vec2 {

    private float x, y;

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 x(float x) {
        this.x = x;
        return this;
    }

    public Vec2 y(float y) {
        this.y = y;
        return this;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public Vec2 add(Vec2 vector) {
        return new Vec2(this.x + vector.x, this.y + vector.y);
    }

    public Vec2 sub(Vec2 vector) {
        return new Vec2(this.x - vector.x, this.y - vector.y);
    }

    public Vec2 scale(float scale) {
        return new Vec2(this.x * scale, this.y * scale);
    }
}
