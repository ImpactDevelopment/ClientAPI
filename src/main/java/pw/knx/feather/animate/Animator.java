package pw.knx.feather.animate;

/**
 *
 * In the future, will be more useful than it is.
 * For now, mostly builder methods
 *
 * @author KNOXDEV
 * @since 8/23/2017
 */
public class Animator {
	public TickAnimation byTicks(int ticks) {
		return new TickAnimation().setDuration(ticks);
	}

	public TickAnimation byTime(int milliseconds) {
		return new TimeAnimation().setDuration(milliseconds);
	}
}
