/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.api.event.defaults.game;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

/**
 * Called from EntityLivingBase#onDeath
 *
 * @author Brady
 * @since 2/28/2017 12:00 PM
 */
public final class EntityDeathEvent {

    /**
     * The Entity that died
     */
    private final EntityLivingBase entity;

    /**
     * Type of Damage that caused the Death
     */
    private final DamageSource source;

    public EntityDeathEvent(EntityLivingBase entity, DamageSource source) {
        this.entity = entity;
        this.source = source;
    }

    /**
     * @return The entity that died
     */
    public final EntityLivingBase getEntity() {
        return this.entity;
    }

    /**
     * @return The DamageSource that caused the Death
     */
    public final DamageSource getSource() {
        return this.source;
    }
}
