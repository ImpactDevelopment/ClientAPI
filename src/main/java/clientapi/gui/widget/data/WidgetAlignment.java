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

package clientapi.gui.widget.data;

/**
 * Used to calculate the X offset (Alignment) of widgets. The X value of a
 * widget is increased by the widget's width times the value returned by its
 * alignment.
 *
 * @see DefaultWidgetAlignment
 * @author Brady
 * @since 5/28/2017 10:00 AM
 */
public interface WidgetAlignment {

    float getValue();
}
