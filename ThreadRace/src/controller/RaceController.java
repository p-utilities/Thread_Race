package controller;

import model.Race;
import view.RacingFrame;

/**
 * Object of this class controls every activity between model and view.
 * To start GUI construct one object of this class and call method initFrame().
 * This class implements RacerListener and FrameListener
 * 
 * @see {@link #initFrame()}
 * @see {@link FrameListener}
 * @see {@link RacerListener}
 * @see {@link controller.RacerListener}
 * @author Igor Stojanovic
 *
 */
public class RaceController implements RacerListener, FrameListener {

	private RacingFrame racingFrame;
	private Race raceModel;

	/**
	 * Initialize objects of classes RacingFrame and Race to start GUI.
	 * 
	 * @see {@link view.RacingFrame}
	 * @see {@link model.Race}
	 */
	public void initFrame() {
		racingFrame = new RacingFrame();
		racingFrame.setFrameButtonListener(this);
		racingFrame.setVisible(true);

		raceModel = new Race(this, racingFrame.getLengthStartToFinish());
	}

	/**
	 * {@inheritDoc} Calls RacingFrame method setCarPosition(CarNumber, int) to
	 * change position of car label in main frame.
	 * 
	 * @see {@link view.RacingFrame}
	 * @see {@link view.RacingFrame#setCarPosition(CarNumber, int)}
	 * @see {@link view.Car}
	 */
	@Override
	public void racerPositionChanged(int distance, CarNumber carNumber) {
		racingFrame.setCarPosition(carNumber, distance);
	}

	/**
	 * {@inheritDoc} Calls raceModel method setRacerBarriers to set barriers to
	 * true.
	 * 
	 * @see {@link model.Race}
	 * @see {@link model.Race#setRacerBarriers(boolean)}
	 */
	@Override
	public void barriersOn() {
		raceModel.setRacerBarriers(true);
	}

	/**
	 * {@inheritDoc} Calls raceModel method setRacerBarriers to set barriers to
	 * false.
	 * 
	 * @see {@link model.Race}
	 * @see {@link model.Race#setRacerBarriers(boolean)}
	 */
	@Override
	public void barriersOff() {
		raceModel.setRacerBarriers(false);
	}

	/**
	 * {@inheritDoc} Calls raceModel method startRace to start the race.
	 * 
	 * @see {@link model.Race}
	 * @see {@link model.Race#startRace()}
	 */
	@Override
	public void startRace() {
		raceModel.startRace();
	}

	/**
	 * {@inheritDoc} Calls raceModel method stopRace to stop the race.
	 * 
	 * @see {@link model.Race}
	 * @see {@link model.Race#stopRace()}
	 */
	@Override
	public void stopRace() {
		raceModel.stopRace();
	}
}
