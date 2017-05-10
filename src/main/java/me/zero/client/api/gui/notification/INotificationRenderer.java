package me.zero.client.api.gui.notification;

import java.util.Collection;

/**
 * Used to render notifications
 *
 * @author Brady
 * @since 1/6/2017 12:00 PM
 */
public interface INotificationRenderer {

	/**
	 * Draws a list of notifications
	 *
	 * @param notifications The notifications getting rendered
	 */
	void draw(Collection<INotification> notifications);
}
