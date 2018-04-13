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
import clientapi.ClientInfo;
import clientapi.event.defaults.game.core.*;
import clientapi.event.defaults.game.render.GuiDisplayEvent;
import clientapi.event.defaults.game.world.WorldEvent;
import clientapi.event.handle.ClientHandler;
import clientapi.load.ClientInitException;
import clientapi.load.mixin.extension.IMinecraft;
import clientapi.util.io.MouseKeyTracker;
import clientapi.util.io.StreamReader;
import clientapi.util.render.gl.GLUtils;
import com.google.gson.GsonBuilder;
import me.zero.alpine.type.EventState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
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

import static clientapi.event.defaults.game.core.ClickEvent.MouseButton.*;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(Minecraft.class)
public abstract class MixinMinecraft implements IMinecraft {

    @Shadow public GuiScreen currentScreen;

    @Accessor @Override public abstract Timer getTimer();
    @Accessor @Override public abstract void setSession(Session session);
    @Accessor @Override public abstract void setRightClickDelayTimer(int delay);

    @Shadow private void clickMouse() {}
    @Shadow private void rightClickMouse() {}
    @Shadow private void middleClickMouse() {}

    @Override
    public void clickMouse(int button) {
        // IF statements are required because Mixin doesn't support SWITCH
        if (button == LEFT)
            clickMouse();
        if (button == RIGHT)
            rightClickMouse();
        if (button == MIDDLE)
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
    private void preRunGameLoop(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new LoopEvent(EventState.PRE));
    }

    @Inject(method = "runGameLoop", at = @At("RETURN"))
    private void postRunGameLoop(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new LoopEvent(EventState.POST));
    }

    @Inject(method = "runTickKeyboard", at = @At(value = "INVOKE_ASSIGN", target = "org/lwjgl/input/Keyboard.getEventKeyState()Z", remap = false))
    private void onKeyEvent(CallbackInfo ci) {
        if (currentScreen != null)
            return;

        boolean down = Keyboard.getEventKeyState();
        int key = Keyboard.getEventKey();
        char ch = Keyboard.getEventCharacter();

        ClientAPI.EVENT_BUS.post(down ? new KeyEvent(key, ch) : new KeyUpEvent(key, ch));
    }

    @Inject(method = "runTickMouse", at = @At(value = "INVOKE_ASSIGN", id = "org/lwjgl/input/Mouse.getEventButton()I"))
    private void onMouseEvent(CallbackInfo info) {
        if (currentScreen != null)
            return;

        int button = Mouse.getEventButton();
        if (button > 0 && MouseKeyTracker.INSTANCE.wasButtonPressed(button, Mouse.getEventButtonState())) {
            ClientAPI.EVENT_BUS.post(new ClickEvent(button));
        }
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        // Try and find the "client.json" config
        InputStream stream = this.getClass().getResourceAsStream("/client.json");

        if (stream == null)
            throw new ClientInitException("Unable to locate Client Configuration (client.json)");

        // Construct a ClientInfo object from the client json using GSON
        ClientInfo clientInfo = new GsonBuilder().setPrettyPrinting().create().fromJson(new StreamReader(stream).all(), ClientInfo.class);

        if (clientInfo == null)
            throw new ClientInitException("Unable to create ClientInfo from client.json");

        // Attempt to instantiate the specified class from the ClientInfo
        Client client;
        try {
            Class<?> clientClass = Class.forName(clientInfo.getMain());
            Constructor<?> constructor;
            if (clientClass != null && clientClass.getSuperclass().equals(Client.class) && (constructor = clientClass.getConstructor(ClientInfo.class)) != null) {
                client = (Client) constructor.newInstance(clientInfo);
            } else {
                throw new ClientInitException("Client class is null or the superclass is not Client type");
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ClientInitException("Unable to instantiate Client");
        } catch (ClassNotFoundException e) {
            throw new ClientInitException("Unable to find client class");
        } catch (NoSuchMethodException e) {
            throw new ClientInitException("Unable to find constructor with valid parameters");
        }

        // Init GLUtils
        GLUtils.init();

        // Init client
        client.onInit(clientInfo);

        ClientAPI.EVENT_BUS.subscribe(ClientHandler.INSTANCE);
    }

    @ModifyVariable(method = "displayGuiScreen", at = @At("HEAD"))
    private GuiScreen displayGuiScreen(GuiScreen screen) {
        GuiDisplayEvent event = new GuiDisplayEvent(screen);
        ClientAPI.EVENT_BUS.post(event);
        return event.getScreen();
    }

    @Inject(method = "loadWorld(Lnet/minecraft/client/multiplayer/WorldClient;Ljava/lang/String;)V", at = @At("HEAD"))
    private void loadWorld(@Nullable WorldClient worldClientIn, String loadingMessage, CallbackInfo ci) {
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
