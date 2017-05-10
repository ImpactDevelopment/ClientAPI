package me.zero.client.api.util.render.camera;

import me.zero.client.api.manage.Manager;

/**
 * Used to store all of the
 *
 * @author Brady
 * @since 2/9/2017 12:00 PM
 */
public final class CameraManager extends Manager<Camera> {

    /**
     * Instance of the CameraManager
     */
    private static final CameraManager instance = new CameraManager();

    private CameraManager() {
        super("Camera");
    }

    @Override
    public void load() {}

    @Override
    public void save() {}

    /**
     * Registers a camera to the Manager
     *
     * @param camera Camera being registered
     */
    final void register(Camera camera) {
        if (!this.getData().contains(camera))
            this.addData(camera);
    }

    /**
     * @return The instance of the CameraManager
     */
    public static CameraManager getInstance() {
        return CameraManager.instance;
    }
}
