package clientapi.load.mixin;

import clientapi.ClientAPI;
import clientapi.event.defaults.game.entity.EntityStatusEvent;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.SPacketEntityStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static clientapi.util.interfaces.Helper.*;

/**
 * @author Brady
 * @since 8/24/2017 10:18 PM
 */
@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {

    @Inject(method = "handleEntityStatus", at = @At("HEAD"), cancellable = true)
    private void handleEntityStatus(SPacketEntityStatus packet, CallbackInfo ci) {
        if (mc.world == null)
            return;

        Entity entity = packet.getEntity(mc.world);
        // noinspection ConstantConditions
        if (entity == null)
            return;

        EntityStatusEvent event = new EntityStatusEvent(entity, packet.getOpCode());
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }
}
