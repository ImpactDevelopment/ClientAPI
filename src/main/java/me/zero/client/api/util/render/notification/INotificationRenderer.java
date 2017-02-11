package me.zero.client.api.util.render.notification;

import java.util.List;

/**
 * Used to render notifications
 *
 * @since 1.0
 *
 * Created by Brady on 1/6/2017.
 */
public interface INotificationRenderer {
	
	void draw(List<INotification> notifications);
}
