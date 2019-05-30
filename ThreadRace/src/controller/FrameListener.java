package controller;

/**
 * Class RaceController implements this listener to listen buttons in
 * RacingFrame.
 * 
 * @see {@link RaceController}
 * @see {@link view.RacingFrame}
 * @author Igor Stojanovic
 *
 */
public interface FrameListener {

	/**
	 * When method is called, barriers are set to on.
	 */
	public void barriersOn();

	/**
	 * When method is called, barriers are set to off.
	 */
	public void barriersOff();

	/**
	 * When method is called, the race begins.
	 */
	public void startRace();
	
	/**
	 * When method is called, the race ends.
	 */
	public void stopRace();
}
