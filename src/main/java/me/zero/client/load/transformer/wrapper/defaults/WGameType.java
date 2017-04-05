package me.zero.client.load.transformer.wrapper.defaults;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import me.zero.client.api.exception.UnexpectedOutcomeException;
import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.wrapper.ClassWrapper;
import me.zero.client.wrapper.IGameType;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Wraps IGameType to GameType
 *
 * @since 1.0
 *
 * Created by Brady on 4/4/2017.
 */
@LoadTransformer
public class WGameType extends ClassWrapper {

    private static CtClass String;

    static {
        try {
            String = ClassPool.getDefault().get("java.lang.String");
        } catch (NotFoundException e) {
            throw new UnexpectedOutcomeException("Unable to find String class");
        }
    }

    public WGameType() {
        super(GameType, IGameType.class);
    }

    @Override
    protected void loadImplementations() {
        this.implementR("getShortName", String, shortName);
    }
}

