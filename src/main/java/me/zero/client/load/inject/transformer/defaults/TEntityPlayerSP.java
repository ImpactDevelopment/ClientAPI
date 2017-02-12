package me.zero.client.load.inject.transformer.defaults;

import me.zero.client.load.inject.transformer.LoadTransformer;
import me.zero.client.load.inject.transformer.Transformer;
import me.zero.client.load.inject.transformer.hook.ClassHook;
import me.zero.client.load.inject.transformer.reference.ClassReference;

import java.util.List;

import static me.zero.client.load.inject.transformer.reference.obfuscation.MCMappings.*;

/**
 * Used to hook the UpdateEvent, LivingUpdateEvent,
 * and the Send stage for ChatEvent
 *
 * @since 1.0
 *
 * Created by Brady on 2/8/2017.
 */
@LoadTransformer
public final class TEntityPlayerSP extends Transformer {

    @Override
    public void loadHooks(List<ClassHook> hooks) {
        hooks.add(onUpdate.createHook(method -> method.insertBefore("EventManager.post(new UpdateEvent());")));
        hooks.add(onLivingUpdate.createHook(method -> {
            method.insertBefore("EventManager.post(new LivingUpdateEvent(EventState.PRE));");
            method.insertAfter("EventManager.post(new LivingUpdateEvent(EventState.POST));");
        }));
        hooks.add(sendChatMessage.createHook(method -> method.insertBefore("{ ChatEvent event = new ChatEvent($1, ChatEvent.Type.SEND); EventManager.post(event); if (event.isCancelled()) return; $1 = event.getMessage(); }")));
        hooks.add(moveEntity.createHook(method -> method.insertBefore("{ MoveEvent event = new MoveEvent($1, $2, $3, $4); EventManager.post(event); $2 = event.getX(); $3 = event.getY(); $4 = event.getZ(); }")));
    }

    @Override
    public ClassReference[] getTargetClasses() {
        return new ClassReference[] { EntityPlayerSP };
    }
}
