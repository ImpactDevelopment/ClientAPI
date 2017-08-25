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

package clientapi.event;

import me.zero.alpine.EventManager;

/**
 * Gives more control to EventManager
 *
 * @author Brady
 * @since 6/9/2017 2:53 PM
 */
public final class CAPIEventManager extends EventManager {

    private boolean enabled = true;

    @Override
    public final void post(Object event) {
        if (enabled) super.post(event);
    }

    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public final boolean isEnabled() {
        return this.enabled;
    }
}
