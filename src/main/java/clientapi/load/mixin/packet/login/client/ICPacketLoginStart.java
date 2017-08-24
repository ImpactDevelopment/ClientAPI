package clientapi.load.mixin.packet.login.client;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.login.client.CPacketLoginStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 10:15 PM
 */
@Mixin(CPacketLoginStart.class)
public interface ICPacketLoginStart {

    @Accessor GameProfile getProfile();

    @Accessor void setProfile(GameProfile profile);
}
