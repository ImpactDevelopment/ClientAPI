package me.zero.client.wrapper;

/**
 * Used to get the X, Y and Z render positions
 *
 * @author Brady
 * @since 2/20/2017 12:00PM
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
