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

package clientapi.util.render.camera;

import clientapi.util.interfaces.Helper;
import clientapi.util.math.Vec2;
import clientapi.util.math.Vec3;
import clientapi.util.render.RenderUtils;
import clientapi.load.mixin.wrapper.IEntity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.Display;

/**
 * The base for Cameras.
 *
 * All credits go to 10askinsw, he made the original Camera file.
 *
 * @author Brady
 * @since 2/4/2017 12:00 PM
 */
public class Camera implements Helper {

    /**
     * State to keep track of whether or not any cameras are capturing
     */
    private static boolean capturing;

    /**
     * The position of the camera
     */
    protected Vec3 position;

    /**
     * The angle rotations of the camera
     */
    protected Vec2 rotation;

    /**
     * {@code Framebuffer} rendered to
     */
    private Framebuffer framebuffer;

    /**
     *
     */
    protected CameraHandle handle;

    /**
     * The last width of the display window
     */
    private int lastWidth;

    /**
     * The last height of the display window
     */
    private int lastHeight;

    /**
     * Whether or
     */
    private boolean updated;
    private boolean reflected;

    public Camera(CameraHandle handle) {
        this.handle = handle;
        this.reflected = handle.reflected();
        this.framebuffer = new Framebuffer(handle.width(), handle.height(), true);

        CameraManager.getInstance().register(this);
    }

    /**
     * Updates the current Framebuffer
     *
     * @param partialTicks The Render 2D partial ticks
     */
    public void updateFramebuffer(float partialTicks) {
        // Check if we are able to update the framebuffer
        if (Camera.capturing || !mc.inGameHasFocus || !handle.visible())
            return;

        // Gets the entity util for the render view entity and if it is null then the code stops
        IEntity entity = (IEntity) mc.getRenderViewEntity();
        if (entity == null)
            return;

        // Preserve all of the settings before changing them on rendering
        Vec3 pos = entity.getPos(), prevPos = entity.getPrevPos(), lastTickPos = entity.getLastTickPos();
        Vec2 angles = entity.getRotations(), prevAngles = entity.getPrevRotations();
        int displayWidth = mc.displayWidth, displayHeight = mc.displayHeight, thirdPersonView = mc.gameSettings.thirdPersonView;
        boolean hideGUI = mc.gameSettings.hideGUI, viewBobbing = mc.gameSettings.viewBobbing;

        // Render the camera
        this.render(entity, partialTicks);

        // Reset all of the settings
        entity.setAll(pos, prevPos, lastTickPos, angles, prevAngles);
        mc.displayWidth                 = displayWidth;
        mc.displayHeight                = displayHeight;
        mc.gameSettings.thirdPersonView = thirdPersonView;
        mc.gameSettings.hideGUI         = hideGUI;
        mc.gameSettings.viewBobbing     = viewBobbing;
    }

    /**
     * Renders the camera to the Framebuffer
     *
     * @param entity The game's view entity
     */
    private void render(IEntity entity, float partialTicks) {
        // Setup camera
        entity.setAll(this.position, this.rotation);
        mc.displayWidth = handle.width();
        mc.displayHeight = handle.height();
        mc.gameSettings.thirdPersonView = 0;
        mc.gameSettings.viewBobbing = false;
        mc.gameSettings.hideGUI = true;

        // Run framebuffer update check
        checkUpdate();

        // Render camera
        setCapture(true);
        mc.entityRenderer.updateCameraAndRender(partialTicks, System.nanoTime());
        setCapture(false);

        updated = true;
    }

    /**
     * Draws the camera to the screen.
     * Stretches the Framebuffer over the specified area.
     */
    public final void draw(float x, float y, float x1, float y1) {
        GlStateManager.pushMatrix();
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.enableColorMaterial();

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        framebuffer.bindFramebufferTexture();
        if (reflected)
            RenderUtils.drawReflectedTexturedRect(x, y, x1, y1);
        else
            RenderUtils.drawFlippedTexturedRect(x, y, x1, y1);
        framebuffer.unbindFramebufferTexture();

        GlStateManager.popMatrix();
    }

    /**
     * Checks if the framebuffer requires an update
     */
    private void checkUpdate() {
        if (lastWidth != handle.width() || lastHeight != handle.height())
            this.framebuffer.createFramebuffer(handle.width(), handle.height());

        lastWidth = handle.width();
        lastHeight = handle.height();
    }

    /**
     * Sets the capture state of the camera
     *
     * @param capture Whether or not to capture
     */
    private void setCapture(boolean capture) {
        if (capture)
            framebuffer.bindFramebuffer(true);
        else
            framebuffer.unbindFramebuffer();

        Camera.capturing = capture;
    }

    /**
     * Returns the visibility state of the Camera
     *
     * @return Whether or not the camera is visible
     */
    public boolean isVisible() {
        return this.handle.visible();
    }

    /**
     * @return Whether or not any of the cameras are currently capturing
     */
    public static boolean isCapturing() {
        return capturing;
    }
}
