package clientapi.load.mixin.packet.client;

import net.minecraft.network.play.client.CPacketSeenAdvancements;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 9:36 PM
 */
@Mixin(CPacketSeenAdvancements.class)
public interface ICPacketSeenAdvancements {

    @Accessor CPacketSeenAdvancements.Action getAction();

    @Accessor void setAction(CPacketSeenAdvancements.Action action);

    @Accessor ResourceLocation getTab();

    @Accessor void setTab(ResourceLocation tab);
}
