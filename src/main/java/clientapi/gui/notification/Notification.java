/*
 * Copyright 2018 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.gui.notification;

import clientapi.util.Timer;

/**
 * Implementation of INotification.
 *
 * @author Brady
 * @since 1/6/2017
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
		this.start = Timer.getTimeMillis();
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
