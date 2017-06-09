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

import me.zero.client.api.event.handle.ClientHandler;

/**
 * The base for all Clients
 *
 * @author Brady
 * @since 1/19/2017 12:00 PM
 */
public abstract class Client extends ClientBase {

    /**
     * Called after the game has initialized.
     *
     * @see me.zero.client.api.module.Module
     * @see me.zero.client.api.module.plugin.Plugin
     *
     * @param handler The handler that's being used to handle Client API events
     */
    public abstract void onInit(ClientHandler handler);
}
