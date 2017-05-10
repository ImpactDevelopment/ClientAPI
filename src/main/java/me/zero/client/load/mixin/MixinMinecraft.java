package me.zero.client.load.mixin;

import com.google.gson.GsonBuilder;
import me.zero.client.api.Client;
import me.zero.client.api.ClientInfo;
import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.defaults.*;
import me.zero.client.api.event.handle.ClientHandler;
import me.zero.client.api.util.render.GlUtils;
import me.zero.client.load.ClientInitException;
import me.zero.client.wrapper.IMinecraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(Minecraft.class)
public abstract class MixinMinecraft implements IMinecraft {

    @Shadow @Final private Timer timer;
    @Shadow @Final private Session session;
    @Shadow private int rightClickDelayTimer;

    @Shadow public abstract void clickMouse();
    @Shadow public abstract void rightClickMouse();
    @Shadow public abstract void middleClickMouse();

    @Inject(method = "runTick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        EventManager.post(new TickEvent());
    }

    @Inject(method = "runGameLoop", at = @At("HEAD"))
    public void onLoop(CallbackInfo ci) {
        EventManager.post(new LoopEvent());
    }

    @Inject(method = "init", at = @At("RETURN"))
    public void init(CallbackInfo ci) {
        InputStream stream = this.getClass().getResourceAsStream("/client.json");

        if (stream == null)
            throw new ClientInitException("Unable to locate the Client.json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        ClientInfo clientInfo = new GsonBuilder().setPrettyPrinting().create().fromJson(reader, ClientInfo.class);

        if (clientInfo == null)
            throw new ClientInitException("");

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

        GlUtils.init();
        client.onInit(clientInfo);
        EventManager.subscribe(new ClientHandler());
    }

    @Inject(method = "clickMouse", at = @At("HEAD"))
    public void clickMouse(CallbackInfo ci) {
        EventManager.post(new ClickEvent(ClickEvent.MouseButton.LEFT));
    }

    @Inject(method = "rightClickMouse", at = @At("HEAD"))
    public void rightClickMouse(CallbackInfo ci) {
        EventManager.post(new ClickEvent(ClickEvent.MouseButton.RIGHT));
    }

    @Inject(method = "middleClickMouse", at = @At("HEAD"))
    public void middleClickMouse(CallbackInfo ci) {
        EventManager.post(new ClickEvent(ClickEvent.MouseButton.MIDDLE));
    }

    @ModifyVariable(method = "displayGuiScreen", at = @At("HEAD"))
    public GuiScreen displayGuiScreen(GuiScreen screen) {
        GuiEvent event = new GuiEvent(screen);
        EventManager.post(event);
        return event.getScreen();
    }

    @Inject(method = "loadWorld(Lnet/minecraft/client/multiplayer/WorldClient;Ljava/lang/String;)V", at = @At("HEAD"))
    public void loadWorld(@Nullable WorldClient worldClientIn, String loadingMessage, CallbackInfo ci) {
        if (worldClientIn != null)
            EventManager.post(new WorldLoadEvent(worldClientIn));
    }

    @Override
    public Timer getTimer() {
        return this.timer;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void clickMouse(ClickEvent.MouseButton button) {
        switch (button) {
            case LEFT:
                this.clickMouse();
                break;
            case RIGHT:
                this.rightClickMouse();
                break;
            case MIDDLE:
                this.middleClickMouse();
                break;
        }
    }

    @Override
    public void setRightClickDelayTimer(int delay) {
        this.rightClickDelayTimer = delay;
    }
}
