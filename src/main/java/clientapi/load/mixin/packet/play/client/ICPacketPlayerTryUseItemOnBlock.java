package clientapi.load.mixin.packet.play.client;

import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 9:14 PM
 */
@Mixin(CPacketPlayerTryUseItemOnBlock.class)
public interface ICPacketPlayerTryUseItemOnBlock {

    @Accessor BlockPos getPosition();

    @Accessor void setPosition(BlockPos position);

    @Accessor EnumFacing getPlacedBlockDirection();

    @Accessor void setPlacedBlockDirection(EnumFacing placedBlockDirection);

    @Accessor EnumHand getHand();

    @Accessor void getHand(EnumHand hand);

    @Accessor float getFacingX();

    @Accessor void setFacingX(float facingX);

    @Accessor float getFacingY();

    @Accessor void setFacingY(float facingY);

    @Accessor float getFacingZ();

    @Accessor void setFacingZ(float facingZ);
}
