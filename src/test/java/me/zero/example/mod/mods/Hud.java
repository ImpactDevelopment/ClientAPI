package me.zero.example.mod.mods;

import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.Listener;
import me.zero.client.api.event.defaults.Render2DEvent;
import me.zero.client.api.module.Mod;
import me.zero.client.api.module.Module;
import me.zero.example.ExampleClient;
import me.zero.example.mod.category.IRender;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

import java.util.Comparator;

/**
 * Created by Brady on 2/8/2017.
 */
@Mod(name = "HUD", description = "Displays an In-Game HUD")
public class Hud extends Module implements IRender {

    private int y;

    public Hud() {
        this.toggle();
    }

    @EventHandler
    private Listener<Render2DEvent> render2DListener = new Listener<>(event -> {
        ScaledResolution sr = new ScaledResolution(mc);
        FontRenderer font = mc.fontRenderer;

        int color = 0xFF98ABB8;

        font.drawStringWithShadow("Client", 2, 2, color);

        y = sr.getScaledHeight() - 12;

        ExampleClient.getInstance().getModuleManager().getData().stream().filter(Module::getState)
                .sorted(Comparator.comparingInt(m -> -font.getStringWidth(m.getName()))).forEach(module -> {
            font.drawStringWithShadow(module.getName(), sr.getScaledWidth() - 2 - font.getStringWidth(module.getName()), y, color);
            y -= font.FONT_HEIGHT;
        });
    });
}
