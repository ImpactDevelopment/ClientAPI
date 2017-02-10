package me.zero.client.load.inject.transformer.defaults;

import me.zero.client.load.inject.transformer.Transformer;
import me.zero.client.load.inject.transformer.hook.ClassHook;
import me.zero.client.load.inject.transformer.reference.ClassReference;

import java.util.List;

import static me.zero.client.load.inject.transformer.reference.obfuscation.MCMappings.*;

/**
 * Hooks the receive stage
 *
 * @since 1.0
 *
 * Created by Brady on 2/10/2017.
 */
public class TGuiNewChat extends Transformer {

    @Override
    public void loadHooks(List<ClassHook> hooks) {
        hooks.add(printChatMessageWithOptionalDeletion.createHook(method -> method.insertBefore("EventManager.post(new ChatEvent($1, ChatEvent.TYPE.RECEIVE));")));
    }

    @Override
    public void loadImports(List<String> imports) {}

    @Override
    public ClassReference[] getTargetClasses() {
        return new ClassReference[] { GuiNewChat };
    }
}
