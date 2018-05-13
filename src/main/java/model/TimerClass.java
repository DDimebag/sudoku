package model;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.text.Text;

/**
 * Used to control the timer.
 *
 */
public class TimerClass {
	private int secondsPassed = 0;

	public Timer timer = new Timer();
	public Text timerText = new Text();
	public TimerTask task = new TimerTask() {
		public void run() {
			secondsPassed++;
			timerText.setText(Integer.toString(secondsPassed));
			System.out.println(secondsPassed);
		}
	};

	/**
	 * Schedules the task for repeated execution
	 */
	public void startTimer() {
		timer.scheduleAtFixedRate(task, 1000, 1000);
	}

	/**
	 * Returns the seconds passed since the start of the timer.
	 * 
	 * @return seconds passed in int
	 */
	public int getSecondsPassed() {
		return this.secondsPassed;
	}
}
