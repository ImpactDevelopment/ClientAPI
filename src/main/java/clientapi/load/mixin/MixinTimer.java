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

import clientapi.load.mixin.extension.ITimer;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static org.spongepowered.asm.lib.Opcodes.GETFIELD;

/**
 * @author Brady
 * @since 1/24/2018
 */
@Mixin(Timer.class)
public class MixinTimer implements ITimer {

    @Shadow private float tickLength;

    private float speed = 1;

    /**
     * Apply timer hack to the Timer class
     *
     * @author LeafHacker
     */
    @Redirect(
            method = "updateTimer",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/util/Timer;tickLength:F",
                    opcode = GETFIELD
            )
    )
    private float getTickLength(Timer self) {
        return this.tickLength / speed;
    }

    @Override
    public final void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public final float getSpeed() {
        return this.speed;
    }
}
