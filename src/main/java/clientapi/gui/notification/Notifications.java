/*
 * Copyright 2017 ImpactDevelopment
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

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Essentially a Notification Manager, only takes in a renderer
 *
 * @author Brady
 * @since 1/6/2017 12:00 PM
 */
public class Notifications {

    /**
     * The list of notifications for this notification handler
     */
    private final Set<INotification> notifications = new LinkedHashSet<>();

    /**
     * The renderer for this notification handler
     */
    private final INotificationRenderer renderer;

    public Notifications(INotificationRenderer renderer) {
        this.renderer = renderer;
    }

    /**
     * Creates a notification
     *
     * @param header The header text
     * @param subtext The subtext
     */
    public void post(String header, String subtext) {
        this.post(header, subtext, 2500);
    }

    /**
     * Creates a notification
     *
     * @param header The header text
     * @param subtext The subtext
     * @param displayTime The display time
     */
    public void post(String header, String subtext, long displayTime) {
        this.post(header, subtext, (long) (displayTime / 16F), displayTime);
    }

    /**
     * Creates a notification
     *
     * @param header The header text
     * @param subtext The subtext
     * @param fade The fade time
     * @param displayTime The display time
     */
    public void post(String header, String subtext, long fade,
        long displayTime) {
        this.notifications
            .add(new Notification(header, subtext, fade, displayTime));
    }

    /**
     * Renders the nofications
     */
    public void render() {
        renderer.draw(notifications);
    }
}
