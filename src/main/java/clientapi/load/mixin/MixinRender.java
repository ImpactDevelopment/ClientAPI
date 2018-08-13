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
import clientapi.event.defaults.game.render.RenderEntityLabelEvent;
import clientapi.event.defaults.game.render.TeamColorEvent;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.scoreboard.ScorePlayerTeam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

/**
 * @author Brady
 * @since 5/21/2017
 */
@Mixin(Render.class)
public class MixinRender {

    @Inject(method = "getTeamColor", at = @At("RETURN"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void getTeamColor(Entity entityIn, CallbackInfoReturnable<Integer> cir, int i, ScorePlayerTeam scoreplayerteam) {
        TeamColorEvent event = new TeamColorEvent(entityIn, i);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            cir.setReturnValue(event.getColor());
    }

    @Inject(method = "renderLivingLabel", at = @At("HEAD"), cancellable = true)
    private void renderLivingLabel(Entity entityIn, String str, double x, double y, double z, int maxDistance, CallbackInfo ci) {
        RenderEntityLabelEvent event = new RenderEntityLabelEvent(entityIn, str);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }
}
