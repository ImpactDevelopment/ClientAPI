package me.zero.example.mod.mods;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.type.EventPriority;
import clientapi.event.defaults.game.render.RenderHudEvent;
import clientapi.module.Mod;
import clientapi.module.Module;
import clientapi.util.annotation.Label;
import clientapi.util.render.camera.defaults.OverheadCamera;
import clientapi.value.annotation.NumberValue;
import me.zero.example.mod.category.IRender;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

/**
 * Created by Brady on 2/11/2017.
 */
@Mod(name = "Camera", description = "", bind = Keyboard.KEY_B)
public final class Camera extends Module implements IRender {

    @Label(name = "Height", id = "offset", description = "How high up the camera is")
    @NumberValue(min = 10, max = 50)
    private double height = 30;

    // Must be instantiated before the Render2DEvent Listener
    private OverheadCamera camera = new OverheadCamera(new OverheadCamera.OverheadHandle() {

        @Override
        public float camRotation() {
            return mc.player.rotationYaw;
        }

        @Override
        public double camHeight() {
            return height;
        }

        @Override
        public boolean visible() {
            return Camera.this.getState();
        }

        @Override
        public boolean reflected() {
            return false;
        }
    });

    @EventHandler
    private Listener<RenderHudEvent> render2DListener = new Listener<>(event -> {
        height = 10;
        ScaledResolution sr = new ScaledResolution(mc);
        float size = sr.getScaledWidth() / 5F;
        camera.draw(sr.getScaledWidth() - size, 0, sr.getScaledWidth(), size);
    }, EventPriority.HIGHEST);
}
