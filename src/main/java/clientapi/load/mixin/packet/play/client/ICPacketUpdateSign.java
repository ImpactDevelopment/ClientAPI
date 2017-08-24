package clientapi.load.mixin.packet.play.client;

import net.minecraft.network.play.client.CPacketUpdateSign;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 9:47 PM
 */
@Mixin(CPacketUpdateSign.class)
public interface ICPacketUpdateSign {

    @Accessor BlockPos getPos();

    @Accessor void setPos(BlockPos pos);

    @Accessor String[] getLines();

    @Accessor void setLines(String[] lines);
}
