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

package clientapi.value;

import pw.knx.feather.structures.Color;

/**
 * @author Brady
 * @since 9/27/2018
 */
public interface IColorValue extends IValue<Color> {

    default float getRed() {
        return this.getValue().getRed();
    }

    default float getGreen() {
        return this.getValue().getGreen();
    }

    default float getBlue() {
        return this.getValue().getBlue();
    }

    default float getHue() {
        return this.getValue().getHue();
    }

    default float getSaturation() {
        return this.getValue().getSaturation();
    }

    default float getBrightness() {
        return this.getValue().getBrightness();
    }

    default int getHex() {
        return this.getValue().getHex();
    }
}
