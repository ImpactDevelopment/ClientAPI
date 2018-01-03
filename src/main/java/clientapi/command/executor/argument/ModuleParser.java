package clientapi.command.executor.argument;

import clientapi.command.executor.ExecutionContext;
import clientapi.manage.Manager;
import clientapi.module.Module;

import java.lang.reflect.Type;

/**
 * @author Brady
 * @since 11/3/2017 2:22 PM
 */
public final class ModuleParser implements ArgumentParser<Module> {

    private final Manager<Module> moduleManager;

    public ModuleParser(Manager<Module> moduleManager) {
        this.moduleManager = moduleManager;
    }

    @Override
    public final Module parse(ExecutionContext context, Type type, String raw) {
        return moduleManager.stream().filter(mod -> format(mod.getName()).equals(format(raw))).findFirst().orElse(null);
    }

    @Override
    public final boolean isTarget(Type type) {
        return type instanceof Class && Module.class.isAssignableFrom((Class) type);
    }

    /**
     * Removes any characters that module names may contain
     * which shouldn't be required to target the module.
     *
     * @param input The module name
     * @return Formatted module name
     */
    private String format(String input) {
        return input.replace(" ", "").replace("_", "").toLowerCase();
    }
}
