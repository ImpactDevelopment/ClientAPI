package me.zero.client.api.wrapper;

import me.zero.client.api.event.defaults.ClickEvent;
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
     *
     * @param session The new Session
     */
    void setSession(Session session);

    /**
     * Clicks a mouse button
     *
     * @since 1.0
     *
     * @param button The button
     */
    void clickMouse(ClickEvent.MouseButton button);

    /**
     * Sets the right click delay timer
     *
     * @since 1.0
     *
     * @param delay The new right click delay
     */
    void setRightClickDelayTimer(int delay);
}
