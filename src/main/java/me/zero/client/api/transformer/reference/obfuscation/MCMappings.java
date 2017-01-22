package me.zero.client.api.transformer.reference.obfuscation;

import me.zero.client.api.transformer.reference.ClassReference;
import me.zero.client.api.transformer.reference.MethodReference;

import static me.zero.client.api.transformer.reference.obfuscation.Obfuscation.MCP;
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

    ClassReference Minecraft = new ClassReference(new ObfuscationName[] { from(MCP, "net.minecraft.client.Minecraft") });

    MethodReference runTick = new MethodReference(new ObfuscationName[] { from(MCP, "runTick") }, Void.TYPE);
}
