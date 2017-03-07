package me.zero.client.api.bot.handler;

import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import mcp.MethodsReturnNonnullByDefault;
import me.zero.client.api.bot.Bot;
import me.zero.client.api.util.interfaces.Helper;
import net.minecraft.client.network.NetHandlerLoginClient;
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

    private static final MinecraftSessionService sessionService;

    private final NetworkManager networkManager;
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

        try {
            sessionService.joinServer(bot.getSession().getProfile(), bot.getSession().getToken(), encryptedid);
        } catch (AuthenticationUnavailableException e) {
            this.networkManager.closeChannel(new TextComponentTranslation("disconnect.loginFailedInfo", new TextComponentTranslation("disconnect.loginFailedInfo.serversUnavailable")));
            return;
        } catch (InvalidCredentialsException e) {
            this.networkManager.closeChannel(new TextComponentTranslation("disconnect.loginFailedInfo", new TextComponentTranslation("disconnect.loginFailedInfo.invalidSession")));
            return;
        } catch (AuthenticationException e) {
            this.networkManager.closeChannel(new TextComponentTranslation("disconnect.loginFailedInfo", e.getMessage()));
            return;
        }

        this.networkManager.sendPacket(new CPacketEncryptionResponse(secretkey, publickey, packetIn.getVerifyToken()), p_operationComplete_1_ -> BotLogin.this.networkManager.enableEncryption(secretkey));
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
        if (!this.networkManager.isLocalChannel()) {
            this.networkManager.setCompressionThreshold(packetIn.getCompressionThreshold());
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onDisconnect(ITextComponent reason) {}
}
