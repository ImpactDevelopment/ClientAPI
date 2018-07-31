/*
 * Copyright 2018 ImpactDevelopment
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

package clientapi.load.mixin;

import clientapi.Client;
import clientapi.ClientAPI;
import clientapi.event.defaults.game.core.GameShutdownEvent;
import clientapi.event.defaults.game.core.LoopEvent;
import clientapi.event.defaults.game.core.TickEvent;
import clientapi.event.defaults.game.render.GuiDisplayEvent;
import clientapi.event.defaults.game.world.WorldEvent;
import clientapi.event.handle.ClientHandler;
import clientapi.load.ClientInitException;
import clientapi.load.config.ClientConfiguration;
import clientapi.load.config.JsonConfiguration;
import clientapi.load.mixin.extension.IMinecraft;
import clientapi.util.render.gl.GLUtils;
import me.zero.alpine.type.EventState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(Minecraft.class)
public abstract class MixinMinecraft implements IMinecraft {

//    @Accessor @Override public abstract Timer getTimer();
//    @Accessor @Override public abstract void setSession(Session session);
    @Accessor @Override public abstract void setRightClickDelayTimer(int delay);

    @Shadow private void clickMouse() {}
    @Shadow private void rightClickMouse() {}
    @Shadow private void middleClickMouse() {}

    @Override
    public void clickMouse(int button) {
        // IF statements are required because Mixin doesn't support SWITCH
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT)
            clickMouse();
        if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT)
            rightClickMouse();
        if (button == GLFW.GLFW_MOUSE_BUTTON_MIDDLE)
            middleClickMouse();
    }

    @Inject(method = "runTick", at = @At("HEAD"))
    private void preRunTick(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new TickEvent(EventState.PRE));
    }

    @Inject(method = "runTick", at = @At("RETURN"))
    private void postRunTick(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new TickEvent(EventState.POST));
    }

    @Inject(method = "runGameLoop", at = @At("HEAD"))
    private void preRunGameLoop(boolean p_195542_1_, CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new LoopEvent(EventState.PRE));
    }

    @Inject(method = "runGameLoop", at = @At("RETURN"))
    private void postRunGameLoop(boolean p_195542_1_, CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new LoopEvent(EventState.POST));
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        InputStream stream = this.getClass().getResourceAsStream("/client.json");

        if (stream == null)
            throw new ClientInitException("Unable to locate Client Configuration");

        // Construct a ClientConfiguration object from the client json using GSON
        ClientConfiguration clientConfig = JsonConfiguration.loadConfiguration(stream, ClientConfiguration.class);

        if (clientConfig == null)
            throw new ClientInitException("Unable to create Client Configuration from client.json");

        // Attempt to instantiate the specified class from the client configuration
        Client client;
        try {
            Class<?> clientClass = Class.forName(clientConfig.getMainClass());
            Constructor<?> constructor;
            if (clientClass != null && clientClass.getSuperclass().equals(Client.class) && (constructor = clientClass.getConstructor(ClientConfiguration.class)) != null) {
                client = (Client) constructor.newInstance(clientConfig);
            } else {
                throw new ClientInitException("Client class is null or the superclass is not Client type");
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ClientInitException("Unable to instantiate main client class");
        } catch (ClassNotFoundException e) {
            throw new ClientInitException("Unable to find client class");
        } catch (NoSuchMethodException e) {
            throw new ClientInitException("Unable to find constructor with valid parameters");
        }

        // Init GLUtils
        GLUtils.init();

        // Init client
        client.init();

        ClientAPI.EVENT_BUS.subscribe(ClientHandler.INSTANCE);
    }

    @ModifyVariable(method = "displayGuiScreen", at = @At("HEAD"))
    private GuiScreen displayGuiScreen$HEAD(GuiScreen screen) {
        GuiDisplayEvent event = new GuiDisplayEvent(EventState.PRE, screen);
        ClientAPI.EVENT_BUS.post(event);
        return event.getScreen();
    }

    @Inject(method = "displayGuiScreen", at = @At("RETURN"))
    private void displayGuiScreen$RETURN(GuiScreen screen, CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new GuiDisplayEvent(EventState.POST, screen));
    }

    @Inject(method = "loadWorld(Lnet/minecraft/client/multiplayer/WorldClient;Lnet/minecraft/client/gui/GuiScreen;)V", at = @At("HEAD"))
    private void loadWorld(@Nullable WorldClient worldClientIn, GuiScreen loadingScreen, CallbackInfo ci) {
        // If the world is null, then it must be unloading
        if (worldClientIn != null)
            ClientAPI.EVENT_BUS.post(new WorldEvent.Load(worldClientIn));
        else
            ClientAPI.EVENT_BUS.post(new WorldEvent.Unload());
    }

    @Inject(method = "shutdown", at = @At("HEAD"), cancellable = true)
    private void shutdown(CallbackInfo ci) {
        GameShutdownEvent event = new GameShutdownEvent();
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }
}
