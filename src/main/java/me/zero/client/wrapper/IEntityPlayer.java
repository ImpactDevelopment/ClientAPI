package me.zero.client.wrapper;

/**
 * Used to set sleeping
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/25/2017 12:00 PM
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
