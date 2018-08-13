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

package clientapi.command.executor.parser.impl;

import clientapi.command.executor.ExecutionContext;
import clientapi.command.executor.parser.ArgumentParser;
import net.minecraft.item.Item;

import java.lang.reflect.Type;

/**
 * @author Brady
 * @since 4/19/2018
 */
public final class ItemParser implements ArgumentParser<Item> {

    @Override
    public final Item parse(ExecutionContext context, Type type, String raw) {
        return Item.getByNameOrId(raw);
    }

    @Override
    public boolean isTarget(Type type) {
        return type instanceof Class && Item.class.isAssignableFrom((Class) type);
    }
}
