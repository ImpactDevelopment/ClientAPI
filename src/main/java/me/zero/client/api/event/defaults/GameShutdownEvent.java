package me.zero.client.api.event.defaults;

import me.zero.alpine.type.Cancellable;

/**
 * Called when the game is requested to be shutdown.
 * This event is invoked at the head of Minecraft#shutdown().
 * Cancelling this event will result in the shutdown
 * process cancelling. The only reason a developer should
 * cancel shutdown is to complete a cleanup process,
 * if it is unable to be done directly before the shutdown.
 *
 * @author Brady
 * @since 5/24/2017 12:26 PM
 */
public final class GameShutdownEvent extends Cancellable {}
