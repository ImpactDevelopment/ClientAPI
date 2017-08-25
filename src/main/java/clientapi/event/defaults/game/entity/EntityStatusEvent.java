package clientapi.event.defaults.game.entity;

import me.zero.alpine.type.Cancellable;
import net.minecraft.entity.Entity;

/**
 * Called when {@code NetHandlerPlayClient#handleEntityStatus(SPacketEntityStatus)}
 * invokes {@code Entity#handleStatusUpdate(int)}. The event can be cancelled to
 * prevent the entity status update from occuring.
 *
 * @author Brady
 * @since 8/24/2017 9:31 PM
 */
public final class EntityStatusEvent extends Cancellable {

    /**
     * Entity receiving a status update
     */
    private final Entity entity;

    /**
     * The entity status update opcode
     */
    private int opcode;

    public EntityStatusEvent(Entity entity, int opcode) {
        this.entity = entity;
        this.opcode = opcode;
    }

    /**
     * @return The entity receiving a status update
     */
    public final Entity getEntity() {
        return this.entity;
    }

    /**
     * @return The entity status update opcode
     */
    public final int getOpcode() {
        return this.opcode;
    }

    public interface StatusOpcodes {

        // None
        int GUARDIAN_SOUND = 21;
        int TOTEM_USE = 35;

        // AbstractHorse
        int SPAWN_HORSE_PARTICLES_SUCCESS = 7;
        int SPAWN_HORSE_PARTICLES_FAILURE = 6;

        // EntityAnimal
        int ANIMAL_MATE = 18;

        // EntityArmorStand
        int ARMOR_STAND_ATTACK = 32;

        // EntityEvokerFangs
        int EVOKER_FANGS_ATTACK = 4;

        // EntityFireworkRocket
        int FIREWORK_LAUNCH = 17;

        // EntityFishHook
        int FISH_HOOK_BRING_IN = 31;

        // EntityIronGolen
        int GOLEM_ATTACk = 4;
        int GOLEM_GRAB_ROSE = 11;
        int GOLEM_RELEASE_ROSE = 34;

        // EntityLiving
        int SPAWM_EXPLOSION = 20;

        // EntityLivingBase
        int ENTITY_GENERIC_DAMAGE = 2;
        int ENTITY_DEATH = 3;
        int ENTITY_SHIELD_BREAK = 30;
        int ENTITY_SHIELD_BLOCK = 29;
        int ENTITY_THORNS_HIT = 33;
        int ENTITY_DAMAGE_DROWN = 36;
        int ENTITY_DAMAGE_FIRE = 37;

        // EntityMinecartTNT
        int MINECART_TNT_IGNITE = 10;

        // EntityPlayer
        int PLAYER_FINISH_USE_ITEM = 9;
        int PLAYER_REDUCED_DEBUG_FALSE = 23;
        int PLAYER_REDUCED_DEBUG_TRUE = 22;

        // EntityPlayerSP
        int PLAYER_PERM0 = 24;
        int PLAYER_PERM1 = 25;
        int PLAYER_PERM2 = 26;
        int PLAYER_PERM3 = 27;
        int PLAYER_PERM4 = 28;

        // EntityRabbit
        int RABBIT_RUNNING = 1;

        // EntitySheep
        int SHEEP_RESET_TIMER = 40;

        // EntitySquid
        int SQUID_RESET_ROTATION = 19;

        // EntityTameable
        int TAMEABLE_TAME_SUCCEED = 7;
        int TAMEABLE_TAME_FAILURE = 6;

        // EntityTippedArrow
        int ARROW_DESPAWN = 0;

        // EntityVillager
        int VILLAGER_BREED = 12;
        int VILLAGER_ANGRY = 13;
        int VILLAGER_HAPPY = 14;

        // EntityWitch
        int WITCH_SPELL = 15;

        // EntityWolf
        int WOLF_SHAKE = 8;

        // EntityZombieVillager
        int ZOMBIE_VILLAGER_CURE = 16;
    }
}
