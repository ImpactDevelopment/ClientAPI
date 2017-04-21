package me.zero.client.api.gui.notification;

/**
 * Implementation of INotification.
 *
 * @since 1.0
 *
 * Created by Brady on 1/6/2017.
 */
public class Notification implements INotification {

	/**
	 * Header text
	 */
	private final String header;

	/**
	 * Subtext
	 */
	private final String subtext;

	/**
	 * Time when notification was created
	 */
	private final long start;

	/**
	 * Fade in/out timer
	 */
	private final long fade;

	/**
	 * How long the notification will be displayed
	 */
	private final long displayTime;
	
	Notification(String header, String subtext, long fade, long displayTime) {
		this.header = header;
		this.subtext = subtext;
		this.start = System.currentTimeMillis();
		this.fade = Math.min(displayTime / 2, fade);
		this.displayTime = displayTime;
	}

	@Override
	public final String getHeader() {
		return this.header;
	}

	@Override
	public final String getSubtext() {
		return this.subtext;
	}

	@Override
	public final long getStart() {
		return this.start;
	}

	@Override
	public final long getFade() {
		return this.fade;
	}

	@Override
	public final long getDisplayTime() {
		return this.displayTime;
	}
}
