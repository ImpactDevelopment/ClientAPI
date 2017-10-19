package clientapi.command.sender;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Implementation of {@code CommandSender}, where the sender
 * is defined by an {@code EntityPlayer}
 *
 * @author Brady
 * @since 10/17/2017 9:17 PM
 */
public interface PlayerSender extends CommandSender {

    /**
     * @return {@code EntityPlayer} representing this sender.
     */
    EntityPlayer getPlayer();

    /**
     * Implementation of {@code PlayerSender}
     */
    class Impl implements PlayerSender {

        private final EntityPlayer player;

        Impl(EntityPlayer player) {
            this.player = player;
        }

        @Override
        public final EntityPlayer getPlayer() {
            return this.player;
        }

        @Override
        public final String getName() {
            return this.player.getName();
        }
    }
}
