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

package clientapi.event.defaults.game.render;

import me.zero.alpine.type.Cancellable;

/**
 * Called when various hud overlays are rendered. Can be cancelled,
 * preventing the overlay from being rendered.
 *
 * @see Type
 *
 * @author Brady
 * @since 5/24/2018 10:47 AM
 */
public final class HudOverlayEvent extends Cancellable {

    /**
     * The type of overlay that is being rendered
     */
    private final Type type;

    public HudOverlayEvent(Type type) {
        this.type = type;
    }

    /**
     * @return The type of overlay that is being rendered
     */
    public final Type getType() {
        return this.type;
    }

    public enum Type {
        /**
         * Rendered when the player is in water and their eye height is under the water level
         */
        WATER,

        /**
         * Rendered when the player is in lava and their eye height is under the lava level
         */
        LAVA,

        /**
         * Rendered when the player has a pumpkin on their head
         */
        PUMPKIN,

        /**
         * Transformation to the screen when the player takes damage
         */
        HURTCAM,

        /**
         * The scoreboard that is displayed on the right side of the screen
         */
        SCOREBOARD,

        /**
         * Rendered when the player is on fire
         */
        FIRE,

        /**
         * The entire stat bar
         */
        STAT_ALL,

        /**
         * The stat bar displaying the health level of the player
         */
        STAT_HEALTH,

        /**
         * The stat bar displaying the food level of the player
         */
        STAT_FOOD,

        /**
         * The stat bar displaying the armor level of the player
         */
        STAT_ARMOR,

        /**
         * The stat bar displaying the remaining air that the player has when underwater
         */
        STAT_AIR,

        /**
         * The boss bar rendered at the top of the screen showing the boss health
         */
        BOSS_BAR,

        /**
         * The bar displaying the current player experience level
         */
        EXP_BAR,

        /**
         * The vignette effect overlayed over the game
         */
        VIGNETTE,

        /**
         * The crosshair
         */
        CROSSHAIR,

        /**
         * The attack indicator displaying the remaining time until the player's attack is fully charged
         */
        ATTACK_INDICATOR,

        /**
         * The horse jump bar
         */
        JUMP_BAR,

        /**
         * The health of the currently mounted entity
         */
        MOUNT_HEALTH,

        /**
         * The overlay rendered when the player is inside of a nether portal
         */
        PORTAL,

        /**
         * The tooltip displayed over the hotbar when a new item is selected
         */
        SELECTED_ITEM_TOOLTIP,

        /**
         * The list of potion effects in the top right of the screen
         */
        POTION_EFFECTS
    }
}
