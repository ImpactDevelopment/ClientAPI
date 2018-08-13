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

import me.zero.alpine.EventBus;

/**
 * An implementation of {@code EventBus} where all inherited abstract methods
 * have been implemented with empty bodies, so that only required methods have
 * to be implemented. This is particularly useful in child event buses, where
 * not all methods may need to be implemented.
 *
 * @author Brady
 * @since 11/8/2017
 */
public abstract class AbstractEventBus implements EventBus {

    @Override
    public void subscribe(Object object) {}

    @Override
    public void subscribe(Object... objects) {}

    @Override
    public void subscribe(Iterable<Object> objects) {}

    @Override
    public void unsubscribe(Object object) {}

    @Override
    public void unsubscribe(Object... objects) {}

    @Override
    public void unsubscribe(Iterable<Object> objects) {}

    @Override
    public void post(Object event) {}

    @Override
    public void attach(EventBus bus) {}

    @Override
    public void detach(EventBus bus) {}
}