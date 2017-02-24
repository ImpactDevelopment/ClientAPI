package me.zero.client.api.wrapper;

import net.minecraft.util.Session;
import net.minecraft.util.Timer;

/**
 * Used to retrieve the Timer instance.
 *
 * @since 1.0
 *
 * Created by Brady on 2/20/2017.
 */
public interface IMinecraft {

    /**
     * @since 1.0
     *
     * @return Returns the Game's Timer
     */
    Timer getTimer();

    /**
     * Sets the game's session
     *
     * @since 1.0
     */
    void setSession(Session session);
}
