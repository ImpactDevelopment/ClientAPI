package me.zero.client.integration.defaults;

import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.util.interfaces.Registry;
import me.zero.client.integration.Integration;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
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
 * @version 2.1.0, February 2017
 *
 * Created by Brady on 2/9/2017.
 */
public class CapesAPI extends Integration implements Registry<UUID, ResourceLocation>, Helper {

    /**
     * The Current Version
     */
    public static final String VERSION = "2.1.0";

    /**
     * The CapesAPI Base URL
     */
    public static final String HOST = "http://capesapi.com/api/v1/";

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
        String url = HOST + obj.toString() + "/getCape";
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

    /**
     * Returns whether or not the UUID has specified the capeId.
     *
     * @since 1.0
     *
     * @param uuid The UUID
     * @param capeId The Cape ID
     * @return Whether or not the UUID has the specified capeId
     * @throws IOException thrown by URL Creation and various Streams
     */
    public boolean hasCape(UUID uuid, String capeId) throws IOException {
        URL url = new URL(HOST + uuid.toString() + "/hasCape" + capeId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String response = "";
        String line;
        while ((line = in.readLine()) != null)
            response += line;

        in.close();

        return Integer.parseInt(response) == 1;
    }

    /**
     * Grants the user associated with the UUID
     * with the cape specified.
     *
     * @since 1.0
     *
     * @param uuid The UUID
     * @param capeId The Cape ID
     * @return Whether or not the cape adding was successful
     * @throws IOException
     */
    public boolean addCape(UUID uuid, String capeId) throws IOException {
        String data = "{\"capeId\": \"" + capeId + "\"}";
        URL url = new URL(HOST + uuid.toString() + "/addCape");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);

        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.write(data.getBytes());
        out.flush();
        out.close();

        InputStream stream = con.getResponseCode() == 200 ? con.getInputStream() : con.getErrorStream();

        if (stream == null)
            throw new IOException();

        BufferedReader in = new BufferedReader(new InputStreamReader(stream));

        String response = "";
        String line;
        while ((line = in.readLine()) != null)
            response += line;

        in.close();

        return Integer.parseInt(response) == 1;
    }
}
