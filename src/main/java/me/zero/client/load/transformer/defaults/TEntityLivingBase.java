package me.zero.client.load.transformer.defaults;

import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;

import java.util.Collection;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Used to hook the EntityDeathEvent
 *
 * @since 1.0
 *
 * Created by Brady on 2/28/2017.
 */
@LoadTransformer
public final class TEntityLivingBase extends Transformer {

    @Override
    public void loadHooks(Collection<ClassHook> hooks) {
        hooks.add(onDeath.createHook(method -> method.insertBefore("EventManager.post(new EntityDeathEvent(this, $1));")));
    }

    @Override
    public ClassReference getTargetClass() {
        return EntityLivingBase;
    }
}
