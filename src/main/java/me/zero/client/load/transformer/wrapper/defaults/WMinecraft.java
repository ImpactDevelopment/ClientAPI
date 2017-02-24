package me.zero.client.load.transformer.wrapper.defaults;

import javassist.CtClass;
import javassist.CtPrimitiveType;
import me.zero.client.api.wrapper.IMinecraft;
import me.zero.client.load.transformer.wrapper.ClassWrapper;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Wrapper for IMinecraft
 *
 * @since 1.0
 *
 * Created by Brady on 2/23/2017.
 */
public class WMinecraft extends ClassWrapper {

    public WMinecraft() {
        super(Minecraft, IMinecraft.class);
    }

    @Override
    protected void loadImplementations() {
        this.implement("getTimer", Timer.getCtClass(), timer);
        this.implement("setTimer", CtPrimitiveType.voidType, new CtClass[] { Session.getCtClass() }, session.createReturn());
    }
}
