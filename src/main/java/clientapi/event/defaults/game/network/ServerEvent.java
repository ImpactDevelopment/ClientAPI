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

package clientapi.event.defaults.game.network;

import me.zero.alpine.type.EventState;
import net.minecraft.client.multiplayer.ServerData;

/**
 * Called when the client user connects or disconnects from a server
 *
 * @author Brady
 * @since 9/7/2017
 */
public class ServerEvent {

    /**
     * Server being connected/disconnected to/from
     */
    private final ServerData serverData;

    ServerEvent(ServerData serverData) {
        this.serverData = serverData;
    }

    /**
     * @return Server being connected/disconnected to/from
     */
    public final ServerData getServerData() {
        return this.serverData;
    }

    /**
     * Called when the client user connects to a server
     */
    public static class Connect extends ServerEvent {

        /**
         * State of the event
         *
         * @see State
         */
        private final State state;

        public Connect(State state, ServerData serverData) {
            super(serverData);
            this.state = state;
        }

        /**
         * @return The state of the event
         */
        public final State getState() {
            return this.state;
        }

        public enum State {

            /**
             * Called before the connection attempt
             */
            PRE,

            /**
             * Indicates that the connection attempt was successful
             */
            CONNECT,

            /**
             * Indicates that an exception occurred when trying to connect to the target server.
             * This will be followed by an instance of {@code ServerEvent.Disconnect} being posted.
             */
            FAILED
        }
    }

    /**
     * Called when the client user disconnects from a server
     */
    public static class Disconnect extends ServerEvent {

        /**
         * State of the event. PRE if before the disconnect processing.
         * POST if disconnect processing has already been executed.
         */
        private final EventState state;

        /**
         * Whether or not the connection was forcefully closed. True if the
         * server called for the client to be disconnected. False if the
         * client manually disconnected through {@code GuiIngameMenu}.
         */
        private final boolean forced;

        public Disconnect(EventState state, boolean forced, ServerData serverData) {
            super(serverData);
            this.state = state;
            this.forced = forced;
        }

        /**
         * @return The state of the event
         */
        public final EventState getState() {
            return this.state;
        }

        /**
         * @return Whether or not the connection was forcefully closed
         */
        public final boolean isForced() {
            return this.forced;
        }
    }

    @Override
    public String toString() {
        return "ServerEvent{" +
                "serverData=" + serverData +
                '}';
    }
}
