package me.zero.client.wrapper;

/**
 * Used to get the X, Y and Z render positions
 *
 * @author Brady
 * @since 2/20/2017 12:00 PM
 */
public interface IRenderManager {

    /**
     * @return Render Pos X
     */
    double getRenderPosX();

    /**
     * @return Render Pos Y
     */
    double getRenderPosY();

    /**
     * @return Render Pos Z
     */
    double getRenderPosZ();
}
