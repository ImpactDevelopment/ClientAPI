package me.zero.client.load.mixin.wrapper;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 2/24/2017 12:00 PM
 */
@Mixin(PlayerControllerMP.class)
public interface IPlayerControllerMP {

    /**
     * @return PlayerControllerMP#isHittingBlock
     */
    @Accessor boolean getIsHittingBlock();

    /**
     * @return PlayerControllerMP#curBlockDamageMP
     */
    @Accessor float getCurBlockDamageMP();

    /**
     * Sets PlayerControllerMP#curBlockDamageMP
     *
     * @param damage New damage value
     */
    @Accessor void setCurBlockDamageMP(float damage);
}
