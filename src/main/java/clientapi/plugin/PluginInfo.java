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

package clientapi.plugin;

/**
 * Representation of the {@code plugin.json} format.
 *
 * @author Brady
 * @since 1/26/2017 12:00 PM
 */
public final class PluginInfo {

    /**
     * Name of the Plugin
     */
    private String name;

    /**
     * Description of the Plugin
     */
    private String description;

    /**
     * Build version of the plugin
     */
    private double build;

    /**
     * Main class of the Plugin
     */
    private String main;

    /**
     * @return The name of this plugin
     */
    public String getName() {
        return name;
    }

    /**
     * @return The description of this plugin
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The build version of the plugin
     */
    public double getBuild() {
        return this.build;
    }

    /**
     * @return The main class of this plugin
     */
    public String getMain() {
        return main;
    }
}
