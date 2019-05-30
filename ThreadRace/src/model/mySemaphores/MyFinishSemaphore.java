package model.mySemaphores;

/**
 * This is a singleton class, it extends class MySemaphore.
 * 
 * @author Igor Stojanovic
 * @see {@link #getFinishSemaphoreInstance()}
 * @see {@link MySemaphore}
 */
public class MyFinishSemaphore extends MySemaphore {
	
	//This class instance
	private static MyFinishSemaphore myFinishSemaphore;

	//Singleton constructor
	private MyFinishSemaphore() {
		}

	/**
	 * @return one and only object of this class
	 */
	public static MyFinishSemaphore getFinishSemaphoreInstance() {
		if (myFinishSemaphore == null) {
			myFinishSemaphore = new MyFinishSemaphore();
		}
		return myFinishSemaphore;
	}
}
