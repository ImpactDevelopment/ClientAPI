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
import clientapi.event.defaults.game.render.HudOverlayEvent;
import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.fluid.Fluid;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.tags.Tag;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.spongepowered.asm.lib.Opcodes.GETFIELD;

/**
 * @author Brady
 * @since 5/24/2018 10:53 AM
 */
@Mixin(GuiIngame.class)
public class MixinGuiIngame {

    @Inject(method = "func_194808_p", at = @At("HEAD"), cancellable = true)
    private void renderPumpkinOverlay(CallbackInfo ci) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.PUMPKIN);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }

    @Inject(method = "func_194802_a", at = @At("HEAD"), cancellable = true)
    private void renderScoreboard(ScoreObjective objective, CallbackInfo ci) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.SCOREBOARD);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }

    @Inject(method = "func_194807_n", at = @At("HEAD"), cancellable = true)
    private void renderPlayerStats(CallbackInfo ci) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.STAT_ALL);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }

    @Redirect(method = "func_194807_n", at = @At(value = "INVOKE", target = "net/minecraft/entity/player/EntityPlayer.getTotalArmorValue()I"))
    private int renderPlayerStats$getTotalArmorValue(EntityPlayer player) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.STAT_ARMOR);
        ClientAPI.EVENT_BUS.post(event);
        return event.isCancelled() ? 0 : player.getTotalArmorValue();
    }

    @Redirect(method = "func_194807_n", at = @At(value = "INVOKE", target = "net/minecraft/util/math/MathHelper.ceil(F)I", ordinal = 4))
    private int renderPlayerStats$ceil$4(float value) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.STAT_HEALTH);
        ClientAPI.EVENT_BUS.post(event);
        return event.isCancelled() ? 0 : MathHelper.ceil(value);
    }

    @Redirect(method = "func_194807_n", at = @At(value = "INVOKE", target = "net/minecraft/entity/player/EntityPlayer.getRidingEntity()Lnet/minecraft/entity/Entity;"))
    private Entity renderPlayerStats$getRidingEntity(EntityPlayer player) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.STAT_FOOD);
        ClientAPI.EVENT_BUS.post(event);
        return event.isCancelled() ? player : player.getRidingEntity();
    }

    @Redirect(method = "func_194807_n", at = @At(value = "INVOKE", target = "net/minecraft/entity/player/EntityPlayer.func_208600_a(Lnet/minecraft/tags/Tag;)Z"))
    private boolean renderPlayerStats$func_208600_a(EntityPlayer player, Tag<Fluid> fluidTag) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.STAT_AIR);
        ClientAPI.EVENT_BUS.post(event);
        return !event.isCancelled() && player.func_208600_a(fluidTag);
    }

    @Inject(method = "func_194804_b", at = @At("HEAD"), cancellable = true)
    private void renderExpBar(int x, CallbackInfo ci) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.EXP_BAR);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }

    @Inject(method = "func_194800_d", at = @At("HEAD"), cancellable = true)
    private void renderVignette(float lightLevel, CallbackInfo ci) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.VIGNETTE);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }

    @Redirect(method = "func_194798_c", at = @At(value = "INVOKE", target = "net/minecraft/client/gui/GuiIngame.drawTexturedModalRect(IIIIII)V", ordinal = 0))
    private void renderAttackIndicator(GuiIngame gui, int x, int y, int textureX, int textureY, int width, int height) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.CROSSHAIR);
        ClientAPI.EVENT_BUS.post(event);
        if (!event.isCancelled())
            gui.drawTexturedModalRect(x, y, textureX, textureY, width, height);
    }

    @Redirect(method = "func_194798_c", at = @At(value = "FIELD", target = "net/minecraft/client/GameSettings.attackIndicator:I", opcode = GETFIELD))
    private int renderAttackIndicator$attackIndicator(GameSettings gameSettings) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.ATTACK_INDICATOR);
        ClientAPI.EVENT_BUS.post(event);
        return event.isCancelled() ? 0 : gameSettings.attackIndicator;
    }

    @Redirect(method = "func_194806_b", at = @At(value = "FIELD", target = "net/minecraft/client/GameSettings.attackIndicator:I", opcode = GETFIELD))
    private int renderHotbar$attackIndicator(GameSettings gameSettings) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.ATTACK_INDICATOR);
        ClientAPI.EVENT_BUS.post(event);
        return event.isCancelled() ? 0 : gameSettings.attackIndicator;
    }

    @Inject(method = "func_194803_a", at = @At("HEAD"), cancellable = true)
    private void renderHorseJumpBar(int x, CallbackInfo ci) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.JUMP_BAR);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }

    @Inject(method = "func_194805_e", at = @At("HEAD"), cancellable = true)
    private void renderPortal(float timeInPortal, CallbackInfo ci) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.PORTAL);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }

    @Inject(method = "func_194801_c", at = @At("HEAD"), cancellable = true)
    private void renderSelectedItem(CallbackInfo ci) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.SELECTED_ITEM_TOOLTIP);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }

    @Inject(method = "func_194809_b", at = @At("HEAD"), cancellable = true)
    private void renderPotionEffects(CallbackInfo ci) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.POTION_EFFECTS);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }
}
