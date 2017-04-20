package pw.knx.feather.animate;

/**
 * A tick-based animation utility. This class essentially acts as a wrapper
 * for a double that tracks your animation's progress. You can then use get() to
 * retrieve this double as any range of values you need for your implementation.
 * Features include functions to control animation behavior (play/pause),
 * transition appearance, and animation duration.
 * <p>
 * "tick-based" refers to how the animation increments once per call to the animation's
 * get() function. If you need the animation to tick based on time, please use TimeAnimation.
 * <p>
 * The voids in this interface return the Animation object for easy method chaining.
 *
 * @author KNOXDEV
 * @since 6/10/2016 06:21
 */
public class Animation {

	/**
	 * Your internal Transition object. Defaults to Linear for simplicity.
	 */
	private Transition transition = Transition.LINEAR;

	/**
	 * An integer representing your current animation duration. Stored in "ticks"
	 */
	protected int duration = 20;

	/**
	 * True if the animation is playing from 0-1.0, false if from 1.0-0
	 */
	private boolean isForward = true;

	/**
	 * True if your animation is currently in progress
	 */
	private boolean inProgress = false;

	/**
	 * The internal double representing your actual progress thus far
	 */
	protected double progress = 0;

	/**
	 * Sets the animation to progress.
	 *
	 * @return the original Animation object
	 */
	public Animation play() {
		this.inProgress = true;
		return this;
	}

	/**
	 * Sets the animation to stop progressing.
	 *
	 * @return the original Animation object
	 */
	public Animation pause() {
		this.inProgress = false;
		return this;
	}

	/**
	 * Restarts the animation and pauses its progress.
	 *
	 * @return the original Animation object
	 */
	public Animation cancel() {
		return restart().pause();
	}

	/**
	 * Resets your progress to its starting position, based on its current direction.
	 *
	 * @return the original Animation object
	 */
	public Animation restart() {
		this.progress = isForward ? 0 : 1;
		return this;
	}

	/**
	 * @return the original Animation object
	 */
	public Animation reverse() {
		this.isForward = false;
		return this;
	}

	/**
	 * @return the original Animation object
	 */
	public Animation forward() {
		this.isForward = true;
		return this;
	}

	/**
	 * @param transition the transition you wish to set your currentl Animation to
	 * @return the original Animation object
	 */
	public Animation setTransition(Transition transition) {
		this.transition = transition;
		return this;
	}

	/**
	 * @param duration the duration you wish to set your Animation to
	 * @return the original Animation object
	 */
	public Animation setDuration(int duration) {
		this.duration = duration;
		return this;
	}

	/**
	 * Directly sets your animation's internal progress.
	 *
	 * @param progress the progress as a double from 0-1.0
	 * @return the original Animation object
	 */
	public Animation setProgress(double progress) {
		this.progress = progress;
		return this;
	}

	/**
	 * @return true, if the animation is currently in progress
	 */
	public boolean isRunning() {
		return this.inProgress;
	}

	/**
	 * Gives your current Animation progress on a scale from 0-1.0.
	 * Automatically increments your progress by one tick.
	 *
	 * @return your animation's progress as a double from 0-1.0
	 */
	public double get() {
		increment(1);
		return translate(progress);
	}

	/**
	 * Does everything get() does, only scales your progress to be a
	 * corresponding value in your inputted range.
	 *
	 * @param low  the low end of your range
	 * @param high the high end of your range
	 * @return your progress as a number between *low* and *high*
	 */
	public double get(double low, double high) {
		return ((high - low) * get() + low);
	}

	/**
	 * Increments your progress given the total duration and the number of ticks that have passed.
	 *
	 * @param ticks number of actual ticks to increment your progress by
	 */
	protected void increment(int ticks) {
		if (!inProgress) return;
		if (!isForward) ticks *= -1;
		this.progress += (double) ticks / (double) duration;

		if (progress > 1) {
			this.progress = 1;
			this.inProgress = false;
		} else if (progress < 0) {
			this.progress = 0;
			this.inProgress = false;
		}
	}

	/**
	 * An internal translation function that's used to apply your given
	 * Transition to your current output. See the Transition Enum for specific details.
	 *
	 * @param progress Your current animation's progress
	 * @return The output progress given your input progress and your current transition
	 */
	protected double translate(double progress) {
		switch (this.transition) {
			case CURVE:
				return progress * progress;
			case STEEP_CURVE:
				return Math.pow(progress, 3);
			case BEZIER_CURVE:
				return Math.pow(-1 + Math.sqrt(-progress + 1), 2);
			case INVERSE_CURVE:
				return -Math.pow(progress - 1, 2) + 1;
			case INVERSE_STEEP_CURVE:
				return Math.pow(progress - 1, 3) + 1;
			case RUBBER:
				return -Math.sin(10.0 * progress) / (10.0 * progress) + 1;
			case INERTIA:
				return Math.cbrt(progress - 0.5) / Transition.INERTIA_CONST + 0.5;
			case INSTANT:
				return Math.round(progress);
		}

		// Min and Max to clamp the value between 0 and 1, just in case.
		return Math.min(Math.max(progress, 0), 1);
	}

	/**
	 * An enum storing the different types of visual Transitions available. Simply set
	 * the current transition with setTransition() to see the difference.
	 * It's important to note that the Transition you choose does not change the speed
	 * of the animation, just the visual output when you use get().
	 */
	public enum Transition {

		/**
		 * LINEAR is just that. No transition whatsoever, from A->B
		 */
		LINEAR,

		/**
		 * CURVE refers to a simple quadratic curve where the beginning starts out
		 * fast but ends slow.
		 */
		CURVE,

		/**
		 * STEEP_CURVE is simply a cube alternative to CURVE that's a little
		 * bit more drastic.
		 */
		STEEP_CURVE,

		/**
		 * BEZIER_CURVE results in a mathamatically perfect alternative to CURVE
		 * that's consistent in variation.
		 */
		BEZIER_CURVE,

		/**
		 * INVERSE_CURVE is literally just the opposite of CURVE, a quadratic curve
		 * that starts out slow but ends fast.
		 */
		INVERSE_CURVE,

		/**
		 * INVERSE_STEEP_CURVE is simply a cube alternative to INVERSE_CURVE that's a little
		 * bit more drastic.
		 */
		INVERSE_STEEP_CURVE,

		/**
		 * RUBBER is an applied sin wave algorithm that's named after Apple's patented
		 * rubber-banding animation. Fortunately, this is a different implementation.
		 */
		RUBBER,

		/**
		 * INERTIA is an interesting little function reminiscent of a bell curve:
		 * it starts out slow and ends slow, but the middle is fast. This style usually
		 * looks the most professional and sleek.
		 */
		INERTIA,

		/**
		 * INSTANT clamps the progress to a whole number, resulting in an animation
		 * that's over as soon as it starts.
		 */
		INSTANT;

		/**
		 * A magic number that's used for the INERTIA transition
		 */
		final static double INERTIA_CONST = Math.cbrt(0.5) / 0.5;
	}
}
