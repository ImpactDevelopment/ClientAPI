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

package clientapi.load;

/**
 * Called when a state occurs in which the program is unable to proceed while
 * the Client is being initialized by the LaunchClassLoader.
 *
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
public final class ClientInitException extends RuntimeException {

    public ClientInitException(String message) {
        super(message);
    }
}
