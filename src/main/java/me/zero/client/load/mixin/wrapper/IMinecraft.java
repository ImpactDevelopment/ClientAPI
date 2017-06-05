package me.zero.client.load.mixin.wrapper;

import me.zero.client.api.event.defaults.ClickEvent;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;

/**
 * @author Brady
 * @since 2/20/2017 12:00 PM
 */
public interface IMinecraft {

    /**
     * @return Returns the Game's Timer
     */
    Timer getTimer();

    /**
     * Sets the game's session
     *
     * @param session The new Session
     */
    void setSession(Session session);

    /**
     * Clicks a mouse button
     *
     * @param button The button
     */
    void clickMouse(ClickEvent.MouseButton button);

    /**
     * Sets the right click delay timer
     *
     * @param delay The new right click delay
     */
    void setRightClickDelayTimer(int delay);
}
