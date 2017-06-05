package me.zero.client.load.mixin.wrapper;

import net.minecraft.world.GameType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 4/4/2017 12:00 PM
 */
@Mixin(GameType.class)
public interface IGameType {

    @Accessor String getShortName();
}
