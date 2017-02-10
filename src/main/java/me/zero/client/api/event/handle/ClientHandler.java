package me.zero.client.api.event.handle;

import me.zero.client.api.Client;
import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.defaults.KeyEvent;
import me.zero.client.api.event.defaults.Render2DEvent;
import me.zero.client.api.exception.ActionNotValidException;
import me.zero.client.api.module.Module;
import me.zero.client.api.util.ClientUtils;
import me.zero.client.api.util.render.camera.CameraManager;
import me.zero.client.load.ClientLoader;

/**
 * Some basic events that the client uses
 *
 * @since 1.0
 *
 * Created by Brady on 2/9/2017.
 */
public final class ClientHandler {

    private Client client;

    public ClientHandler(Client client) {
        if (ClientUtils.traceSource() != ClientLoader.class)
            throw new ActionNotValidException("The ClientHandler can only be initialized from the ClientLoader");

        this.client = client;
    }

    @EventHandler
    private void onRender2D(Render2DEvent event) {
        CameraManager.getInstance().getData().forEach(camera -> camera.updateFramebuffer(event.getPartialTicks()));
    }

    @EventHandler
    private void onKey(KeyEvent event) {
        client.getModuleManager().getData().stream().filter(module -> module.getBind() == event.getKey()).forEach(Module::toggle);
    }
}
