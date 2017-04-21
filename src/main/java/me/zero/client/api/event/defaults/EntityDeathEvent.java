package me.zero.client.api.event.defaults;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

/**
 * Called from EntityLivingBase#onDeath
 *
 * @since 1.0
 *
 * Created by Brady on 2/28/2017.
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
     * @since 1.0
     *
     * @return The entity that died
     */
    public final EntityLivingBase getEntity() {
        return this.entity;
    }

    /**
     * @since 1.0
     *
     * @return The DamageSource that caused the Death
     */
    public final DamageSource getSource() {
        return this.source;
    }
}
