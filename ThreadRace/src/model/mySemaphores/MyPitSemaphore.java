package model.mySemaphores;

/**
 * This is a singleton class, it extends class MySemaphore.
 * 
 * @author Igor Stojanovic
 * @see {@link #getPitSemaphoreInstance()}
 * @see {@link MySemaphore}
 */
public class MyPitSemaphore extends MySemaphore {
	
	//This class instance
	private static MyPitSemaphore myPitSemaphore;

	//Singleton constructor
	private MyPitSemaphore() {
	}

	/**
	 * @return one and only object of this class
	 */
	public static MyPitSemaphore getPitSemaphoreInstance() {
		if (myPitSemaphore == null) {
			myPitSemaphore = new MyPitSemaphore();
		}
		return myPitSemaphore;
	}
}
