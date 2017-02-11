package me.zero.client.api.gui.notification;

import java.util.List;

/**
 * Used to render notifications
 *
 * @since 1.0
 *
 * Created by Brady on 1/6/2017.
 */
public interface INotificationRenderer {

	/**
	 * Draws a list of notifications
	 *
	 * @since 1.0
	 *
	 * @param notifications The notifications getting rendered
	 */
	void draw(List<INotification> notifications);
}
