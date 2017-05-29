package me.zero.client.load.mixin.wrapper;

/**
 * Used for various PlayerControllerMP fields
 *
 * @author Brady
 * @since 2/24/2017 12:00 PM
 */
public interface IPlayerControllerMP {

    /**
     * @return PlayerControllerMP#isHittingBlock
     */
    boolean isHittingBlock();

    /**
     * @return PlayerControllerMP#curBlockDamageMP
     */
    float getCurBlockDamage();

    /**
     * Sets PlayerControllerMP#curBlockDamageMP
     *
     * @param damage New damage value
     */
    void setCurBlockDamage(float damage);
}
