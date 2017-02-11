package me.zero.client.api.util.render.notification;

/**
 * Base for Notifications
 *
 * @since 1.0
 *
 * Created by Brady on 1/6/2017.
 */
interface INotification {
	
	String getHeader();
	
	String getSubtext();
	
	long getStart();
	
	long getFade();
	
	long getDisplayTime();
}
