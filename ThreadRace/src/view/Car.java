package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * This class is model for all cars inside this GUI. The images are size 100x60
 * pixels.
 * 
 * @see {@link #Car(ImageIcon)}
 * @see {@link #getX()}
 * @see {@link #move(int)}
 * @see {@link #setCarOnScreen()}
 * @see {@link #setX(int)}
 * @see {@link #updateCarPosition()}
 * @author Igor Stojanovic
 *
 */
public class Car {
	private JLabel car;
	private int carHeight;
	private int carWidth;

	/**
	 * Constructs JLabel with given image. For good look and feel image should be 100x60 pixels
	 * @param image that will be set as JLabel image icon.
	 */
	public Car(ImageIcon image) {
		carHeight = image.getIconHeight();
		carWidth = image.getIconWidth();
		car = new JLabel(image);
	}

	/**
	 * @return X position on screen of this object.
	 */
	public int getX() {
		return (int) car.getLocation().getX();
	}

	/**
	 * set start position on the screen.
	 * 
	 * @param x position
	 * @param y position
	 */
	public void setStartPosition(int x, int y) {
		car.setBounds(x, y, carWidth, carHeight);
		updateCarPosition();
	}

	/**
	 * Sets x position of the car on the screen. Y position will stay the same.
	 * 
	 * @param x position
	 */
	public void move(int xPosition) {
		car.setLocation(xPosition, (int) car.getLocation().getY());
		updateCarPosition();
	}

	/**
	 * @return an JLabel object representing car
	 */
	public JLabel setCarOnScreen() {
		return car;
	}

	/**
	 * This method update car position when it is called. This method should be
	 * called every time when position of the car is changed.
	 */
	public void updateCarPosition() {
		car.updateUI();
	}
}
