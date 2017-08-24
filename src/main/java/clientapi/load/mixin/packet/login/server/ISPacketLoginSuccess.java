package clientapi.load.mixin.packet.login.server;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.login.server.SPacketLoginSuccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/24/2017 7:41 AM
 */
@Mixin(SPacketLoginSuccess.class)
public interface ISPacketLoginSuccess {

    @Accessor GameProfile getProfile();

    @Accessor void setProfile(GameProfile profile);
}
