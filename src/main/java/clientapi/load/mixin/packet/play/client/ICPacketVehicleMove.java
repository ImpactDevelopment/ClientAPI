package clientapi.load.mixin.packet.play.client;

import net.minecraft.network.play.client.CPacketVehicleMove;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 9:56 PM
 */
@Mixin(CPacketVehicleMove.class)
public interface ICPacketVehicleMove {

    @Accessor void setX(double x);

    @Accessor double getX();

    @Accessor void setY(double y);

    @Accessor double getY();

    @Accessor void setZ(double z);

    @Accessor double getZ();

    @Accessor void setYaw(float yaw);

    @Accessor float getYaw();

    @Accessor void setPitch(float pitch);

    @Accessor float getPitch();
}
