package basenostates.doorstates;

import basenostates.Clock;
import basenostates.Door;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a door state where the door is unlocked for a short duration.
 * After 10 seconds, the door transitions to either the locked state (if closed)
 * or the propped state (if open).
 */
public class UnlockShortly extends DoorState implements Observer {
  private static final Logger logger = LoggerFactory.getLogger(UnlockShortly.class);
  // Logger instance

  private final LocalDateTime now;
  private final Clock timer;

  /**
   * Constructs an UnlockShortly state for the door.
   */
  public UnlockShortly(Door door, String stateName) {
    super(door, stateName); // save the actual time to compare it with the time of the timer
    timer = Clock.getInstance();
    now = LocalDateTime.now();
    // start a timer of 10 seconds, after that, door will be locked again
    timer.start();
    // tell the observable who is the observer
    timer.addObserver(this);
  }

  @Override
  public void open() {
    // if door is unlocked shortly, you can open it
    if (door.isClosed()) {
      door.setClosed(false);
      logger.info("Door opened");
    } else {
      logger.info("Door is already open");
    }
  }

  @Override
  public void close() {
    // if door is unlocked shortly, you can close it
    if (!door.isClosed()) {
      door.setClosed(true);
      logger.info("Door closed");
    } else {
      logger.info("Door is already closed");
    }
  }

  @Override
  public void lock() {
    // if door is open, then is propped
    if (!door.isClosed()) {
      logger.error("Tried to lock an opened door");
    } else {
      // if door is unlocked shortly, you can lock it
      door.setState(new Locked(door, State.LOCKED));
      logger.info("Door locked");
    }
  }

  @Override
  public void unlock() {
    // if door is unlocked shortly, you can unlock it
    door.setState(new Unlocked(door, State.UNLOCKED));
    logger.info("Door unlocked");
  }

  @Override
  public void unlockShortly() {
    // if door is unlocked shortly, you can't unlock it shortly
    logger.warn("Tried to unlock shortly an already unlocked shortly door");
  }

  @Override
  public void update(Observable o, Object arg) {
    if (o instanceof Clock) {
      LocalDateTime time = (LocalDateTime) arg;
      // If 10 seconds have passed, the door will be locked again
      // but if the door is open, then is propped
      if (now.plusSeconds(10).isBefore(time)) {
        timer.stop(); // stop the timer if 10 seconds have passed
        if (!door.isClosed()) {
          logger.info("10 seconds have passed and the door is still open. "
              + "Door state set to propped");
          door.setState(new Propped(door, State.PROPPED));
        } else {
          door.setState(new Locked(door, State.LOCKED));
          logger.info("10 seconds have passed and the door is closed. Door locked");
        }
      }
      // do nothing
    }
  }

}