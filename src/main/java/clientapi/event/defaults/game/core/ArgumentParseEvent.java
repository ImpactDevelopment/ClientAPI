package clientapi.event.defaults.game.core;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import me.zero.alpine.type.EventState;
import net.minecraft.client.main.Main;

/**
 * Called before and after {@code OptionParser} parsing
 * occurs in {@code Main#main(String[])}. In the {@code PRE}
 * state, the {@code set} field will be {@code null}.
 *
 * @see Main#main(String[])
 *
 * @author Brady
 * @since 10/19/2017 8:23 PM
 */
public final class ArgumentParseEvent {

    /**
     * State of the event
     */
    private final EventState state;

    /**
     * The OptionParser being used to parse the launch arguments.
     */
    private final OptionParser parser;

    /**
     * The OptionSet generated from the parser. Only non-null in the {@code POST} state.
     */
    private final OptionSet set;

    public ArgumentParseEvent(EventState state, OptionParser parser, OptionSet set) {
        this.state = state;
        this.parser = parser;
        this.set = set;
    }

    /**
     * @return State of the event
     */
    public final EventState getState() {
        return this.state;
    }

    /**
     * @return The OptionParser being used to parse the launch arguments.
     */
    public final OptionParser getParser() {
        return this.parser;
    }

    /**
     * @return The OptionSet generated from the parser.
     */
    public final OptionSet getSet() {
        return this.set;
    }
}
