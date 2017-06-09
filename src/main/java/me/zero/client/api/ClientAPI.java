/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.api;

import me.zero.alpine.EventBus;
import me.zero.client.api.event.CAPIEventManager;

/**
 * Contains some constants that are used throughout the API.
 *
 * @author Brady
 * @since 5/27/2017 9:52 AM
 */
public final class ClientAPI {

    /**
     * Prevent instantiation
     */
    private ClientAPI() {}

    /**
     * Instance of the API event bus. All default game event are
     * passed through this event bus.
     */
    public static final EventBus EVENT_BUS = new CAPIEventManager();
}
