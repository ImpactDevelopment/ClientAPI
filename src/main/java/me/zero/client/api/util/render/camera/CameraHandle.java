package me.zero.client.api.util.render.camera;

/**
 * Class for handling camera properties
 *
 * @since 1.0
 *
 * Created by Brady on 2/4/2017.
 */
public interface CameraHandle {

    /**
     * @since 1.0
     *
     * @return The visibility of this camera
     */
    boolean visible();

    /**
     * @since 1.0
     *
     * @return Whether or not this camera is reflected
     */
    boolean reflected();
}
