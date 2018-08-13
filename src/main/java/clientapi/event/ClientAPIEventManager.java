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

package clientapi.event;

import me.zero.alpine.EventManager;

/**
 * Implementation of {@code EventManager} that allows the
 * user to enable/disable the post function in order to
 * allow/prevent events from being posted to their listeners.
 *
 * @author Brady
 * @since 4/21/2018
 */
public final class ClientAPIEventManager extends EventManager {

    /**
     * Whether or not this EventManager is enabled. If this is set
     * to {@code false}, no events will be posted to their listeners.
     */
    private boolean enabled;

    public ClientAPIEventManager() {
        this.enabled = true;
    }

    @Override
    public final void post(Object event) {
        if (enabled && event != null) {
            super.post(event);
        }
    }

    /**
     * Sets whether or not this EventManager is enabled.
     *
     * @param enabled The new enabled state
     */
    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return Whether or not this EventManager is enabled.
     */
    public final boolean isEnabled() {
        return this.enabled;
    }
}
