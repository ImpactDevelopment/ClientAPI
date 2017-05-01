package me.zero.client.api.util.interfaces;

import me.zero.client.wrapper.IMinecraft;
import net.minecraft.client.Minecraft;

/**
 *
 *
 * @since 1.0
 *
 * Created by Brady on 2/9/2017.
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
