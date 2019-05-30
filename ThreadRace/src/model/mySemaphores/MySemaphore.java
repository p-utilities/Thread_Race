package model.mySemaphores;

/**
 * This abstract class is blueprint for {@link MyStartSemaphore},
 * {@link MyPitSemaphore} and {@link MyFinishSemaphore}. Those classes are
 * singleton classes. Those objects will stop every thread until all threads are
 * inside the semaphore. When the last thread come, all threads are interrupted
 * from sleep and semaphore is cleared.
 * 
 * @author Igor Stojanovic
 * @see {@link #stop()}
 * @see {@link #clearSemaphore()}
 */
public abstract class MySemaphore {
	private int numberOfThreads; // number of threads that this semaphore will count

	private int numberOfStopedThreads = 0; // number of stopped threads, this variable should always be less then
											// numberOfThreads

	/**
	 * This method sets number of threads that this semaphore will count.
	 * 
	 * @param numberOfThreads to set
	 */
	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}

	/**
	 * When this method is called by some thread, it will wait until all threads
	 * arrive or until that thread is interrupted. When the last waiting thread is
	 * in the semaphore, it will call clearSemaphore method.
	 * 
	 * @see {@link #clearSemaphore()}
	 */
	public synchronized void stop() {
		numberOfStopedThreads++;
		if (numberOfStopedThreads < numberOfThreads) {
			while (true) {
				try {
					wait();
					break;
				} catch (InterruptedException e) {
					clearSemaphore();
					System.out.println("interupted");
					break;
				}
			}
		} else {
			clearSemaphore();
		}
	}

	/**
	 * When this method is called, counter for the stopped threads is set to 0, and
	 * all waiting threads are interrupted.
	 */
	public synchronized void clearSemaphore() {
		numberOfStopedThreads = 0;
		notifyAll();
	}
}
