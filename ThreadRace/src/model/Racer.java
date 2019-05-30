package model;

import controller.CarNumber;
import controller.RacerListener;
import model.mySemaphores.MyFinishSemaphore;
import model.mySemaphores.MyPitSemaphore;
import model.mySemaphores.MySemaphore;
import model.mySemaphores.MyStartSemaphore;

/**
 * This class implements Runnable interface. When method run() (through thread
 * method start()) is called, it will calculate and send data to the racer
 * listener. Data contains distance that every racer does and CarNumber of that
 * racer.
 * 
 * @see {@link #Racer(boolean, CarNumber, RacerListener)} constructor
 * @see {@link #run()}
 * @see {@link controller.CarNumber}
 * @author Igor Stojanovic
 *
 */
public class Racer implements Runnable {

	private int distance;
	private CarNumber carNumber;

	private RacerListener racerListener;

	private MySemaphore startSemaphore = MyStartSemaphore.getStartSemaphoreInstance();
	private MySemaphore finishSemaphore = MyFinishSemaphore.getFinishSemaphoreInstance();
	private MySemaphore pitSemaphore;

	private boolean barriersOn = true;
	private static int startToFinishLength;
	
	/**
	 * If doPitStop is true, MyPitSemaphore instance is set to racers variable
	 * pitSemaphore. Depending on pitSemaphore, racer will/will not consider
	 * barriers as a obstacle.
	 * 
	 * @param doPitStop     if true, racer will stop at the barrier pit stop.
	 * @param carNumber     - racers name.
	 * @param racerListener
	 */
	public Racer(boolean doPitStop, CarNumber carNumber, RacerListener racerListener) {
		if (doPitStop) {
			pitSemaphore = MyPitSemaphore.getPitSemaphoreInstance();
		}
		this.carNumber = carNumber;
		this.racerListener = racerListener;
	}

	/**
	 * When run method is called, the racer thread will calculate distance until the
	 * thread is stopped.
	 */
	public void run() {
		while (true) {
			if (barriersOn) {
				raceWithBarriersOn();
			} else {
				raceWithBarriersOff();
			}
		}
	}

	/**
	 * Use this method to set start to finish length.
	 * 
	 * @param startToFinishLength
	 */
	public static void setLenght(int startToFinishLength) {
		Racer.startToFinishLength = startToFinishLength;
	}

	/**
	 * Sets barriers to true or false. This will be considered when racer is on the
	 * start position.
	 * 
	 * @param barriersOnOff
	 */
	public void setBarriers(boolean barriersOnOff) {
		this.barriersOn = barriersOnOff;
	}

	/*
	 * When racer comes to the barrier, it will stop and wait for other racers to
	 * come to the same barrier. Only when all racers are at the barrier line, the
	 * calculations for each racer can continue.
	 */
	private void raceWithBarriersOn() {
		startSemaphore.stop();
		if (pitSemaphore != null) {
			raceToFromPit();
			pitSemaphore.stop();
			raceToFromPit();
		} else {
			raceStartToFinish();
		}
		finishSemaphore.stop();
		raceFinishToStart();
	}

	/*
	 * Racers will go around the track changing their speed when they pass start and
	 * stop line.
	 */
	private void raceWithBarriersOff() {
		raceStartToFinish();
		raceFinishToStart();
	}

	/*
	 * This method calls method race to calculate distance for start to finish
	 * length. It uses parameter startToFinishLength.
	 */
	private void raceStartToFinish() {
		race(6, 12, startToFinishLength);
	}

	/*
	 * This method calls method race to calculate distance for start to pit, or pit
	 * to finish length. It uses half size of startToFinishLength parameter.
	 */
	private void raceToFromPit() {
		race(3, 6, startToFinishLength / 2);
	}

	/*
	 * This method calls method race to calculate distance for finish to start
	 * length. It is the same method as raceToFromPit(), only names are different so
	 * that code would be clear.
	 */
	private void raceFinishToStart() {
		race(3, 6, startToFinishLength / 2);
	}

	/*
	 * First of all, distance is calculated for given length by method
	 * countTimeForLength(int, int, int). After that, for every loop in while loop,
	 * length will be decremented for distance and racer thread will sleep for 100
	 * milliseconds. When length is smaller or equal to the zero, the racer is
	 * traveled the whole length.
	 * 
	 * parameter : minSeconds that racer can drive through the distance parameter :
	 * maxSeconds that racer can drive through the distance parameter : length - the
	 * distance that racer need to go through
	 */
	private void race(int minSeconds, int maxSeconds, int length) {
		countTimeForLength(minSeconds, maxSeconds, length);

		while (length > 0) {
			if (length >= distance) {
				racerListener.racerPositionChanged(distance, carNumber);
				length -= distance;
			} else {
				racerListener.racerPositionChanged(length, carNumber);
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}

	/*
	 * This method will count the time in which racer need to go from one point of
	 * the track to the other. That time need to be between minSeconds and
	 * maxSeconds. When correct time is found, method countDistance is called to set
	 * variable time and the loop is finished.
	 * 
	 * parameter : minSeconds that racer can drive through the distance parameter :
	 * maxSeconds that racer can drive through the distance parameter : length - the
	 * distance that racer need to go through
	 */
	private void countTimeForLength(int minSeconds, int maxSeconds, int length) {
		while (true) {
			double time = length / (Math.random() * 200);
			if (time > minSeconds && time < maxSeconds) {
				distance = countDistance(time, length);
				break;
			}
		}
	}

	/*
	 * parameter : time for racer to go through given length parameter : length
	 * 
	 * return : distance which racer need to drive in 100 milliseconds.
	 */
	private int countDistance(double time, int length) {
		return (int) Math.round(length / (time * 10));
	}
}
