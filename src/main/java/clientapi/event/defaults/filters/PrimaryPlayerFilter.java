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

package clientapi.event.defaults.filters;

import clientapi.util.interfaces.MinecraftAccessible;
import net.minecraft.client.Minecraft;

/**
 * An implementation of {@link LocalPlayerFilter} that accepts the primary player as defined by {@link Minecraft#player}
 *
 * @author Brady
 * @since 11/16/2018
 */
public class PrimaryPlayerFilter extends LocalPlayerFilter implements MinecraftAccessible {

    public PrimaryPlayerFilter() {
        super(() -> mc.player);
    }
}
