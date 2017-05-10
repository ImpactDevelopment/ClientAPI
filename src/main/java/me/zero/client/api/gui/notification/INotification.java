package me.zero.client.api.gui.notification;

/**
 * Base for Notifications
 *
 * @since 1.0
 *
 * @author Brady
 * @since 1/6/2017 12:00PM
 */
interface INotification {

	/**
	 * @since 1.0
	 *
	 * @return The Header of the Notificaton
	 */
	String getHeader();

	/**
	 * @since 1.0
	 *
	 * @return The Subtext of the Notification
	 */
	String getSubtext();

	/**
	 * @since 1.0
	 *
	 * @return Returns the time in milliseconds when this Notification was created
	 */
	long getStart();

	/**
	 * @since 1.0
	 *
	 * @return Returns the time in milliseconds for the fade in and out
	 */
	long getFade();

	/**
	 * @since 1.0
	 *
	 * @return Returns the time in milliseconds that this notification should be displayed for
	 */
	long getDisplayTime();
}
