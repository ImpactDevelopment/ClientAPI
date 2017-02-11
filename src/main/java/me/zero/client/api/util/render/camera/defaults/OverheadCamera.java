package me.zero.client.api.util.render.camera.defaults;

import me.zero.client.api.util.EntityUtil;
import me.zero.client.api.util.render.camera.Camera;
import me.zero.client.api.util.render.camera.CameraHandle;
import net.minecraft.util.math.Vec2f;

/**
 * An implementation of Camera that renders what is directly behind the player.
 *
 * @since 1.0
 *
 * Created by Brady on 2/4/2017.
 */
public class OverheadCamera extends Camera {

    public OverheadCamera(OverheadHandle handle) {
        super(handle);
    }

    @Override
    public void updateFramebuffer(float partialTicks) {
        OverheadHandle handle = (OverheadHandle) this.handle;

        EntityUtil entity = EntityUtil.get(mc.player);
        this.position = entity.getPos().addVector(0, handle.camHeight(), 0);
        this.rotation = new Vec2f(handle.camRotation(), 90);
        super.updateFramebuffer(partialTicks);
    }

    public interface OverheadHandle extends CameraHandle {

        /**
         * Controls the rotation of the camera. Can be used
         * to rotate with the player's angles if needed.
         *
         * @since 1.0
         *
         * @return The rotation
         */
        float camRotation();

        /**
         * Controls the height of the camera above the player
         *
         * @since 1.0
         *
         * @return The height above the player
         */
        double camHeight();
    }
}
