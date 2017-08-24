package clientapi.load.mixin.packet.play.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketClientSettings;
import net.minecraft.util.EnumHandSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:14 PM
 */
@Mixin(CPacketClientSettings.class)
public interface ICPacketClientSettings {

    @Accessor String getLang();

    @Accessor void setLang(String lang);

    @Accessor int getView();

    @Accessor void setView(int view);

    @Accessor EntityPlayer.EnumChatVisibility getChatVisibility();

    @Accessor void setChatVisibility(EntityPlayer.EnumChatVisibility chatVisibility);

    @Accessor boolean isEnabledColors();

    @Accessor void setEnabledColors(boolean enabledColors);

    @Accessor int getModelPartFlags();

    @Accessor void setModelPartFlags(int modelPartFlags);

    @Accessor EnumHandSide getMainHand();

    @Accessor void setMainHand(EnumHandSide mainHand);
}
