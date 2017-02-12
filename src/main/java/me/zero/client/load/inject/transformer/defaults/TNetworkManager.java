package me.zero.client.load.inject.transformer.defaults;

import me.zero.client.load.inject.transformer.Transformer;
import me.zero.client.load.inject.transformer.hook.ClassHook;
import me.zero.client.load.inject.transformer.reference.ClassReference;

import java.util.List;

import static me.zero.client.load.inject.transformer.reference.obfuscation.MCMappings.*;

/**
 * Used for packet send and receive events
 *
 * @since 1.0
 *
 * Created by Brady on 2/7/2017.
 */
public final class TNetworkManager extends Transformer {

    @Override
    public void loadHooks(List<ClassHook> hooks) {
        String code = "{ PacketEvent event = new PacketEvent(%s, PacketEvent.Type.%s); EventManager.post(event); if (event.isCancelled()) return; %s = event.getPacket(); }";

        hooks.add(channelRead0.createHook(method -> method.insertBefore(String.format(code, "$2", "RECEIVE", "$2"))));
        hooks.add(sendPacket1.createHook(method -> method.insertBefore(String.format(code, "$1", "SEND", "$1"))));
    }

    @Override
    public ClassReference[] getTargetClasses() {
        return new ClassReference[]  { NetworkManager };
    }
}
