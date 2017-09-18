/*
 * Copyright 2017 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
