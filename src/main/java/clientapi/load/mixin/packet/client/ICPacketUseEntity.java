package clientapi.load.mixin.packet.client;

import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 9:49 PM
 */
@Mixin(CPacketUseEntity.class)
public interface ICPacketUseEntity {

    @Accessor int getEntityId();

    @Accessor void setEntityId(int entityId);

    @Accessor CPacketUseEntity.Action getAction();

    @Accessor void setAction(CPacketUseEntity.Action action);

    @Accessor Vec3d getHitVec();

    @Accessor void setHitVec(Vec3d hitVec);

    @Accessor EnumHand getHand();

    @Accessor void setHand(EnumHand hand);
}
