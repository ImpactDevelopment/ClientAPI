package me.zero.client.load.inject.transformer.reference.obfuscation;

import io.netty.channel.ChannelHandlerContext;
import me.zero.client.load.inject.transformer.reference.ClassReference;
import me.zero.client.load.inject.transformer.reference.MethodReference;

import static me.zero.client.load.inject.transformer.reference.obfuscation.Obfuscation.MCP;
import static me.zero.client.load.inject.transformer.reference.obfuscation.Obfuscation.VANILLA;
import static me.zero.client.load.inject.transformer.reference.obfuscation.ObfuscationName.from;

/**
 * Contains class and method references for the game
 * that are required by the API's default transformers.
 *
 * @see me.zero.client.load.inject.transformer.ITransformer
 *
 * @since 1.0
 *
 * Created by Brady on 1/22/2017.
 */
public interface MCMappings {

    // Classes
    ClassReference Main = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.main.Main"), from(VANILLA, "net.minecraft.client.main.Main") });
    ClassReference Minecraft = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.Minecraft"), from(VANILLA, "bes") });
    ClassReference GuiIngame = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.gui.GuiIngame"), from(VANILLA, "bfh") });
    ClassReference NetHandlerPlayClient = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.network.NetHandlerPlayClient"), from(VANILLA, "bno") });
    ClassReference NetworkManager = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.network.NetworkManager"), from(VANILLA, "er") });
    ClassReference Packet = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.network.Packet"), from(VANILLA, "fm") });
    ClassReference EntityPlayerSP = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.entity.EntityPlayerSP"), from(VANILLA, "bps") });
    ClassReference Entity = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.entity.Entity"), from(VANILLA, "sn") });
    ClassReference MoverType = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.entity.MoverType"), from(VANILLA, "tc") });
    ClassReference World = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.world.World"), from(VANILLA, "bpp") });
    ClassReference WorldClient = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.multiplayer.WorldClient"), from(VANILLA, "bnq") });
    ClassReference Profiler = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.profiler.Profiler"), from(VANILLA, "bqe") });
    ClassReference GuiNewChat = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.gui.GuiNewChat"), from(VANILLA, "bfn") });
    ClassReference ITextComponent = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.util.text"), from(VANILLA, "fb") });
    ClassReference EntityRenderer = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.renderer.EntityRenderer"), from(VANILLA, "bqe") });

    // Methods
    MethodReference main = new MethodReference(new ObfuscationName[] { from(MCP, "main"), from(VANILLA, "main")}, Void.TYPE);
    MethodReference runTick = new MethodReference(new ObfuscationName[] { from(MCP, "runTick"), from(VANILLA, "t") }, Void.TYPE);
    MethodReference runGameLoop = new MethodReference(new ObfuscationName[] { from(MCP, "runGameLoop"), from(VANILLA, "av") }, Void.TYPE);
    MethodReference startGame = new MethodReference(new ObfuscationName[] { from(MCP, "startGame"), from(VANILLA, "an") }, Void.TYPE);
    MethodReference renderGameOverlay = new MethodReference(new ObfuscationName[] { from(MCP, "renderGameOverlay"), from(VANILLA, "a") }, Void.TYPE, Float.TYPE);
    MethodReference channelRead0 = new MethodReference(new ObfuscationName[] { from(MCP, "channelRead0"), from(VANILLA, "a" )}, Void.TYPE, ChannelHandlerContext.class, Packet);
    MethodReference sendPacket1 = new MethodReference(new ObfuscationName[] { from(MCP, "sendPacket" ), from(VANILLA, "a")}, Void.TYPE, Packet);
    MethodReference onUpdate = new MethodReference(new ObfuscationName[] { from(MCP, "onUpdate"), from(VANILLA, "A_") }, Void.TYPE);
    MethodReference moveEntity = new MethodReference(new ObfuscationName[] { from(MCP, "moveEntity"), from(VANILLA, "a")}, Void.TYPE, MoverType, Double.TYPE, Double.TYPE, Double.TYPE);
    MethodReference clickMouse = new MethodReference(new ObfuscationName[] { from(MCP, "clickMouse"), from(VANILLA, "aw")}, Void.TYPE);
    MethodReference rightClickMouse = new MethodReference(new ObfuscationName[] { from(MCP, "rightClickMouse"), from(VANILLA, "ax")}, Void.TYPE);
    MethodReference middleClickMouse = new MethodReference(new ObfuscationName[] { from(MCP, "middleClickMouse"), from(VANILLA, "aC")}, Void.TYPE);
    MethodReference loadWorld = new MethodReference(new ObfuscationName[] { from(MCP, "loadWorld"), from(VANILLA, "a") }, Void.TYPE, WorldClient, String.class);
    MethodReference startSection = new MethodReference(new ObfuscationName[] { from(MCP, "startSection"), from(VANILLA, "a") }, Void.TYPE, String.class);
    MethodReference onLivingUpdate = new MethodReference(new ObfuscationName[] { from(MCP, "onLivingUpdate"), from(VANILLA, "n") }, Void.TYPE);
    MethodReference sendChatMessage = new MethodReference(new ObfuscationName[] { from(MCP, "sendChatMessage"), from(VANILLA, "g") }, Void.TYPE, String.class);
    MethodReference printChatMessageWithOptionalDeletion = new MethodReference(new ObfuscationName[] { from(MCP, "printChatMessageWithOptionalDeletion"), from(VANILLA, "a") }, Void.TYPE, ITextComponent, Integer.TYPE);
    MethodReference getFOVModifier = new MethodReference(new ObfuscationName[] { from(MCP, "getFOVModifier"), from(VANILLA, "a")}, Float.TYPE, Float.TYPE, Boolean.TYPE);
}