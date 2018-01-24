package clientapi.load.mixin.extension;

/**
 * Extension to {@code Timer} that allows the adjusting of the game tickrate.
 *
 * @author Brady
 * @since 1/24/2018 11:34 AM
 */
public interface ITimer {

    void setSpeed(float speed);

    float getSpeed();
}
