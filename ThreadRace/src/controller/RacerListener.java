package controller;

import model.Racer;

/**
 * RaceController implements this interface to listen the object of class Racer. 
 * 
 * @see {@link #racerPositionChanged(int, CarNumber)}
 * @see {@link RaceController}
 * @see {@link model.Racer}
 * @author Igor Stojanovic
 *
 */
public interface RacerListener {

	/**
	 * This method will be called every time when parameter distance in class Racer
	 * is changed.
	 * 
	 * @see {@link Racer}
	 * @param distance which racing car in frame need to move.
	 * @param carNumber - car that need to move for given distance.
	 */
	public void racerPositionChanged(int distance, CarNumber carNumber);
}
