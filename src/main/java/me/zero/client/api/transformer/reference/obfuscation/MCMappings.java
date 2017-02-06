package me.zero.client.api.transformer.reference.obfuscation;

import me.zero.client.api.transformer.reference.ClassReference;
import me.zero.client.api.transformer.reference.MethodReference;

import static me.zero.client.api.transformer.reference.obfuscation.Obfuscation.MCP;
import static me.zero.client.api.transformer.reference.obfuscation.Obfuscation.VANILLA;
import static me.zero.client.api.transformer.reference.obfuscation.ObfuscationName.from;

/**
 * Contains class and method references for the game
 * that are required by the API's default transformers.
 *
 * @see me.zero.client.api.transformer.ITransformer
 *
 * @since 1.0
 *
 * Created by Brady on 1/22/2017.
 */
public interface MCMappings {

    // Classes
    ClassReference Minecraft = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.Minecraft"), from(VANILLA, "bes") });
    ClassReference GuiIngame = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.gui.GuiIngame"), from(VANILLA, "bfh") });

    // Methods
    MethodReference runTick = new MethodReference(new ObfuscationName[] { from(MCP, "runTick"), from(VANILLA, "t") }, Void.TYPE);
    MethodReference runGameLoop = new MethodReference(new ObfuscationName[] { from(MCP, "runGameLoop"), from(VANILLA, "av") }, Void.TYPE);
    MethodReference startGame = new MethodReference(new ObfuscationName[] { from(MCP, "startGame"), from(VANILLA, "an") }, Void.TYPE);
    MethodReference renderGameOverlay = new MethodReference(new ObfuscationName[] { from(MCP, "renderGameOverlay"), from(VANILLA, "a") }, Void.TYPE, Float.TYPE);
}
