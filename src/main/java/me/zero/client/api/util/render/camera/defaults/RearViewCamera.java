/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.api.util.render.camera.defaults;

import me.zero.client.api.util.math.Vec2;
import me.zero.client.api.util.render.camera.Camera;
import me.zero.client.api.util.render.camera.CameraHandle;
import me.zero.client.load.mixin.wrapper.IEntity;

/**
 * An implementation of Camera that renders what is directly behind the player.
 *
 * @author Brady
 * @since 2/4/2017 12:00 PM
 */
public final class RearViewCamera extends Camera {

    public RearViewCamera(CameraHandle handle) {
        super(handle);
    }

    @Override
    public void updateFramebuffer(float partialTicks) {
        IEntity entity = (IEntity) mc.player;
        Vec2 rotations = entity.getRotations();
        this.position = entity.getPos();
        this.rotation = new Vec2(rotations.getX() + 180, rotations.getY() * -1);
        super.updateFramebuffer(partialTicks);
    }
}
