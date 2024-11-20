package basenostates;


import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Clock class represents a timer that notifies its observers with the current date and time
 * at a fixed interval defined by the period.
 * It extends the Observable class to provide an observer pattern implementation.
 */
public class Clock extends Observable {
  private LocalDateTime date;
  private final Timer timer;
  private final int period; // seconds

  /**
   * Constructs a Clock with a specific update period in seconds.
   * period the interval (in seconds) at which the clock notifies its observers.
   */
  public Clock(int period) {
    this.period = period;
    timer = new Timer();
  }

  /**
   * Starts the clock. The clock will notify its observers at the fixed interval
   * with the current date and time.
   */
  public void start() {
    TimerTask repeatedTask = new TimerTask() {
      public void run() {
          // Instance of anonymous class
          date = LocalDateTime.now();
          System.out.println("run() executed at " + date);
          setChanged(); // set changed flag to true
          notifyObservers(date); // notify all observers giving them the date
      }
    };
    timer.scheduleAtFixedRate(repeatedTask, 0, 1000L * period);
  }

  /**
   * Stops the clock, cancelling any ongoing tasks.
   */
  public void stop() {
    timer.cancel();
  }

}

