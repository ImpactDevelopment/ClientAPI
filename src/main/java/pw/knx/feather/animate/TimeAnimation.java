package pw.knx.feather.animate;

/**
 * A time-based animation utility. This class essentially acts as a wrapper
 * for a double that tracks your animation's progress. You can then use get() to
 * retrieve this double as any range of values you need for your implementation.
 * Features include functions to control animation behavior (play/pause),
 * transition appearance, and animation duration.
 * <p>
 * "time-based" refers to how the animation increments based on how much system time
 * has passed since your last call. If you need to tick your animation based on something
 * else, consider using default *Animation*.
 * <p>
 * The voids in this interface return the Animation object for easy method chaining.
 *
 * @author KNOXDEV
 * @since 6/10/2016 06:21
 */
public class TimeAnimation extends Animation {

	/**
	 * Now that we'll dealing with time, we'll need to keep track of the time that's passed.
	 */
	private long prevTime;

	/**
	 * TimeAnimation constructor. See Class Documentation for more information.
	 * Notably sets the default duration to 400, since we're now dealing with
	 * milliseconds and not ticks.
	 */
	public TimeAnimation() {
		this.duration = 400;
	}

	/**
	 * Sets the animation to progress.
	 * In TimeAnimation, also sets your last system time.
	 *
	 * @return the original Animation object
	 */
	@Override
	public Animation play() {
		prevTime = System.currentTimeMillis();
		return super.play();
	}

	/**
	 * Gives your current Animation progress on a scale from 0-1.0.
	 * Automatically increments your progress by however many
	 * milliseconds its been since last tick, creating a tick-independent,
	 * time-dependant animation.
	 *
	 * @return your animation's progress as a double from 0-1.0
	 */
	@Override
	public double get() {
		final long time = System.currentTimeMillis();
		increment((int) (time - prevTime));
		this.prevTime = time;
		return translate(progress);
	}
}
