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

package clientapi.gui.tab;

import clientapi.gui.IRenderer;

/**
 * Base for Tab Gui Elements
 *
 * @see ITabGui
 * @author Brady
 * @since 1/20/2017 12:00 PM
 */
public interface ITabGuiElement extends IRenderer {

	/**
	 * @see #getTextColor()
	 * @return The text that will be displayed by the renderer
	 */
	String getText();

	/**
	 * @see #getText()
	 * @return The color of the Text that is rendered
	 */
	default int getTextColor() {
		return 0xFFFFFFFF;
	}

	/**
	 * Called whenever a key is pressed
	 *
	 * @param key - The key that iis pressed
	 */
	void keyPress(int key);

	/**
	 * @see #toggle()
	 * @return The state of the Element
	 */
	boolean isActive();

	/**
	 * Toggles the Active state
	 *
	 * @see #isActive()
	 */
	void toggle();
}
