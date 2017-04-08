package me.zero.client.api.bot;

import me.zero.client.api.bot.entity.BotEntity;
import me.zero.client.api.bot.handler.BotLogin;
import me.zero.client.api.util.Protocol;
import me.zero.client.api.util.factory.AuthenticationFactory;
import me.zero.client.api.util.interfaces.Helper;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.util.Session;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * A basic bot
 *
 * @since 1.0
 *
 * Created by Brady on 3/6/2017.
 */
public final class Bot implements Helper {

    /**
     * NetworkManager used by the Bot
     */
    private NetworkManager networkManager;

    /**
     * Login session
     */
    private final Session session;

    /**
     * Player representing the Bot
     */
    private BotEntity player;

    /**
     * PlayerController that handles various bot actions
     */
    private PlayerControllerMP playerController;

    public Bot(AuthenticationFactory auth) {
        this(auth.session());
    }

    public Bot(Session session) {
        this.session = session;
    }

    public final void login(String hostname, int port, Protocol protocol) throws UnknownHostException {
        InetAddress inet = InetAddress.getByName(hostname);
        this.networkManager = NetworkManager.createNetworkManagerAndConnect(inet, port, mc.gameSettings.useNativeTransport);
        this.networkManager.setNetHandler(new BotLogin(this));
        this.networkManager.sendPacket(new C00Handshake(protocol.getProtocol(), hostname, port, EnumConnectionState.LOGIN));
    }

    /**
     * @since 1.0
     *
     * @return The Bot Entity
     */
    public final BotEntity getPlayer() {
        return this.player;
    }

    /**
     * @since 1.0
     *
     * @return The PlayerController for the Bot
     */
    public final PlayerControllerMP getPlayerController() {
        return this.playerController;
    }

    /**
     * @since 1.0
     *
     * @return NetworkManager that handles networking actions for the bot
     */
    public final NetworkManager getNetworkManager() {
        return this.networkManager;
    }

    /**
     * @since 1.0
     *
     * @return The Bot's Login Session
     */
    public final Session getSession() {
        return this.session;
    }
}
