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
public class RearViewCamera extends Camera {

    public RearViewCamera(CameraHandle handle) {
        super(handle);
    }

    @Override
    public void updateFramebuffer(float partialTicks) {
        EntityUtil entity = EntityUtil.get(mc.player);
        Vec2f rotations = entity.getRotations();
        this.position = entity.getPos();
        this.rotation = new Vec2f(rotations.x + 180, rotations.y * -1);
        super.updateFramebuffer(partialTicks);
    }
}
