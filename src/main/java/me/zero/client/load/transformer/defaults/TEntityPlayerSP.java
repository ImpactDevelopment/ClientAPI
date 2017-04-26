package me.zero.client.load.transformer.defaults;

import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;

import java.util.Collection;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

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
    public void loadHooks(Collection<ClassHook> hooks) {
        hooks.add(onUpdate.createHook(method -> method.insertBefore("EventManager.post(new UpdateEvent());")));
        hooks.add(onLivingUpdate.createHook(method -> {
            method.insertBefore("EventManager.post(new LivingUpdateEvent(EventState.PRE));");
            method.insertAfter("EventManager.post(new LivingUpdateEvent(EventState.POST));");
        }));
        hooks.add(sendChatMessage.createHook(method -> method.insertBefore("{ ChatEvent event = new ChatEvent($1, ChatEvent.Type.SEND); EventManager.post(event); if (event.isCancelled()) return; $1 = event.getMessage(); }")));
        hooks.add(move.createHook(method -> method.insertBefore("{ MoveEvent event = new MoveEvent($1, $2, $3, $4); EventManager.post(event); if (event.isCancelled()) return; $2 = event.getX(); $3 = event.getY(); $4 = event.getZ(); }")));
        hooks.add(onUpdateWalkingPlayer.createHook(method -> {
            method.insertBefore("EventManager.post(new MotionUpdateEvent(EventState.PRE)); MotionUpdateEvent.apply();");
            method.insertAfter("MotionUpdateEvent.reset(); EventManager.post(new MotionUpdateEvent(EventState.POST));");
        }));
    }

    @Override
    public ClassReference getTargetClass() {
        return EntityPlayerSP;
    }
}
