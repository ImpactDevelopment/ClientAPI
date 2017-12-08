package clientapi.event.defaults.game.entity;

import me.zero.alpine.type.Cancellable;
import me.zero.alpine.type.EventState;
import net.minecraft.entity.EntityLivingBase;

/**
 * Called before and after {@code EntityLivingBase#jump()} is
 * invoked. Should only be cancelled in the {@code PRE} state
 * to prevent the entity from jumping.
 *
 * @see EntityLivingBase#jump()
 *
 * @author Brady
 * @since 12/8/2017 2:35 PM
 */
public final class EntityJumpEvent extends Cancellable {

    /**
     * The state of the event
     */
    private final EventState state;

    /**
     * The {@code EntityLivingBase} that is jumping
     */
    private final EntityLivingBase entity;

    public EntityJumpEvent(EventState state, EntityLivingBase entity) {
        this.state = state;
        this.entity = entity;
    }

    /**
     * @return The state of the event
     */
    public final EventState getState() {
        return this.state;
    }

    /**
     * @return The {@code EntityLivingBase} that is jumping
     */
    public final EntityLivingBase getEntity() {
        return this.entity;
    }
}
