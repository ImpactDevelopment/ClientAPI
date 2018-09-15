/*
 * Copyright 2018 ImpactDevelopment
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

import clientapi.ClientAPI;
import clientapi.event.defaults.game.network.ServerEvent;
import me.zero.alpine.type.EventState;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Brady
 * @since 9/7/2017
 */
@Mixin(GuiGameOver.class)
public class MixinGuiGameOver extends GuiScreen {

    @Inject(
            method = "confirmClicked",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "net/minecraft/client/Minecraft.loadWorld(Lnet/minecraft/client/multiplayer/WorldClient;)V"
            )
    )
    private void postLoadWorld(CallbackInfo ci) {
        if (this.mc.getCurrentServerData() != null)
            ClientAPI.EVENT_BUS.post(new ServerEvent.Disconnect(EventState.POST, false, this.mc.getCurrentServerData()));
    }
}
