package pw.knx.feather.animate;

import java.util.function.Function;

/**
 * An enum storing the different types of visual Transitions available. Simply set
 * the current transition with setTransition() to see the difference.
 * It's important to note that the Transition you choose does not change the speed
 * of the animation, just the visual output when you use get().
 *
 * @author KNOXDEV
 * @since 6/8/2017 6:19 PM
 */

public enum Transition {

	/**
	 * LINEAR is just that. No transition whatsoever, from A->B
	 */
	LINEAR(a -> a),

	/**
	 * CURVE refers to a simple quadratic curve where the beginning starts out
	 * fast but ends slow.
	 */
	CURVE(a -> a * a),

	/**
	 * STEEP_CURVE is simply a cube alternative to CURVE that's a little
	 * bit more drastic.
	 */
	STEEP_CURVE(a -> a * a * a),

	/**
	 * BEZIER_CURVE results in a mathematically perfect alternative to CURVE
	 * that's consistent in variation.
	 */
	BEZIER_CURVE(a -> {
		double curve = -1 + Math.sqrt(-a + 1);
		return curve*curve;
	}),

	/**
	 * INVERSE_CURVE is literally just the opposite of CURVE, a quadratic curve
	 * that starts out slow but ends fast.
	 */
	INVERSE_CURVE(a -> -(a-1)*(a-1) + 1),

	/**
	 * INVERSE_STEEP_CURVE is simply a cube alternative to INVERSE_CURVE that's a little
	 * bit more drastic.
	 */
	INVERSE_STEEP_CURVE(a -> Math.pow(a-1, 3) + 1),

	/**
	 * RUBBER is an applied sin wave algorithm that's named after Apple's patented
	 * rubber-banding animation. Fortunately, this is a different implementation.
	 */
	RUBBER(a -> -Math.sin(10.0 * a) / (10.0 * a) + 1),

	/**
	 * INERTIA is an interesting little function reminiscent of a bell curve:
	 * it starts out slow and ends slow, but the middle is fast. This style usually
	 * looks the most professional and sleek both ways.
	 *
	 * The magic number is precalculated for performance: 2/Math.cbrt(2)
	 */
	INERTIA(a -> Math.cbrt(a - 0.5) / (1.5874010519681994)),

	/**
	 * INSTANT clamps the progress to a whole number, resulting in an animation
	 * that's over as soon as it starts.
	 */
	INSTANT(a -> (double) Math.round(a));

	/**
	 * The internal functional relationship between a transition's input and output
	 */
	private final Function<Double, Double> translator;

	Transition(Function<Double, Double> translator) {
		this.translator = translator;
	}

	/**
	 * @param input the animation progress from 0.0 to 1.0
	 * @return the corresponding output progress for this Transition
	 */
	public double apply(double input) {
		return translator.apply(input);
	}
}
