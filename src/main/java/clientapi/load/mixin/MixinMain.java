package clientapi.load.mixin;

import clientapi.ClientAPI;
import clientapi.event.defaults.game.core.ArgumentParseEvent;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import me.zero.alpine.type.EventState;
import net.minecraft.client.main.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Brady
 * @since 10/19/2017 3:22 PM
 */
@Mixin(Main.class)
public class MixinMain {

    @Redirect(method = "main", at = @At(value = "INVOKE_ASSIGN", target = "joptsimple/OptionParser.parse([Ljava/lang/String;)Ljoptsimple/OptionSet;"))
    private static OptionSet parse(OptionParser parser, String... arguments) {
        ClientAPI.EVENT_BUS.post(new ArgumentParseEvent(EventState.PRE, parser, null));
        OptionSet set = parser.parse(arguments);
        ClientAPI.EVENT_BUS.post(new ArgumentParseEvent(EventState.POST, parser, set));
        return set;
    }
}
