package clientapi.command.executor.argument;

import clientapi.command.executor.ExecutionContext;
import net.minecraft.block.Block;

import java.lang.reflect.Type;

/**
 * @author Brady
 * @since 11/3/2017 5:33 PM
 */
public final class BlockParser implements ArgumentParser<Block> {

    @Override
    public final Block parse(ExecutionContext context, Type type, String raw) {
        return Block.getBlockFromName(raw);
    }

    @Override
    public final boolean isTarget(Type type) {
        return type instanceof Class && Block.class.isAssignableFrom((Class) type);
    }
}
