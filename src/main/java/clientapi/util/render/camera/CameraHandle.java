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

package clientapi.util.render.camera;

/**
 * Class for handling camera properties
 *
 * @author Brady
 * @since 2/4/2017 12:00 PM
 */
public interface CameraHandle {

    /**
     * @return The visibility of this camera
     */
    boolean visible();

    /**
     * @return Whether or not this camera is reflected
     */
    boolean reflected();

    /**
     * @return The width of the camera window
     */
    int width();

    /**
     * @return The height of the camera window
     */
    int height();
}
