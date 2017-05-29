package me.zero.client.load.mixin;

import me.zero.client.load.mixin.wrapper.IEntityPlayer;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer implements IEntityPlayer {

    @Shadow private boolean sleeping;
    @Shadow private int sleepTimer;

    @Override
    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }

    @Override
    public void setSleepTimer(int sleepTimer) {
        this.sleepTimer = sleepTimer;
    }
}
