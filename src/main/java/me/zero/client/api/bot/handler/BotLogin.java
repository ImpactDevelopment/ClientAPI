package me.zero.client.api.bot.handler;

import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import me.zero.client.api.bot.Bot;
import me.zero.client.api.util.interfaces.Helper;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.login.INetHandlerLoginClient;
import net.minecraft.network.login.client.CPacketEncryptionResponse;
import net.minecraft.network.login.server.SPacketDisconnect;
import net.minecraft.network.login.server.SPacketEnableCompression;
import net.minecraft.network.login.server.SPacketEncryptionRequest;
import net.minecraft.network.login.server.SPacketLoginSuccess;
import net.minecraft.util.CryptManager;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.net.Proxy;
import java.security.PublicKey;
import java.util.UUID;

/**
 * Handles the Network Login state for Bots
 *
 * @since 1.0
 *
 * Created by Brady on 3/6/2017.
 */
public class BotLogin implements INetHandlerLoginClient, Helper {

    /**
     * Bot Minecraft Session Service, initialized in the static constructor
     */
    private static final MinecraftSessionService sessionService;

    /**
     * NetworkManager used by the Bot
     */
    private final NetworkManager networkManager;

    /**
     * Bot that represents this Play Network Handler
     */
    private final Bot bot;

    static {
        sessionService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString())
                .createMinecraftSessionService();
    }

    public BotLogin(Bot bot) {
        this.bot = bot;
        this.networkManager = bot.getNetworkManager();
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleEncryptionRequest(SPacketEncryptionRequest packetIn) {
        String serverid = packetIn.getServerId();
        PublicKey publickey = packetIn.getPublicKey();
        SecretKey secretkey = CryptManager.createNewSharedKey();

        String encryptedid = new BigInteger(CryptManager.getServerIdHash(serverid, publickey, secretkey)).toString(16);

        boolean success = false;

        try {
            sessionService.joinServer(bot.getSession().getProfile(), bot.getSession().getToken(), encryptedid);
            success = true;
        } catch (AuthenticationUnavailableException e) {
            this.networkManager.closeChannel(new TextComponentTranslation("disconnect.loginFailedInfo", new TextComponentTranslation("disconnect.loginFailedInfo.serversUnavailable")));
        } catch (InvalidCredentialsException e) {
            this.networkManager.closeChannel(new TextComponentTranslation("disconnect.loginFailedInfo", new TextComponentTranslation("disconnect.loginFailedInfo.invalidSession")));
        } catch (AuthenticationException e) {
            this.networkManager.closeChannel(new TextComponentTranslation("disconnect.loginFailedInfo", e.getMessage()));
        }

        if (!success)
            return;

        // noinspection unchecked
        this.networkManager.sendPacket(new CPacketEncryptionResponse(secretkey, publickey, packetIn.getVerifyToken()),
                future -> BotLogin.this.networkManager.enableEncryption(secretkey));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleLoginSuccess(SPacketLoginSuccess packetIn) {
        this.networkManager.setConnectionState(EnumConnectionState.PLAY);
        this.networkManager.setNetHandler(new BotPlay(this.bot));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleDisconnect(SPacketDisconnect packetIn) {
        this.networkManager.closeChannel(packetIn.getReason());
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleEnableCompression(SPacketEnableCompression packetIn) {
        if (!this.networkManager.isLocalChannel())
            this.networkManager.setCompressionThreshold(packetIn.getCompressionThreshold());
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onDisconnect(ITextComponent reason) {}
}
