package me.zero.client.api.module;

import me.zero.client.api.exception.UnexpectedOutcomeException;
import me.zero.client.load.ClientAPI;

import static me.zero.client.load.ClientAPI.Stage.INIT;

/**
 * The base for all cheats
 *
 * @see me.zero.client.api.module.ModuleType
 *
 * @since 1.0
 *
 * Created by Brady on 1/19/2017.
 */
public abstract class Module implements IModule {

    private String name, description;
    private ModuleType type;
    private boolean state;

    public Module() {
        ClientAPI.getAPI().check(INIT, "Module creation after Initialization");

        if (this.getClass().isAnnotationPresent(Mod.class)) {
            Mod data = this.getClass().getAnnotation(Mod.class);

            this.name = data.name();
            this.description = data.description();
            this.type = ModuleType.get(data.type());

            this.setBind(data.bind());
        } else {
            throw new UnexpectedOutcomeException("Modules must have a Mod annotation!");
        }
        if (name == null || description == null || type == null)
            throw new UnexpectedOutcomeException("One or more Mod members were null!");
    }

    @Override
    public final void toggle() {
        this.setState(!this.getState());
    }

    @Override
    public final void setState(boolean state) {
        if (state == this.state) return;

        if (this.state = state) {
            onEnable();
        } else {
            onDisable();
        }
    }

    @Override
    public final boolean getState() {
        return this.state;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }

    @Override
    public final ModuleType getType() {
        return this.type;
    }
}
