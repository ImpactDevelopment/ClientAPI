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

package clientapi.util.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Utils used for mathematical operations.
 *
 * @author Brady
 * @since 1/23/2017 12:00 PM
 */
public final class MathUtils {

	private MathUtils() {}

	/**
	 * Clamps a Number
	 *
	 * @param value The value being clamped
	 * @param minimum The minimum possible value
	 * @param maximum The maximum possible value
	 * @return The clamped value
	 */
	public static <T extends Number> T clamp(T value, T minimum, T maximum) {
		if (minimum.doubleValue() > maximum.doubleValue()) {
			T temp = minimum;
			minimum = maximum;
			maximum = temp;
		}
		if (value.doubleValue() > maximum.doubleValue()) value = maximum;
		if (value.doubleValue() < minimum.doubleValue()) value = minimum;
		return value;
	}

	/**
	 * Rounds a number to a specific place
	 *
	 * @param value Value being rounded
	 * @param places Places being rounded
	 * @return Rounded value
	 */
	public static double roundToPlace(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	/**
	 * Gets a random int between a min value and max value
	 *
	 * @param min The minimum value
	 * @param max The maximum value
	 * @return The random vakue
	 */
	public static int random(int min, int max) {
		if (min > max) {
			int temp = max;
			max = min;
			min = temp;
		}
		return new Random().nextInt(max - min + 1) + min;
	}
}
