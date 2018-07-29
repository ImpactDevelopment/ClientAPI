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
import clientapi.event.defaults.game.core.CharacterEvent;
import clientapi.event.defaults.game.core.KeyEvent;
import net.minecraft.client.KeyboardListener;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Brady
 * @since 7/28/2018 8:29 PM
 */
@Mixin(KeyboardListener.class)
public class MixinKeyboardListener {

    @Shadow @Final private Minecraft mc;

    @Inject(method = "onKeyEvent", at = @At(value = "INVOKE", target = "net/minecraft/client/util/InputMappings.isKeyDown(I)Z", ordinal = 4))
    private void onKeyEvent(long windowPointer, int keyCode, int scanCode, int action, int modifiers, CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new KeyEvent(keyCode, action, modifiers));
    }

    @Inject(method = "onCharEvent", at = @At("HEAD"))
    private void onCharEvent(long windowPointer, int codePoint, int modifiers, CallbackInfo ci) {
        if (windowPointer != mc.mainWindow.getWindowPointer() || mc.currentScreen != null)
            return;

        if (Character.charCount(codePoint) == 1) {
            ClientAPI.EVENT_BUS.post(new CharacterEvent((char) codePoint, modifiers));
        } else {
            for (char character : Character.toChars(codePoint)) {
                ClientAPI.EVENT_BUS.post(new CharacterEvent(character, modifiers));
            }
        }
    }
}
