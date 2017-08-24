package clientapi.load.mixin.packet.play.client;

import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 9:07 PM
 */
@Mixin(CPacketPlayerDigging.class)
public interface ICPacketPlayerDigging {

    @Accessor BlockPos getPosition();

    @Accessor void setPosition(BlockPos position);

    @Accessor EnumFacing getFacing();

    @Accessor void setFacing(EnumFacing facing);

    @Accessor CPacketPlayerDigging.Action getAction();

    @Accessor void setAction(CPacketPlayerDigging.Action action);
}
