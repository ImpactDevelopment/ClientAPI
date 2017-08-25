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

package clientapi.event.bench.impl;

import clientapi.ClientAPI;
import clientapi.event.bench.Benchmark;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

/**
 * Implementation of Benchmark that invokes events to test how fast the event
 * system is
 *
 * @author Brady
 * @since 5/17/2017 2:34 PM
 */
public final class EventBenchmark extends Benchmark {

    public EventBenchmark(int passes, int invokations) {
        super(passes, invokations);
    }

    @Override
    protected void pre() {
        ClientAPI.EVENT_BUS.subscribe(this);
    }

    @Override
    protected void run() {
        ClientAPI.EVENT_BUS.post(new Object());
    }

    @Override
    protected void post() {
        ClientAPI.EVENT_BUS.unsubscribe(this);
    }

    @EventHandler
    private final Listener<Object> benchListener = new Listener<>(event -> {});
}
