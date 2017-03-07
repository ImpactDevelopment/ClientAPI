package me.zero.client.api.bot;

import me.zero.client.api.bot.handler.BotLogin;
import me.zero.client.api.util.Callback;
import me.zero.client.api.util.factory.AuthenticationFactory;
import me.zero.client.api.util.interfaces.Helper;
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
public class Bot implements Helper {

    private NetworkManager networkManager;
    private final Session session;

    public Bot(AuthenticationFactory auth) {
        this(auth.session());
    }

    public Bot(Session session) {
        this.session = session;
    }

    public final void login(String hostname, int port, int protocol) throws UnknownHostException {
        // GuiConnecting used as a reference

        InetAddress inet = InetAddress.getByName(hostname);
        this.networkManager = NetworkManager.createNetworkManagerAndConnect(inet, port, mc.gameSettings.useNativeTransport);
        this.networkManager.setNetHandler(new BotLogin(this));
        this.networkManager.sendPacket(new C00Handshake(protocol, hostname, port, EnumConnectionState.LOGIN));
    }

    public final NetworkManager getNetworkManager() {
        return this.networkManager;
    }

    public final Session getSession() {
        return this.session;
    }
}
