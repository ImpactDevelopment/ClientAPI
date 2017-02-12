package me.zero.client.api.util.render.camera;

import me.zero.client.api.util.EntityUtil;
import me.zero.client.api.util.math.Vec2;
import me.zero.client.api.util.math.Vec3;
import me.zero.client.api.util.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;

/**
 * The base for Cameras.
 *
 * All credits go to 10askinsw, he made the original Camera file.
 *
 * @since 1.0
 *
 * Created by Brady on 2/4/2017.
 */
public class Camera {

    protected static Minecraft mc = Minecraft.getMinecraft();
    private static boolean capturing;

    protected Vec3 position;
    protected Vec2 rotation;
    private Framebuffer framebuffer;
    protected CameraHandle handle;
    private int lastWidth, lastHeight;
    private boolean updated, reflected;

    public Camera(CameraHandle handle) {
        this.handle = handle;
        this.reflected = handle.reflected();
        this.framebuffer = new Framebuffer(handle.width(), handle.height(), true);
        this.createNewFramebuffer();

        CameraManager.getInstance().register(this);
    }

    /**
     * Updates the current Framebuffer
     * Can only be called from {@code GuiIngame}
     */
    public void updateFramebuffer(float partialTicks) {
        // Check if we are able to update the framebuffer
        if (Camera.capturing || !mc.inGameHasFocus || !handle.visible())
            return;

        // Gets the entity util for the render view entity and if it is null then the code stops
        EntityUtil entity = EntityUtil.get(mc.getRenderViewEntity());
        if (entity.getEntity() == null)
            return;

        // Preserve all of the settings before entity change them.
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
    private void render(EntityUtil entity, float partialTicks) {
        // Setup camera
        entity.setAll(this.position, this.rotation);
        mc.displayWidth = handle.width();
        mc.displayWidth = handle.height();
        mc.gameSettings.thirdPersonView = 0;
        mc.gameSettings.viewBobbing = false;
        mc.gameSettings.hideGUI = true;

        // Run framebuffer update check
        checkUpdate();
        if (!updated)
            createNewFramebuffer();

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
    public void draw(float x, float y, float x1, float y1) {
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
     * @since 1.0
     *
     * @return Whether or not the Framebuffer has been updated
     */
    public boolean isUpdated() {
        return this.updated;
    }

    /**
     * Checks if the framebuffer requires an update
     *
     * @since 1.0
     */
    private void checkUpdate() {
        if (lastWidth != handle.width() || lastHeight != handle.height())
            updated = false;

        lastWidth = handle.width();
        lastHeight = handle.height();
    }

    /**
     * Resizes the Framebuffer to the current
     * handle width and height
     *
     * @since 1.0
     */
    private void createNewFramebuffer() {
        this.framebuffer.createFramebuffer(handle.width(), handle.height());
    }

    /**
     * Sets the capture state of the camera
     *
     * @since 1.0
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
     * @return Whether or not any of the cameras are currently capturing
     */
    public static boolean isCapturing() {
        return capturing;
    }
}
