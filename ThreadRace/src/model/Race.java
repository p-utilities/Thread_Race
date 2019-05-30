package model;

import controller.CarNumber;
import controller.RacerListener;
import model.mySemaphores.MyFinishSemaphore;
import model.mySemaphores.MyPitSemaphore;
import model.mySemaphores.MyStartSemaphore;

/**
 * Object of this class controls construction of racer objects and threads for
 * that racers. For this GUI Racer class should be instantiated through this
 * class only.
 * 
 * @see {{@link #Race(RacerListener, int)}
 * @see {@link Racer}
 * @author Igor Stojanovic
 *
 */
public class Race {

	// Five racers, one for every racer labels.
	private Racer racerOne;
	private Racer racerTwo;
	private Racer racerThree;
	private Racer racerFour;
	private Racer racerFive;

	/*
	 * Class Racer implements Runnable, with that, every Racer object need one
	 * Thread.
	 */
	private Thread threadRacerOne;
	private Thread threadRacerTwo;
	private Thread threadRacerThree;
	private Thread threadRacerFour;
	private Thread threadRacerFive;

	private RacerListener racerListener;
	private int startToFinishLength;

	/**
	 * Constructor for this class. It sets semaphores and creates five racers.
	 * 
	 * @param racerListener       which is passed to every racer.
	 * @param startToFinishLength with which racer will calculate how much racer
	 *                            label should move.
	 */
	public Race(RacerListener racerListener, int startToFinishLength) {
		this.racerListener = racerListener;
		this.startToFinishLength = startToFinishLength;
		// setSemaphores();
		createRacers();
	}

	// Creates five racers and sets length.
	private void createRacers() {
		racerOne = new Racer(false, CarNumber.ONE, racerListener);
		racerTwo = new Racer(false, CarNumber.TWO, racerListener);
		racerThree = new Racer(true, CarNumber.THREE, racerListener);
		racerFour = new Racer(true, CarNumber.FOUR, racerListener);
		racerFive = new Racer(true, CarNumber.FIVE, racerListener);

		// static method is called to set all racers same startToFinishLength
		Racer.setLenght(startToFinishLength);
	}

	/*
	 * Through static method gets objects of semaphores and sets how much every
	 * semaphore can stop/release cars.
	 */
	private void setSemaphores() {
		MyStartSemaphore.getStartSemaphoreInstance().setNumberOfThreads(5);
		MyFinishSemaphore.getFinishSemaphoreInstance().setNumberOfThreads(5);
		MyPitSemaphore.getPitSemaphoreInstance().setNumberOfThreads(3);
	}

	// Calls MySemaphore method clearSemaphore to clear internal variables.
	// For more info about MySemaphore class : {@link
	// model.mySemaphores.MySemaphore}
	private void clearSemaphores() {
		MyStartSemaphore.getStartSemaphoreInstance().clearSemaphore();
		MyFinishSemaphore.getFinishSemaphoreInstance().clearSemaphore();
		MyPitSemaphore.getPitSemaphoreInstance().clearSemaphore();
	}

	// Creates threads for every racer.
	private void createThreadsForRacers() {
		threadRacerOne = new Thread(racerOne);
		threadRacerTwo = new Thread(racerTwo);
		threadRacerThree = new Thread(racerThree);
		threadRacerFour = new Thread(racerFour);
		threadRacerFive = new Thread(racerFive);
	}

	private void startRacerThreads() {
		threadRacerOne.start();
		threadRacerTwo.start();
		threadRacerThree.start();
		threadRacerFour.start();
		threadRacerFive.start();
	}

	/**
	 * When this method is called, racer threads are created and started. After that
	 * every racer begin to send informations of car movement to the controller.
	 */
	public void startRace() {
		setSemaphores();
		createThreadsForRacers();
		startRacerThreads();
	}

	/**
	 * This class calls deprecated thread method stop to stop threads.
	 */

	/*
	 * I tried to use other ways of stopping threads but every time I used blinker
	 * or other methods, problem occurs in semaphores. Threads need to be stopped
	 * and semaphores should be cleared and but threads that are already inside
	 * Thread.sleep, doesn't wake up. When I use interrupt method, semaphores are
	 * not returning to the starting state. Help is needed
	 */
	@SuppressWarnings("deprecation")
	public void stopRace() {
		clearSemaphores();
		threadRacerOne.stop();
		threadRacerTwo.stop();
		threadRacerThree.stop();
		threadRacerFour.stop();
		threadRacerFive.stop();
	}

	/**
	 * If parameter is set to true, barriers are set to visible(to the racers) and
	 * cars need to stop when they come across barriers. If parameter is set to
	 * false, barriers are set to not visible(to the racers) and cars will pass by
	 * barriers and not stop.
	 * 
	 * @param barriersOnOff true or false variable
	 */
	public void setRacerBarriers(boolean barriersOnOff) {
		racerOne.setBarriers(barriersOnOff);
		racerTwo.setBarriers(barriersOnOff);
		racerThree.setBarriers(barriersOnOff);
		racerFour.setBarriers(barriersOnOff);
		racerFive.setBarriers(barriersOnOff);
	}
}
