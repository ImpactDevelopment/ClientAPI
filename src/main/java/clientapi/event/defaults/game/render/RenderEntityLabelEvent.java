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

package clientapi.event.defaults.game.render;

import me.zero.alpine.type.Cancellable;

import net.minecraft.entity.Entity;

/**
 * Called before Render#renderLivingLabel(Entity, String, double, double.
 * double, int). The event is cancellable, if cancelled then the rendering of
 * the label will be cancelled.
 *
 * @author Brady
 * @since 5/23/2017 5:40 PM
 */
public final class RenderEntityLabelEvent extends Cancellable {

	/**
	 * The entity that is having their label rendered
	 */
	private final Entity entity;

	/**
	 * The text being rendered on the label
	 */
	private final String text;

	public RenderEntityLabelEvent(Entity entity, String text) {
		this.entity = entity;
		this.text = text;
	}

	/**
	 * The entity having their name rendered
	 */
	public final Entity getEntity() {
		return this.entity;
	}

	/**
	 * @return The text being rendered on the label
	 */
	public final String getText() {
		return this.text;
	}
}
