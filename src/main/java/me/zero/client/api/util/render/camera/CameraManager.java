package me.zero.client.api.util.render.camera;

import me.zero.client.api.manage.Manager;

/**
 * Used to store all of the
 *
 * @since 1.0
 *
 * Created by Brady on 2/9/2017.
 */
public class CameraManager extends Manager<Camera> {

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
     * @since 1.0
     *
     * @param camera Camera being registered
     */
    void register(Camera camera) {
        if (!this.getData().contains(camera))
            this.addData(camera);
    }

    /**
     * @since 1.0
     *
     * @return The instance of the CameraManager
     */
    public static CameraManager getInstance() {
        return CameraManager.instance;
    }
}
