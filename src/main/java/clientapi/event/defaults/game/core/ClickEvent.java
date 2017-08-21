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

package clientapi.event.defaults.game.core;

import net.minecraft.client.Minecraft;

/**
 * Event called when a Mouse button is pressed outside of a gui screen while
 * in-game. The only mouse buttons that are supported are the present in the
 * {@code MouseButton} enum. (Left, Right, Middle)
 *
 * @see KeyEvent
 * @see Minecraft#clickMouse()
 * @see Minecraft#rightClickMouse()
 * @see Minecraft#middleClickMouse()
 * @author Brady
 * @since 1/20/2017 12:00 PM
 */
public final class ClickEvent {

	/**
	 * The Mouse button that was clicked
	 */
	private final MouseButton button;

	public ClickEvent(MouseButton button) {
		this.button = button;
	}

	/**
	 * @return The button clicked
	 */
	public final MouseButton getButton() {
		return this.button;
	}

	/**
	 * Types of Mouse Buttons
	 */
	public enum MouseButton {
		LEFT(0), RIGHT(1), MIDDLE(2);

		/**
		 * Mouse Button ID
		 */
		private int id;

		MouseButton(int id) {
			this.id = id;
		}

		/**
		 * @return The ID of the Mouse Button
		 */
		public final int getID() {
			return this.id;
		}
	}
}
