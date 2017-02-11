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
	private String header;

	/**
	 * Subtext
	 */
	private String subtext;

	/**
	 * Time when notification was created
	 */
	private long start;

	/**
	 * Fade in/out timer
	 */
	private long fade;

	/**
	 * How long the notification will be displayed
	 */
	private long displayTime;
	
	Notification(String header, String subtext, long fade, long displayTime) {
		this.header = header;
		this.subtext = subtext;
		this.start = System.currentTimeMillis();
		this.fade = Math.min(displayTime / 2, fade);
		this.displayTime = displayTime;
	}

	@Override
	public String getHeader() {
		return this.header;
	}

	@Override
	public String getSubtext() {
		return this.subtext;
	}

	@Override
	public long getStart() {
		return this.start;
	}

	@Override
	public long getFade() {
		return this.fade;
	}

	@Override
	public long getDisplayTime() {
		return this.displayTime;
	}
}
