package me.zero.client.api.wrapper;

/**
 * Used to get the X, Y and Z render positions
 *
 * Created by Brady on 2/20/2017.
 */
public interface IRenderManager {

    /**
     * @since 1.0
     *
     * @return Render Pos X
     */
    double getRenderPosX();

    /**
     * @since 1.0
     *
     * @return Render Pos Y
     */
    double getRenderPosY();

    /**
     * @since 1.0
     *
     * @return Render Pos Z
     */
    double getRenderPosZ();
}
