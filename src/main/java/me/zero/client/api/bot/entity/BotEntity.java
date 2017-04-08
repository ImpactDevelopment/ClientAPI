package me.zero.client.api.bot.entity;

import me.zero.client.api.bot.Bot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.world.World;

/**
 * A Bot Entity, extends EntityPlayerSP so that
 * it can send position updates.
 *
 * @since 1.0
 *
 * Created by Brady on 3/8/2017.
 */
public final class BotEntity extends EntityPlayerSP {

    /**
     * The bot controlling this Entity
     */
    private Bot bot;

    public BotEntity(Minecraft mcIn, World worldIn, NetHandlerPlayClient netHandler, StatisticsManager statFile, Bot bot) {
        super(mcIn, worldIn, netHandler, statFile);
        this.bot = bot;
    }

    public Bot getBot() {
        return this.bot;
    }
}
