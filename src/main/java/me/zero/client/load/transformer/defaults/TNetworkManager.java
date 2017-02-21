package me.zero.client.load.transformer.defaults;

import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;

import java.util.Collection;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Used for packet send and receive events
 *
 * @since 1.0
 *
 * Created by Brady on 2/7/2017.
 */
@LoadTransformer
public final class TNetworkManager extends Transformer {

    @Override
    public void loadHooks(Collection<ClassHook> hooks) {
        String code = "{ PacketEvent event = new PacketEvent(%s, PacketEvent.Type.%s); EventManager.post(event); if (event.isCancelled()) return; %s = event.getPacket(); }";

        hooks.add(channelRead0.createHook(method -> method.insertBefore(String.format(code, "$2", "RECEIVE", "$2"))));
        hooks.add(sendPacket1.createHook(method -> method.insertBefore(String.format(code, "$1", "SEND", "$1"))));
    }

    @Override
    public ClassReference getTargetClass() {
        return NetworkManager;
    }
}
