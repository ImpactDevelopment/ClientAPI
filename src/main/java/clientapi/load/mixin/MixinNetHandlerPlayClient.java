package clientapi.load.mixin;

import clientapi.ClientAPI;
import clientapi.event.defaults.game.entity.EntityStatusEvent;
import clientapi.event.defaults.game.entity.PlayerDeathEvent;
import me.zero.alpine.type.EventState;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketCombatEvent;
import net.minecraft.network.play.server.SPacketEntityStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static clientapi.util.interfaces.Helper.mc;

/**
 * @author Brady
 * @since 8/24/2017 10:18 PM
 */
@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {

    @Inject(method = "handleEntityStatus", at = @At("HEAD"), cancellable = true)
    private void preHandleEntityStatus(SPacketEntityStatus packet, CallbackInfo ci) {
        if (mc.world == null)
            return;

        Entity entity = packet.getEntity(mc.world);
        // noinspection ConstantConditions
        if (entity == null)
            return;

        EntityStatusEvent event = new EntityStatusEvent(EventState.PRE, entity, packet.getOpCode());
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }

    @Inject(method = "handleEntityStatus", at = @At("RETURN"))
    private void postHandleEntityStatus(SPacketEntityStatus packet, CallbackInfo ci) {
        if (mc.world == null)
            return;

        Entity entity = packet.getEntity(mc.world);
        // noinspection ConstantConditions
        if (entity == null)
            return;

        ClientAPI.EVENT_BUS.post(new EntityStatusEvent(EventState.POST, entity, packet.getOpCode()));
    }

    @Inject(method = "handleCombatEvent", at=  @At(value = "INVOKE_ASSIGN", target = "net/minecraft/network/PacketThreadUtil.checkThreadAndEnqueue(Lnet/minecraft/network/Packet;Lnet/minecraft/network/INetHandler;Lnet/minecraft/util/IThreadListener;)V"))
    private void handleCombatEvent(SPacketCombatEvent event, CallbackInfo ci) {
        if (mc.world == null)
            return;

        if (event.eventType == SPacketCombatEvent.Event.ENTITY_DIED) {
            Entity died = mc.world.getEntityByID(event.playerId);
            Entity killer = mc.world.getEntityByID(event.entityId);

            // The entity that died should always be a player, this is just a safety measure.
            // The killer isn't checked because in some cases it can be null.
            if (died instanceof EntityPlayer)
                ClientAPI.EVENT_BUS.post(new PlayerDeathEvent((EntityPlayer) died, (EntityLivingBase) killer));
        }
    }
}
