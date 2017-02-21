package me.zero.client.load.transformer.defaults;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewMethod;
import javassist.CtPrimitiveType;
import me.zero.client.api.wrapper.IMinecraft;
import me.zero.client.api.wrapper.IRenderManager;
import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;

import java.util.Collection;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.RenderManager;

/**
 * Used to add the RenderManager Wrapper
 *
 * @since 1.0
 *
 * Created by Brady on 2/20/2017.
 */
@LoadTransformer
public class TRenderManager extends Transformer {

    @Override
    public void loadHooks(Collection<ClassHook> hooks) {
        hooks.add(ctClass -> {
            ctClass.addInterface(ClassPool.getDefault().get(IRenderManager.class.getName()));
            ctClass.addMethod(CtNewMethod.make(CtPrimitiveType.doubleType, "getRenderPosX", new CtClass[0], new CtClass[0], "return this.renderPosX;", ctClass));
            ctClass.addMethod(CtNewMethod.make(CtPrimitiveType.doubleType, "getRenderPosY", new CtClass[0], new CtClass[0], "return this.renderPosY;", ctClass));
            ctClass.addMethod(CtNewMethod.make(CtPrimitiveType.doubleType, "getRenderPosZ", new CtClass[0], new CtClass[0], "return this.renderPosZ;", ctClass));
        });
    }

    @Override
    public ClassReference getTargetClass() {
        return RenderManager;
    }
}
