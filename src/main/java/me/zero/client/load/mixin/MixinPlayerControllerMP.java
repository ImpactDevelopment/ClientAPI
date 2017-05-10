package me.zero.client.load.mixin;

import me.zero.client.wrapper.IPlayerControllerMP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(PlayerControllerMP.class)
public class MixinPlayerControllerMP implements IPlayerControllerMP {

    @Shadow private boolean isHittingBlock;
    @Shadow private float curBlockDamageMP;

    @Override
    public boolean isHittingBlock() {
        return this.isHittingBlock;
    }

    @Override
    public float getCurBlockDamage() {
        return this.curBlockDamageMP;
    }

    @Override
    public void setCurBlockDamage(float damage) {
        this.curBlockDamageMP = damage;
    }
}
