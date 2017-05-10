package me.zero.client.api.gui.notification;

import java.util.Collection;

/**
 * Used to render notifications
 *
 * @since 1.0
 *
 * @author Brady
 * @since 1/6/2017 12:00PM
 */
public interface INotificationRenderer {

	/**
	 * Draws a list of notifications
	 *
	 * @since 1.0
	 *
	 * @param notifications The notifications getting rendered
	 */
	void draw(Collection<INotification> notifications);
}
