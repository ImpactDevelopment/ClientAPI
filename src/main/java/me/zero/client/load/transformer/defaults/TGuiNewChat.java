package me.zero.client.load.transformer.defaults;

import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;

import java.util.List;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Hooks the receive stage
 *
 * @since 1.0
 *
 * Created by Brady on 2/10/2017.
 */
@LoadTransformer
public final class TGuiNewChat extends Transformer {

    @Override
    public void loadHooks(List<ClassHook> hooks) {
        hooks.add(printChatMessageWithOptionalDeletion.createHook(method -> method.insertBefore("EventManager.post(new ChatEvent($1, ChatEvent.Type.RECEIVE));")));
    }

    @Override
    public ClassReference[] getTargetClasses() {
        return new ClassReference[] { GuiNewChat };
    }
}
