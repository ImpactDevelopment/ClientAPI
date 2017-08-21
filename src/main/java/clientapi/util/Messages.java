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

package clientapi.util;

import clientapi.util.logger.ILogger;
import clientapi.util.logger.Level;

/**
 * Storage for all Messages used by the Logger's logf method as well as some
 * generic messages sent to the client from the api.
 *
 * @see ILogger#log(Level, String)
 * @see ILogger#logf(Level, String, Object...)
 * @author Brady
 * @since 1/21/2017 12:00 PM
 */
public interface Messages {

	String PLUGIN_LOAD = "Loaded plugin from %s";
	String PLUGIN_JARFILE_CREATE = "Unable to Create plugin JarFile, %s";
	String PLUGIN_INSTANTIATION = "Unable to instantiate PluginMain, %s";
	String PLUGIN_CANT_CREATE_MODULE = "Unable to create Module, %s";
	String PLUGIN_CANT_LOAD_CLASS = "Unable to load Class, %s";
	String PLUGIN_CANT_CREATE_INPUTSTREAM =
	    "Unable to create jar InputStream, %s";
}
