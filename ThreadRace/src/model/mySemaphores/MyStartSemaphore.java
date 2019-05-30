package model.mySemaphores;

/**
 * This is a singleton class, it extends class MySemaphore.
 * 
 * @author Igor Stojanovic
 * @see {@link #getStartSemaphoreInstance()}
 * @see {@link MySemaphore}
 */
public class MyStartSemaphore extends MySemaphore {
	
	//This class instance
	private static MyStartSemaphore myStartSemaphore;
	
	//Singleton constructor
	private MyStartSemaphore() {
	}
	
	/**
	 * @return one and only object of this class
	 */
	public static MyStartSemaphore getStartSemaphoreInstance() {
		if(myStartSemaphore == null) {
			myStartSemaphore = new MyStartSemaphore();
		}
		return myStartSemaphore;
	}
	
}
