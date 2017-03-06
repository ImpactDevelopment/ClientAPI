package me.zero.client.wrapper;

/**
 * Used for various PlayerControllerMP fields
 *
 * @since 1.0
 *
 * Created by Brady on 2/24/2017.
 */
public interface IPlayerControllerMP {

    /**
     * @since 1.0
     *
     * @return PlayerControllerMP#isHittingBlock
     */
    boolean isHittingBlock();

    /**
     * @since 1.0
     *
     * @return PlayerControllerMP#curBlockDamageMP
     */
    float getCurBlockDamage();

    /**
     * Sets PlayerControllerMP#curBlockDamageMP
     *
     * @since 1.0
     *
     * @param damage New damage value
     */
    void setCurBlockDamage(float damage);
}
