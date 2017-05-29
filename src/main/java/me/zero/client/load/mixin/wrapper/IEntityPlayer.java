package me.zero.client.load.mixin.wrapper;

/**
 * Used to set sleeping
 *
 * @author Brady
 * @since 2/25/2017 12:00 PM
 */
public interface IEntityPlayer {

    /**
     * Sets the player sleeping state
     *
     * @param sleeping Sleep state
     */
    void setSleeping(boolean sleeping);

    /**
     * Sets the player sleeping timer
     *
     * @param sleepTimer Sleep timer
     */
    void setSleepTimer(int sleepTimer);
}
