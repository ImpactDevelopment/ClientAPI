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
public class TickAnimation {

	/**
	 * Your internal Transition object. Defaults to Linear for simplicity.
	 */
	private Transition transition = Transition.LINEAR;

	/**
	 * An integer representing your current animation duration. Stored in "ticks"
	 */
	int duration = 20;

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
	double progress = 0;

	/**
	 * Sets the animation to progress.
	 *
	 * @return the original Animation object
	 */
	public TickAnimation play() {
		this.inProgress = true;
		return this;
	}

	/**
	 * Sets the animation to stop progressing.
	 *
	 * @return the original Animation object
	 */
	public TickAnimation pause() {
		this.inProgress = false;
		return this;
	}

	/**
	 * Restarts the animation and pauses its progress.
	 *
	 * @return the original Animation object
	 */
	public TickAnimation cancel() {
		return restart().pause();
	}

	/**
	 * Resets your progress to its starting position, based on its current direction.
	 *
	 * @return the original Animation object
	 */
	public TickAnimation restart() {
		this.progress = isForward ? 0 : 1;
		return this;
	}

	/**
	 * @return the original Animation object
	 */
	public TickAnimation reverse() {
		this.isForward = false;
		return this;
	}

	/**
	 * @return the original Animation object
	 */
	public TickAnimation forward() {
		this.isForward = true;
		return this;
	}

	/**
	 * @param transition the transition you wish to set your current Animation to
	 * @return the original Animation object
	 */
	public TickAnimation setTransition(Transition transition) {
		this.transition = transition;
		return this;
	}

	/**
	 * @param duration the duration you wish to set your Animation to
	 * @return the original Animation object
	 */
	public TickAnimation setDuration(int duration) {
		this.duration = duration;
		return this;
	}

	/**
	 * Directly sets your animation's internal progress.
	 *
	 * @param progress the progress as a double from 0-1.0
	 * @return the original Animation object
	 */
	public TickAnimation setProgress(double progress) {
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
		transition.apply(progress);

		// Min and Max to clamp the value between 0 and 1, just in case.
		return Math.min(Math.max(progress, 0), 1);
	}
}
