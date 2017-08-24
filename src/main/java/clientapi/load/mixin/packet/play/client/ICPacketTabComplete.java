package clientapi.load.mixin.packet.play.client;

import net.minecraft.network.play.client.CPacketTabComplete;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 9:45 PM
 */
@Mixin(CPacketTabComplete.class)
public interface ICPacketTabComplete {

    @Accessor String getMessage();

    @Accessor void setMessage(String message);

    @Accessor("hasTargetBlock") boolean hasTargetBlock();

    @Accessor void setHasTargetBlock(boolean hasTargetBlock);

    @Accessor BlockPos getTargetBlock();

    @Accessor void setTargetBlock(BlockPos targetBlock);
}
