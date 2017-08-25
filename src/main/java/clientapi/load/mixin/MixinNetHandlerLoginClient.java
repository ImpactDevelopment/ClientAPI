package clientapi.load.mixin;

import clientapi.event.defaults.game.network.EncryptionRequestEvent;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.network.login.server.SPacketEncryptionRequest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static clientapi.ClientAPI.EVENT_BUS;

@Mixin(NetHandlerLoginClient.class)
public abstract class MixinNetHandlerLoginClient {

    /**
     * Hook for EncryptionRequestEvent
     *
     * @see EncryptionRequestEvent
     */
    @Inject(method = "handleEncryptionRequest", at = @At("HEAD"), cancellable = true)
    private void onLoginEncryptionRequest(SPacketEncryptionRequest packet, CallbackInfo ci) {
        EncryptionRequestEvent event = new EncryptionRequestEvent((NetHandlerLoginClient) (Object) this, packet);
        EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }
}
