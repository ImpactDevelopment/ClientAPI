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


import me.zero.alpine.bus.EventBus;
import me.zero.alpine.listener.Listenable;
import me.zero.alpine.listener.Listener;

/**
 * An implementation of {@link EventBus} where all inherited abstract methods
 * have been implemented with empty bodies, so that only required methods have
 * to be implemented. This is particularly useful in child event buses, where
 * not all methods may need to be implemented.
 *
 * @author Brady
 * @since 11/8/2017
 */
public abstract class AbstractEventBus implements EventBus {

    @Override
    public void subscribe(Listenable listenable) {}

    @Override
    public void subscribe(Listener listener) {}

    @Override
    public void subscribeAll(Listenable... listenables) {}

    @Override
    public void subscribeAll(Iterable<Listenable> listenables) {}

    @Override
    public void unsubscribe(Listenable listenable) {}

    @Override
    public void unsubscribe(Listener listener) {}

    @Override
    public void unsubscribeAll(Listenable... listenables) {}

    @Override
    public void unsubscribeAll(Iterable<Listenable> listenables) {}

    @Override
    public void post(Object event) {}
}