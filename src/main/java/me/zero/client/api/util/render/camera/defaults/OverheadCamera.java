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
