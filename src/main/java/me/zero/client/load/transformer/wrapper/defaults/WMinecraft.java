package me.zero.client.load.transformer.wrapper.defaults;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import me.zero.client.api.exception.UnexpectedOutcomeException;
import me.zero.client.api.wrapper.IMinecraft;
import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.wrapper.ClassWrapper;

import static javassist.CtClass.voidType;
import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Wraps IMinecraft to Minecraft
 *
 * @since 1.0
 *
 * Created by Brady on 2/23/2017.
 */
@LoadTransformer
public class WMinecraft extends ClassWrapper {

    private static CtClass MouseButton;

    static {
        try {
            MouseButton = ClassPool.getDefault().get("me.zero.client.api.event.defaults.ClickEvent$MouseButton");
        } catch (NotFoundException e) {
            throw new UnexpectedOutcomeException("Unable to find MouseButton classes");
        }
    }

    public WMinecraft() {
        super(Minecraft, IMinecraft.class);
    }

    @Override
    protected void loadImplementations() {
        this.implementR("getTimer", Timer.getCtClass(), timer);
        this.implementS("setSession", Session.getCtClass(), session);

        String code = "{ if ($1 == %s) { this.%s(); } else if ($1 == %s) { this.%s(); } else if ($1 == %s) { this.%s(); } }";
        String format = String.format(code,
                MouseButton.getName() + ".LEFT", clickMouse.getName(),
                MouseButton.getName() + ".RIGHT", rightClickMouse.getName(),
                MouseButton.getName() + ".MIDDLE", middleClickMouse.getName()
                );
        this.implement("clickMouse", voidType, new CtClass[] { MouseButton }, format);
    }
}
