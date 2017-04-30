package me.zero.client.load.mixin;

import me.zero.client.wrapper.IEntityPlayer;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created by Brady on 4/27/2017.
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
