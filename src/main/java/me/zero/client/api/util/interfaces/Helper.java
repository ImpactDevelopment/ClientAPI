package me.zero.client.api.util.interfaces;

import me.zero.client.load.mixin.wrapper.IMinecraft;
import net.minecraft.client.Minecraft;

/**
 *
 *
 * @author Brady
 * @since 2/9/2017 12:00 PM
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
