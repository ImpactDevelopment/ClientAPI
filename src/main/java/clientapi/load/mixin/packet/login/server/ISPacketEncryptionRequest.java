package clientapi.load.mixin.packet.login.server;

import net.minecraft.network.login.server.SPacketEncryptionRequest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.security.PublicKey;

/**
 * @author Brady
 * @since 8/24/2017 7:39 AM
 */
@Mixin(SPacketEncryptionRequest.class)
public interface ISPacketEncryptionRequest {

    @Accessor String getHashedServerId();

    @Accessor void setHashedServerId(String hashedServerId);

    @Accessor PublicKey getPublicKey();

    @Accessor void setPublicKey(PublicKey publicKey);

    @Accessor byte[] getVerifyToken();

    @Accessor void setVerifyToken(byte[] verifyToken);
}
