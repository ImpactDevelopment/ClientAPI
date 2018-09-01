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
import clientapi.event.defaults.game.render.SpawnParticleEvent;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Brady
 * @since 4/10/2018
 */
@Mixin(ParticleManager.class)
public class MixinParticleManager {

    @Redirect(
            method = "spawnEffectParticle",
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/client/particle/IParticleFactory.createParticle(ILnet/minecraft/world/World;DDDDDD[I)Lnet/minecraft/client/particle/Particle;"
            )
    )
    private Particle spawnParticle(IParticleFactory particleFactory, int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... parameters) {
        SpawnParticleEvent event = new SpawnParticleEvent(particleID, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            return null;

        return particleFactory.createParticle(particleID, worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, parameters);
    }
}
