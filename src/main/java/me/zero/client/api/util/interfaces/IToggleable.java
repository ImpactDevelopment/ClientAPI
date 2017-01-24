package me.zero.client.api.util.interfaces;

/**
 * Created by Brady on 1/23/2017.
 */
public interface IToggleable {

    void onEnable();

    void onDisable();

    void toggle();

    void setState(boolean state);

    boolean getState();
}
