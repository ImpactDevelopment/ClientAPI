package clientapi.load.mixin.packet.play.client;

import net.minecraft.network.play.client.CPacketEntityAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:39 PM
 */
@Mixin(CPacketEntityAction.class)
public interface ICPacketEntityAction {

    @Accessor int getEntityId();

    @Accessor void setEntityId(int entityId);

    @Accessor CPacketEntityAction.Action getAction();

    @Accessor void setAction(CPacketEntityAction.Action action);

    @Accessor int getAuxData();

    @Accessor void setAuxData(int auxData);
}
