/*
 * Copyright 2017 ZeroMemes
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

package clientapi.load.mixin.packet.play.server;

import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 9/10/2017 2:09 PM
 */
@Mixin(SPacketSoundEffect.class)
public interface ISPacketSoundEffect {

    @Accessor SoundEvent getSound();

    @Accessor void setSound(SoundEvent sound);

    @Accessor SoundCategory getCategory();

    @Accessor void setCategory(SoundCategory category);

    @Accessor int getPosX();

    @Accessor void setPosX(int posX);

    @Accessor int getPosY();

    @Accessor void setPosY(int posY);

    @Accessor int getPosZ();

    @Accessor void setPosZ(int posZ);

    @Accessor float getSoundVolume();

    @Accessor void setSoundVolume(float soundVolume);

    @Accessor float getSoundPitch();

    @Accessor void setSoundPitch(float soundPitch);
}
