/*
 * Copyright 2018 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
