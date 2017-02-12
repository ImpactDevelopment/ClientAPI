package me.zero.example.mod.mods;

import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.defaults.Render2DEvent;
import me.zero.client.api.event.type.EventPriority;
import me.zero.client.api.module.Mod;
import me.zero.client.api.module.Module;
import me.zero.client.api.util.render.camera.defaults.OverheadCamera;
import me.zero.client.api.value.annotation.NumberValue;
import me.zero.example.mod.category.IRender;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

/**
 * Created by Brady on 2/11/2017.
 */
@Mod(name = "Camera", description = "", bind = Keyboard.KEY_B)
public class Camera extends Module implements IRender {

    @NumberValue(name = "Height", minimum = 10, maximum = 50)
    private double height = 30;

    @EventHandler(EventPriority.HIGHEST)
    private void onRender2D(Render2DEvent event) {
        ScaledResolution sr = new ScaledResolution(mc);
        float size = sr.getScaledWidth() / 5F;
        camera.draw(sr.getScaledWidth() - size, 0, sr.getScaledWidth(), size);
    }

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
            return true;
        }

        @Override
        public boolean reflected() {
            return false;
        }

        @Override
        public int width() {
            return mc.displayWidth;
        }

        @Override
        public int height() {
            return mc.displayHeight;
        }
    });
}
