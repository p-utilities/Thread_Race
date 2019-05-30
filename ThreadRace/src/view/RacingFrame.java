package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CarNumber;
import controller.FrameListener;

/**
 * This class is main frame for GUI. Calling default constructor, the frame is
 * set and ready to be initialized.
 * 
 * @see {@link #RacingFrame()}
 * @see {@link Car}
 * @author Igor Stojanovic
 *
 */
public class RacingFrame extends JFrame {

	private JPanel contentPane;
	private Car carOne;
	private Car carTwo;
	private Car carThree;
	private Car carFour;
	private Car carFive;
	private JButton barriersButton;
	private JButton raceButton;
	private JLabel startLineLabel;
	private JLabel stopLineLabel;
	private JLabel pitStopLineLabel;

	private ImageIcon barriersOnIcon = new ImageIcon(RacingFrame.class.getResource("/CarsImages/BarriersOnButton.png"));
	private ImageIcon barriersOffIcon = new ImageIcon(
			RacingFrame.class.getResource("/CarsImages/BarriersOfButton.png"));
	private ImageIcon stopButtonIcon = new ImageIcon(RacingFrame.class.getResource("/CarsImages/StopButton.png"));
	private ImageIcon raceButtonIcon = new ImageIcon(RacingFrame.class.getResource("/CarsImages/RaceButton.png"));

	private boolean race = true;
	private boolean barriersOn = true;

	private FrameListener frameListener;

	/**
	 * Sets properties for this JFrame and add contents to this JFrame.
	 */
	public RacingFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		addCars();
		setPolPositions();
		addLines();
		addButtons();
		addBackground();
		setRaceButtonListener();
		setBarriersButtonListener();
	}

	/*
	 * This method is called when racers need to stop. It sets car objects on below
	 * coordinates.
	 */
	private void setPolPositions() {
		carOne.setStartPosition(50, 175);
		carTwo.setStartPosition(50, 240);
		carThree.setStartPosition(50, 308);
		carFour.setStartPosition(35, 370);
		carFive.setStartPosition(40, 435);
	}

	/**
	 * @return number of pixels between start line and stop line.
	 */
	public int getLengthStartToFinish() {
		return (int) stopLineLabel.getLocation().getX() - (int) startLineLabel.getLocation().getX();
	}

	/**
	 * Depending on CarNumber, the car object will move along the track for the
	 * given distance every time this method is called.
	 * 
	 * @see {@link controller.CarNumber}
	 * @see {@link controller.RaceController#racerPositionChanged(int, CarNumber)}
	 * @param carNumber which will move along the track.
	 * @param distance  - for how much will car move.
	 */
	public void setCarPosition(CarNumber carNumber, int distance) {
		switch (carNumber) {
		case ONE:
			carPosition(carOne, distance);
			break;

		case TWO:
			carPosition(carTwo, distance);
			break;

		case THREE:
			carPosition(carThree, distance);
			break;

		case FOUR:
			carPosition(carFour, distance);
			break;

		case FIVE:
			carPosition(carFive, distance);
			break;

		default:
			throw new NullPointerException("only five cars for now");
		}
	}

	/*
	 * This method moves object of class Car(JLabel) by the distance that is sent by
	 * racer.
	 */
	private void carPosition(Car car, int distance) {
		if (car.getX() >= 800) {
			car.move(-90);
		} else {
			car.move(car.getX() + distance);
		}
	}

	/**
	 * Sets frame listener for RacingFrame.
	 * 
	 * @see {@link RacingFrame}
	 * @see {@link controller.FrameListener}
	 * @param frameListener
	 */
	public void setFrameButtonListener(FrameListener frameListener) {
		this.frameListener = frameListener;
	}

	/*
	 * This method sets barrier button listener. When button is clicked, the image
	 * of button and race variable is changed.
	 */
	private void setRaceButtonListener() {
		raceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (race) {
					frameListener.startRace();
					raceButton.setIcon(stopButtonIcon);
					race = false;
				} else {
					setPolPositions();
					frameListener.stopRace();
					raceButton.setIcon(raceButtonIcon);
					race = true;
				}
			}
		});
	}

	/*
	 * This method sets barrier button listener. When button is clicked, the image
	 * of button and barriersOn variable is changed.
	 */
	private void setBarriersButtonListener() {
		barriersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (barriersOn) {
					pitStopLineLabel.setVisible(false);
					barriersButton.setIcon(barriersOnIcon);
					frameListener.barriersOff();
					barriersOn = false;
				} else {
					pitStopLineLabel.setVisible(true);
					barriersButton.setIcon(barriersOffIcon);
					frameListener.barriersOn();
					barriersOn = true;
				}
			}
		});
	}

	// Adds cars on the roadway background.
	private void addCars() {
		carOne = new Car(new ImageIcon(RacingFrame.class.getResource("/CarsImages/car1.png")));
		contentPane.add(carOne.setCarOnScreen());

		carTwo = new Car(new ImageIcon(RacingFrame.class.getResource("/CarsImages/car22.png")));
		contentPane.add(carTwo.setCarOnScreen());

		carThree = new Car(new ImageIcon(RacingFrame.class.getResource("/CarsImages/car3.png")));
		contentPane.add(carThree.setCarOnScreen());

		carFour = new Car(new ImageIcon(RacingFrame.class.getResource("/CarsImages/car4.png")));
		contentPane.add(carFour.setCarOnScreen());

		carFive = new Car(new ImageIcon(RacingFrame.class.getResource("/CarsImages/car5.png")));
		contentPane.add(carFive.setCarOnScreen());
	}

	// Adds and sets line barriers over the roadway background.
	private void addLines() {
		startLineLabel = new JLabel(new ImageIcon(RacingFrame.class.getResource("/CarsImages/StartLine.png")));
		startLineLabel.setBounds(150, 175, 20, 320);
		contentPane.add(startLineLabel);

		pitStopLineLabel = new JLabel(new ImageIcon(RacingFrame.class.getResource("/CarsImages/PitStopLine.png")));
		pitStopLineLabel.setBounds(450, 310, 20, 180);
		contentPane.add(pitStopLineLabel);

		stopLineLabel = new JLabel(new ImageIcon(RacingFrame.class.getResource("/CarsImages/StopLine.png")));
		stopLineLabel.setBounds(750, 175, 20, 320);
		contentPane.add(stopLineLabel);
	}

	// Adds and sets start and barrier button.
	private void addButtons() {
		raceButton = new JButton("");
		raceButton.setIcon(raceButtonIcon);
		raceButton.setBounds(282, 155, 90, 20);
		contentPane.add(raceButton);

		barriersButton = new JButton("");
		barriersButton.setIcon(barriersOnIcon);
		barriersButton.setBounds(382, 155, 195, 20);
		contentPane.add(barriersButton);
	}

	/*
	 * Adds background for header and roadway background.
	 */
	private void addBackground() {
		JLabel playgroundLabel = new JLabel(
				new ImageIcon(RacingFrame.class.getResource("/CarsImages/RacingTrackPng.png")));
		playgroundLabel.setBounds(0, 139, 785, 386);
		contentPane.add(playgroundLabel);

		JLabel headerLabel = new JLabel(new ImageIcon(RacingFrame.class.getResource("/CarsImages/GameHeader2.png")));
		headerLabel.setBounds(0, 0, 785, 152);
		contentPane.add(headerLabel);
	}
}
