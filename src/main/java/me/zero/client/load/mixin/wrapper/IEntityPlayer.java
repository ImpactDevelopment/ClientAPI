package me.zero.client.load.mixin.wrapper;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 2/25/2017 12:00 PM
 */
@Mixin(EntityPlayer.class)
public interface IEntityPlayer {

    @Accessor void setSleeping(boolean sleeping);

    @Accessor void setSleepTimer(int sleepTimer);
}
