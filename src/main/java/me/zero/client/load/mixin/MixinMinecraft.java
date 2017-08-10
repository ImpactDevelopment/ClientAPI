/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.load.mixin;

import com.google.gson.GsonBuilder;
import me.zero.client.api.Client;
import me.zero.client.api.ClientInfo;
import me.zero.client.api.ClientAPI;
import me.zero.client.api.event.defaults.game.core.*;
import me.zero.client.api.event.defaults.game.render.GuiEvent;
import me.zero.client.api.event.defaults.game.world.WorldEvent;
import me.zero.client.api.event.handle.ClientHandler;
import me.zero.client.api.util.keybind.Keybind;
import me.zero.client.api.util.render.gl.GlUtils;
import me.zero.client.load.ClientInitException;
import me.zero.client.load.mixin.wrapper.IMinecraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static me.zero.client.api.event.defaults.game.core.ClickEvent.MouseButton.*;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(Minecraft.class)
public abstract class MixinMinecraft implements IMinecraft {

    @Accessor @Override public abstract Timer getTimer();
    @Accessor @Override public abstract void setSession(Session session);
    @Accessor @Override public abstract void setRightClickDelayTimer(int delay);

    @Shadow private void clickMouse() {}
    @Shadow private void rightClickMouse() {}
    @Shadow private void middleClickMouse() {}

    @Override
    public void clickMouse(ClickEvent.MouseButton button) {
        // IF statements are required because Mixin doesn't support SWITCH
        if (button == LEFT)
            clickMouse();
        if (button == RIGHT)
            rightClickMouse();
        if (button == MIDDLE)
            middleClickMouse();
    }

    @Inject(method = "runTick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new TickEvent());
    }

    @Inject(method = "runGameLoop", at = @At("HEAD"))
    public void onLoop(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new LoopEvent());
    }

    @Inject(method = "runTickKeyboard", at = @At(value = "INVOKE_ASSIGN", target = "org/lwjgl/input/Keyboard.getEventKeyState()Z", remap = false))
    public void onKeyEvent(CallbackInfo ci) {
        boolean down = Keyboard.getEventKeyState();
        int key = Keyboard.getEventKey();

        if (down)
            ClientAPI.EVENT_BUS.post(new KeyEvent(key));
        else
            // TODO: split into new KeyUp event
            Keybind.getKeybinds().stream()
                    .filter(bind -> bind.getKey() == key)
                    .forEach(Keybind::onRelease);
    }

    @Inject(method = "init", at = @At("RETURN"))
    public void init(CallbackInfo ci) {
        // Try and find the "client.json" config
        InputStream stream = this.getClass().getResourceAsStream("/client.json");

        if (stream == null)
            throw new ClientInitException("Unable to locate the Client.json");

        // Construct a ClientInfo object from the client json using GSON
        ClientInfo clientInfo = new GsonBuilder().setPrettyPrinting().create().fromJson(new BufferedReader(new InputStreamReader(stream)), ClientInfo.class);

        if (clientInfo == null)
            throw new ClientInitException("Unable to create ClientInfo from Client.json");

        // Attempt to instantiate the specified class from the ClientInfo
        Client client;
        try {
            Class<?> clientClass = Class.forName(clientInfo.getMain());
            if (clientClass != null && clientClass.getSuperclass().equals(Client.class)) {
                try {
                    client = (Client) clientClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new ClientInitException("Unable to instantiate Client");
                }
            } else {
                throw new ClientInitException("Client class is null or the superclass is not Client type");
            }
        } catch (ClassNotFoundException e) {
            throw new ClientInitException("Unable to find client class");
        }

        // Init GLUtils
        GlUtils.init();

        ClientHandler handler = new ClientHandler();

        // Init client
        client.setInfo(clientInfo);
        client.onInit(handler);

        ClientAPI.EVENT_BUS.subscribe(handler);
    }

    @Inject(method = "clickMouse", at = @At("HEAD"))
    public void clickMouse(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new ClickEvent(LEFT));
    }

    @Inject(method = "rightClickMouse", at = @At("HEAD"))
    public void rightClickMouse(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new ClickEvent(RIGHT));
    }

    @Inject(method = "middleClickMouse", at = @At("HEAD"))
    public void middleClickMouse(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new ClickEvent(MIDDLE));
    }

    @ModifyVariable(method = "displayGuiScreen", at = @At("HEAD"))
    public GuiScreen displayGuiScreen(GuiScreen screen) {
        GuiEvent event = new GuiEvent(screen);
        ClientAPI.EVENT_BUS.post(event);
        return event.getScreen();
    }

    @Inject(method = "loadWorld(Lnet/minecraft/client/multiplayer/WorldClient;Ljava/lang/String;)V", at = @At("HEAD"))
    public void loadWorld(@Nullable WorldClient worldClientIn, String loadingMessage, CallbackInfo ci) {
        // If the world is null, then it must be unloading
        if (worldClientIn != null)
            ClientAPI.EVENT_BUS.post(new WorldEvent.Load(worldClientIn));
        else
            ClientAPI.EVENT_BUS.post(new WorldEvent.Unload());
    }

    @Inject(method = "shutdown", at = @At("HEAD"), cancellable = true)
    public void shutdown(CallbackInfo ci) {
        GameShutdownEvent event = new GameShutdownEvent();
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }
}
