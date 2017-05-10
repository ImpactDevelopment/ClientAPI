package me.zero.client.api.util.interfaces;

import me.zero.client.wrapper.IMinecraft;
import net.minecraft.client.Minecraft;

/**
 *
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/9/2017 12:00PM
 */
public interface Helper {

    /**
     * Instance of Minecraft
     */
    Minecraft mc = Minecraft.getMinecraft();

    /**
     * Instance of Minecraft, casted to the accessibility interface
     */
    IMinecraft mca = (IMinecraft) mc;
}
