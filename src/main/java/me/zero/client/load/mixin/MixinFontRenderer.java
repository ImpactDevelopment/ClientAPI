package me.zero.client.load.mixin;

import me.zero.client.api.ClientAPI;
import me.zero.client.api.event.defaults.TextEvent;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(FontRenderer.class)
public abstract class MixinFontRenderer {

    @ModifyVariable(method = "renderStringAtPos", at = @At("HEAD"))
    public String renderStringAtPos(String text) {
        TextEvent event = new TextEvent(text);
        ClientAPI.EVENT_BUS.post(event);
        return event.getText();
    }

    @ModifyVariable(method = "getStringWidth", at = @At("HEAD"))
    public String getStringWidth(String text) {
        TextEvent event = new TextEvent(text);
        ClientAPI.EVENT_BUS.post(event);
        return event.getText();
    }
}
