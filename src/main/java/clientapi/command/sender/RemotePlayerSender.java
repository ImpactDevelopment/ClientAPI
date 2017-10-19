package clientapi.command.sender;

import net.minecraft.client.network.NetworkPlayerInfo;

/**
 * @author Brady
 * @since 10/17/2017 9:29 PM
 */
public interface RemotePlayerSender extends CommandSender {

    /**
     * @return {@code NetworkPlayerInfo} representing this sender.
     */
    NetworkPlayerInfo getPlayer();

    /**
     * Implementation of {@code PlayerSender}
     */
    class Impl implements RemotePlayerSender {

        private final NetworkPlayerInfo player;

        Impl(NetworkPlayerInfo player) {
            this.player = player;
        }

        @Override
        public final NetworkPlayerInfo getPlayer() {
            return this.player;
        }

        @Override
        public final String getName() {
            return this.player.getGameProfile().getName();
        }
    }
}
