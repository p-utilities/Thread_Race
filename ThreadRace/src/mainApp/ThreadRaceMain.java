package mainApp;

import java.awt.EventQueue;

import controller.RaceController;

public class ThreadRaceMain {

	// This is the start of the ThreadRace GUI
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RaceController controller = new RaceController();
					controller.initFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
