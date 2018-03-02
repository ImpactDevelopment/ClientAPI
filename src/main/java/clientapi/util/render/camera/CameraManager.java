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

package clientapi.util.render.camera;

import clientapi.manage.Manager;

/**
 * Used to store all of the
 *
 * @author Brady
 * @since 2/9/2017 12:00 PM
 */
public final class CameraManager extends Manager<Camera> {

    /**
     * Instance of the CameraManager
     */
    private static final CameraManager instance = new CameraManager();

    private CameraManager() {
        super("Camera");
    }

    @Override
    public final void load() {}

    @Override
    public final void save() {}

    /**
     * Registers a camera to the Manager
     *
     * @param camera Camera being registered
     */
    final void register(Camera camera) {
        if (!this.contains(camera))
            this.add(camera);
    }

    /**
     * @return The instance of the CameraManager
     */
    public static CameraManager getInstance() {
        return CameraManager.instance;
    }
}
