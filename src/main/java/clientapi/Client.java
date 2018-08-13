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

package clientapi;

import clientapi.config.ClientConfiguration;
import clientapi.config.Configurable;

/**
 * The base for all ClientAPI Clients. The classpath of implementations
 * of {@code Client} should be defined in the {@code client.json} file.
 *
 * @see ClientConfiguration
 *
 * @author Brady
 * @since 1/19/2017 12:00 PM
 */
public abstract class Client extends Configurable<ClientConfiguration> {

    public Client(ClientConfiguration config) {
        super(config);
    }
}
