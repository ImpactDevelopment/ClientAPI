package me.zero.client.load.mixin;

import me.zero.client.wrapper.IGameType;
import net.minecraft.world.GameType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created by Brady on 4/27/2017.
 */
@Mixin(GameType.class)
public class MixinGameType implements IGameType {

    @Shadow String shortName;

    @Override
    public String getShortName() {
        return this.shortName;
    }
}
