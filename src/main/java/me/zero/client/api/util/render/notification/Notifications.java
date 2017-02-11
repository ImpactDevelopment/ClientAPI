package me.zero.client.api.util.render.notification;

import java.util.ArrayList;
import java.util.List;

/**
 * Essentially a Notification Manager, only takes in a renderer
 *
 * @since 1.0
 *
 * Created by Brady on 1/6/2017.
 */
public class Notifications {

	private List<INotification> notifications = new ArrayList<>();
	private final INotificationRenderer renderer;
	
	public Notifications(INotificationRenderer renderer) {
		this.renderer = renderer;
	}

	public void post(String header, String subtext) {
		this.post(header, subtext, 2500);
	}
	
	public void post(String header, String subtext, long displayTime) {
		this.post(header, subtext, (long) (displayTime / 16F), displayTime);
	}
	
	public void post(String header, String subtext, long fade, long displayTime) {
		this.notifications.add(new Notification(header, subtext, fade, displayTime));
	}

	public void updateAndRender() {
		renderer.draw(notifications);
	}

	/**
	 * Various Notification Types
	 */
	public enum Type {
		URGENT, WARNING, INFO
	}
}
