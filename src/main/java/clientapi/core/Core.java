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

package clientapi.core;

import clientapi.Client;
import clientapi.ClientInfo;
import clientapi.plugin.Plugin;
import clientapi.plugin.PluginInfo;

/**
 * Core for Clients and Plugins. Generic argument "T"
 * should be the info class of the type.
 *
 * @see Client
 * @see Plugin
 * @see ClientInfo
 * @see PluginInfo
 *
 * @author Brady
 * @since 9/1/2017 1:46 PM
 */
public abstract class Core<T> {

    protected T info;

    public Core(T info) {
        this.info = info;
    }

    public abstract void onInit(T info);

    public final T getInfo() {
        return this.info;
    }
}
