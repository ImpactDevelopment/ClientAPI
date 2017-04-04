package me.zero.client.load.transformer.wrapper.defaults;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import me.zero.client.api.exception.UnexpectedOutcomeException;
import me.zero.client.wrapper.IEntity;
import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.wrapper.ClassWrapper;

import static javassist.CtClass.*;
import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Wraps IEntity to Entity
 *
 * @since 1.0
 *
 * Created by Brady on 2/25/2017.
 */
@LoadTransformer
public final class WEntity extends ClassWrapper {

    private static CtClass Vec3, Vec2;

    static {
        try {
            Vec3 = ClassPool.getDefault().get("me.zero.client.api.util.math.Vec3");
            Vec2 = ClassPool.getDefault().get("me.zero.client.api.util.math.Vec2");
        } catch (NotFoundException e) {
            throw new UnexpectedOutcomeException("Unable to find Vec3 and Vec2 classes");
        }
    }

    public WEntity() {
        super(Entity, IEntity.class);
    }

    @Override
    protected void loadImplementations() {
        String set2 = "{ this.%s = $1.getX(); this.%s = $1.getY(); }";
        String set3 = "{ this.%s = $1.getX(); this.%s = $1.getY(); this.%s = $1.getZ(); }";
        String new2 = "{ return new %s(%s, %s); }";
        String new3 = "{ return new %s(%s, %s, %s); }";

        this.implement("setPos", voidType, new CtClass[] { Vec3 }, String.format(set3, posX.getName(), posY.getName(), posZ.getName()));
        this.implement("setPrevPos", voidType, new CtClass[] { Vec3 }, String.format(set3, prevPosX.getName(), prevPosY.getName(), prevPosZ.getName()));
        this.implement("setLastTickPos", voidType, new CtClass[] { Vec3 }, String.format(set3, lastTickPosX.getName(), lastTickPosY.getName(), lastTickPosZ.getName()));
        this.implement("setRotations", voidType, new CtClass[] { Vec2 }, String.format(set2, rotationYaw.getName(), rotationPitch.getName()));
        this.implement("setPrevRotations", voidType, new CtClass[] { Vec2 }, String.format(set2, prevRotationYaw.getName(), prevRotationPitch.getName()));

        this.implement("getPos", Vec3, String.format(new3, Vec3.getName(), posX.getName(), posY.getName(), posZ.getName()));
        this.implement("getPrevPos", Vec3, String.format(new3, Vec3.getName(), prevPosX.getName(), prevPosY.getName(), prevPosZ.getName()));
        this.implement("getLastTickPos", Vec3, String.format(new3, Vec3.getName(), lastTickPosX.getName(), lastTickPosY.getName(), lastTickPosZ.getName()));
        this.implement("getRotations", Vec2, String.format(new2, Vec2.getName(), rotationYaw.getName(), rotationPitch.getName()));
        this.implement("getPrevRotations", Vec2, String.format(new2, Vec2.getName(), prevRotationYaw.getName(), prevRotationPitch.getName()));

        this.implement("interpolate", Vec3, new CtClass[] { floatType }, "{ return this.getLastTickPos().add(this.getPos().sub(this.getLastTickPos()).scale($1)); }");
    }
}
