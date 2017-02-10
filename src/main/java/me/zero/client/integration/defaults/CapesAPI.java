package me.zero.client.integration.defaults;

import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.util.interfaces.Registry;
import me.zero.client.integration.Integration;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Integration for HalfPetal's CapesAPI.
 * <a href="http://capesapi.com/">CapesAPI Website</a>
 * <a href="https://github.com/halfpetal/CapesAPI">CapesAPI GitHub</a>
 *
 * @since 1.0
 *
 * @author Matthew Hatcher
 * @author Marco_MC
 * @author Brady
 * @version 2.0.0, Jan 2017
 *
 * Created by Brady on 2/9/2017.
 */
public class CapesAPI extends Integration implements Registry<UUID, ResourceLocation>, Helper {

    /**
     * Map containing all of the Capes
     */
    private Map<UUID, ResourceLocation> capes = new HashMap<>();

    /**
     * Downloads and registers a user's cape to the cape map
     *
     * @since 1.0
     *
     * @param obj The UUID getting loaded
     */
    @Override
    public void load(UUID obj) {
        String url = "http://capesapi.com/api/v1/" + obj.toString() + "/getCape";
        final ResourceLocation resourceLocation = new ResourceLocation("capeapi/capes/" + obj.toString() + ".png");
        IImageBuffer iImageBuffer = new IImageBuffer() {

            @Override
            public BufferedImage parseUserSkin(BufferedImage image) {
                return image;
            }

            @Override
            public void skinAvailable() {
                capes.put(obj, resourceLocation);
            }
        };
        mc.getTextureManager().loadTexture(resourceLocation, new ThreadDownloadImageData(null, url, null, iImageBuffer));
    }

    /**
     * Removes the cape of the user from the cape map
     *
     * @since 1.0
     *
     * @param obj The User's UUID
     */
    @Override
    public void unload(UUID obj) {
        capes.remove(obj);
    }

    /**
     * Get the cape of the user from the cape Map
     *
     * @since 1.0
     *
     * @param obj The UUID
     * @return The cape belonging to the UUID
     */
    @Override
    public ResourceLocation getEntry(UUID obj) {
        return capes.containsKey(obj) ? capes.get(obj) : null;
    }

    /**
     * @since 1.0
     *
     * @param obj The UUID
     * @return Whether or not the specified UUID has a Cape associated with it
     */
    @Override
    public boolean hasEntry(UUID obj) {
        return capes.containsKey(obj);
    }
}
