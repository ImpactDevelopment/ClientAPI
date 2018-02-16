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

package clientapi.util.render.camera.defaults;

import clientapi.load.mixin.extension.IEntity;
import clientapi.util.math.Vec2;
import clientapi.util.render.camera.Camera;
import clientapi.util.render.camera.CameraHandle;

/**
 * An implementation of Camera that renders what is directly behind the player.
 *
 * @author Brady
 * @since 2/4/2017 12:00 PM
 */
public final class OverheadCamera extends Camera {

    public OverheadCamera(OverheadHandle handle) {
        super(handle);
    }

    @Override
    public void updateFramebuffer(float partialTicks) {
        OverheadHandle handle = (OverheadHandle) this.handle;

        IEntity entity = (IEntity) mc.player;
        this.position = entity.getPos().add(0, handle.camHeight(), 0);
        this.rotation = new Vec2(handle.camRotation(), 90);
        super.updateFramebuffer(partialTicks);
    }

    public interface OverheadHandle extends CameraHandle {

        /**
         * Controls the rotation of the camera. Can be used
         * to rotate with the player's angles if needed.
         *
         * @return The rotation
         */
        float camRotation();

        /**
         * Controls the height of the camera above the player
         *
         * @return The height above the player
         */
        double camHeight();
    }
}
