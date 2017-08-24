package clientapi.load.mixin.packet.login.client;

import net.minecraft.network.login.client.CPacketEncryptionResponse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 10:13 PM
 */
@Mixin(CPacketEncryptionResponse.class)
public interface ICPacketEncryptionResponse {

    @Accessor byte[] getSecretKeyEncrypted();

    @Accessor void setSecretKeyEncrypted(byte[] secretKeyEncrypted);

    @Accessor byte[] verifyTokenEncrypted();

    @Accessor void setVerifyTokenEncrypted(byte[] verifyTokenEncrypted);
}
