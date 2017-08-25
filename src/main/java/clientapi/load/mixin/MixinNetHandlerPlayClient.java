package clientapi.load.mixin;

import clientapi.ClientAPI;
import clientapi.event.defaults.game.entity.EntityStatusEvent;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Brady
 * @since 8/24/2017 10:18 PM
 */
@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {

    @Redirect(method = "handleEntityStatus", at = @At(value = "INVOKE", target = "net/minecraft/entity/Entity.handleStatusUpdate(B)V"))
    private void handleStatusUpdate(Entity entity, byte opcode) {
        EntityStatusEvent event = new EntityStatusEvent(entity, opcode);
        ClientAPI.EVENT_BUS.post(event);
        if (!event.isCancelled())
            entity.handleStatusUpdate(opcode);
    }
}
