package me.zero.client.api.wrapper;

/**
 * Used to set sleeping
 *
 * @since 1.0
 *
 * Created by Brady on 2/25/2017.
 */
public interface IEntityPlayer {

    /**
     * Sets the player sleeping state
     *
     * @since 1.0
     *
     * @param sleeping Sleep state
     */
    void setSleeping(boolean sleeping);

    /**
     * Sets the player sleeping timer
     *
     * @since 1.0
     *
     * @param sleepTimer Sleep timer
     */
    void setSleepTimer(int sleepTimer);
}
