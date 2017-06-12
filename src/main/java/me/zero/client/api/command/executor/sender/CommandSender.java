package me.zero.client.api.command.executor.sender;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Represents a
 *
 * @author Brady
 * @since 6/11/2017 6:41 PM
 */
public interface CommandSender {

    String getName();

    static CommandSender from(EntityPlayer player) {
        return player::getName;
    }
}
