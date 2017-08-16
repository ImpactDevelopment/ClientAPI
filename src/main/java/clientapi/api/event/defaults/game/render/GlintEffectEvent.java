/*
 * Copyright 2017 ZeroMemes
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

package clientapi.api.event.defaults.game.render;

import me.zero.alpine.type.Cancellable;

/**
 * Called when the enchanted effect of a
 * layer or an item is being rendered.
 *
 * @author Brady
 * @since 2/19/2017 12:00 PM
 */
public final class GlintEffectEvent extends Cancellable {

    /**
     * The object getting a glint effect applied to it
     */
    private final GlintTarget target;

    public GlintEffectEvent(GlintTarget target) {
        this.target = target;
    }

    /**
     * @return The glint object
     */
    public final GlintTarget getTarget() {
        return this.target;
    }

    /**
     * Glint Object
     */
    public enum GlintTarget {
        ARMOR, ITEM
    }
}
