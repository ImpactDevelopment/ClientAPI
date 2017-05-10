package me.zero.client.api.util.render.camera;

/**
 * Class for handling camera properties
 *
 * @author Brady
 * @since 2/4/2017 12:00 PM
 */
public interface CameraHandle {

    /**
     * @return The visibility of this camera
     */
    boolean visible();

    /**
     * @return Whether or not this camera is reflected
     */
    boolean reflected();
}
