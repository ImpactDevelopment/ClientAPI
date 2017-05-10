package me.zero.client.api.gui.notification;

/**
 * Base for Notifications
 *
 * @author Brady
 * @since 1/6/2017 12:00 PM
 */
interface INotification {

	/**
	 * @return The Header of the Notificaton
	 */
	String getHeader();

	/**
	 * @return The Subtext of the Notification
	 */
	String getSubtext();

	/**
	 * @return Returns the time in milliseconds when this Notification was created
	 */
	long getStart();

	/**
	 * @return Returns the time in milliseconds for the fade in and out
	 */
	long getFade();

	/**
	 * @return Returns the time in milliseconds that this notification should be displayed for
	 */
	long getDisplayTime();
}
