package me.zero.client.load.transformer.reference.obfuscation;

import io.netty.channel.ChannelHandlerContext;
import me.zero.client.load.transformer.reference.ClassReference;
import me.zero.client.load.transformer.reference.FieldReference;
import me.zero.client.load.transformer.reference.MethodReference;

import static me.zero.client.load.transformer.reference.obfuscation.Obfuscation.MCP;
import static me.zero.client.load.transformer.reference.obfuscation.Obfuscation.VANILLA;
import static me.zero.client.load.transformer.reference.obfuscation.ObfuscationName.from;

/**
 * Contains class and method references for the game
 * that are required by the API's default transformers.
 *
 * @see me.zero.client.load.transformer.ITransformer
 *
 * @since 1.0
 *
 * Created by Brady on 1/22/2017.
 */
public interface MCMappings {
    // Classes
    ClassReference Minecraft = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.Minecraft"), from(VANILLA, "bes") });
    ClassReference Session = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.util.Session"), from(VANILLA, "bez") });
    ClassReference GuiScreen = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.gui.GuiScreen"), from(VANILLA, "bho") });
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
    ClassReference ITextComponent = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.util.text.ITextComponent"), from(VANILLA, "fb") });
    ClassReference EntityRenderer = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.renderer.EntityRenderer"), from(VANILLA, "bqe") });
    ClassReference GuiPlayerTabOverlay = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.gui.GuiPlayerTabOverlay"), from(VANILLA, "bgc") });
    ClassReference LayerArmorBase = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.renderer.entity.layers.LayerArmorBase"), from(VANILLA, "bwz") });
    ClassReference RenderLivingBase = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.renderer.entity.RenderLivingBase"), from(VANILLA, "bvl") });
    ClassReference EntityLivingBase = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.entity.EntityLivingBase"), from(VANILLA, "sw") });
    ClassReference ModelBase = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.model.ModelBase"), from(VANILLA, "blv") });
    ClassReference IBakedModel = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.renderer.block.model.IBakedModel"), from(VANILLA, "cbh") });
    ClassReference RenderItem = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.renderer.RenderItem"), from(VANILLA, "bwz") });
    ClassReference Timer = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.util.Timer"), from(VANILLA, "bey") });
    ClassReference RenderManager = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.renderer.entity"), from(VANILLA, "bur") });

    // Methods
    MethodReference runTick = new MethodReference(new ObfuscationName[] { from(MCP, "runTick"), from(VANILLA, "t") }, Void.TYPE);
    MethodReference runGameLoop = new MethodReference(new ObfuscationName[] { from(MCP, "runGameLoop"), from(VANILLA, "av") }, Void.TYPE);
    MethodReference init = new MethodReference(new ObfuscationName[] { from(MCP, "init"), from(VANILLA, "an") }, Void.TYPE);
    MethodReference displayGuiScreen = new MethodReference(new ObfuscationName[] { from(MCP, "displayGuiScreen"), from(VANILLA, "a") }, Void.TYPE, GuiScreen);
    MethodReference renderGameOverlay = new MethodReference(new ObfuscationName[] { from(MCP, "renderGameOverlay"), from(VANILLA, "a") }, Void.TYPE, Float.TYPE);
    MethodReference channelRead0 = new MethodReference(new ObfuscationName[] { from(MCP, "channelRead0"), from(VANILLA, "a" ) }, Void.TYPE, ChannelHandlerContext.class, Packet);
    MethodReference sendPacket1 = new MethodReference(new ObfuscationName[] { from(MCP, "sendPacket" ), from(VANILLA, "a") }, Void.TYPE, Packet);
    MethodReference onUpdate = new MethodReference(new ObfuscationName[] { from(MCP, "onUpdate"), from(VANILLA, "A_") }, Void.TYPE);
    MethodReference move = new MethodReference(new ObfuscationName[] { from(MCP, "move"), from(VANILLA, "a") }, Void.TYPE, MoverType, Double.TYPE, Double.TYPE, Double.TYPE);
    MethodReference clickMouse = new MethodReference(new ObfuscationName[] { from(MCP, "clickMouse"), from(VANILLA, "aw") }, Void.TYPE);
    MethodReference rightClickMouse = new MethodReference(new ObfuscationName[] { from(MCP, "rightClickMouse"), from(VANILLA, "ax") }, Void.TYPE);
    MethodReference middleClickMouse = new MethodReference(new ObfuscationName[] { from(MCP, "middleClickMouse"), from(VANILLA, "aC") }, Void.TYPE);
    MethodReference loadWorld = new MethodReference(new ObfuscationName[] { from(MCP, "loadWorld"), from(VANILLA, "a") }, Void.TYPE, WorldClient, String.class);
    MethodReference startSection = new MethodReference(new ObfuscationName[] { from(MCP, "startSection"), from(VANILLA, "a") }, Void.TYPE, String.class);
    MethodReference onLivingUpdate = new MethodReference(new ObfuscationName[] { from(MCP, "onLivingUpdate"), from(VANILLA, "n") }, Void.TYPE);
    MethodReference sendChatMessage = new MethodReference(new ObfuscationName[] { from(MCP, "sendChatMessage"), from(VANILLA, "g") }, Void.TYPE, String.class);
    MethodReference printChatMessageWithOptionalDeletion = new MethodReference(new ObfuscationName[] { from(MCP, "printChatMessageWithOptionalDeletion"), from(VANILLA, "a") }, Void.TYPE, ITextComponent, Integer.TYPE);
    MethodReference getFOVModifier = new MethodReference(new ObfuscationName[] { from(MCP, "getFOVModifier"), from(VANILLA, "a") }, Float.TYPE, Float.TYPE, Boolean.TYPE);
    MethodReference onUpdateWalkingPlayer = new MethodReference(new ObfuscationName[] { from(MCP, "onUpdateWalkingPlayer"), from(VANILLA, "x") }, Void.TYPE);
    MethodReference updatePlayerList = new MethodReference(new ObfuscationName[] { from(MCP, "updatePlayerList"), from(VANILLA, "a") }, Void.TYPE, Boolean.TYPE);
    MethodReference renderEnchantedGlint = new MethodReference(new ObfuscationName[] { from(MCP, "renderEnchantedGlint"), from(VANILLA, "") }, Void.TYPE, RenderLivingBase, EntityLivingBase, ModelBase, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE);
    MethodReference renderEffect = new MethodReference(new ObfuscationName[] { from(MCP, "renderEffect"), from(VANILLA, "a") }, Void.TYPE, IBakedModel);

    // Fields
    FieldReference session = new FieldReference(new ObfuscationName[] { from(MCP, "session"), from(VANILLA, "ae") });
    FieldReference timer = new FieldReference(new ObfuscationName[] { from(MCP, "timer"), from(VANILLA, "Y") });
    FieldReference renderPosX = new FieldReference(new ObfuscationName[] { from(MCP, "renderPosX"), from(VANILLA, "o") });
    FieldReference renderPosY = new FieldReference(new ObfuscationName[] { from(MCP, "renderPosY"), from(VANILLA, "p") });
    FieldReference renderPosZ = new FieldReference(new ObfuscationName[] { from(MCP, "renderPosZ"), from(VANILLA, "q") });
}