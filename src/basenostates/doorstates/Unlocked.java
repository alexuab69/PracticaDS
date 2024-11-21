package basenostates.doorstates;

import basenostates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the "unlocked" state of a door, allowing it to be opened or closed
 * freely, and enabling it to transition to the locked state if closed.
 */
public class Unlocked extends DoorState {
  private static final Logger logger = LoggerFactory.getLogger(Unlocked.class); // Logger instance

  /**
   * Constructs an Unlocked state for the specified door.
   */
  public Unlocked(Door door, String stateName) {
    super(door, stateName);
  }

  @Override
  public void open() {
    // if door is unlocked, you can open it
    if (door.isClosed()) {
      door.setClosed(false);
      logger.info("Door opened.");
    } else {
      logger.info("Door is already opened.");
    }
  }

  @Override
  public void close() {
    // if door is unlocked, you can close it
    if (!door.isClosed()) {
      door.setClosed(true);
      logger.info("Door closed.");
    } else {
      logger.info("Door is already closed");
    }
  }

  @Override
  public void lock() {
    // if door is unlocked, you can lock it
    // if door is open, can't lock it
    if (!door.isClosed()) {
      logger.error("Door is open, you can't lock it until it's closed");
    } else {
      door.setState(new Locked(door, State.LOCKED));
      logger.info("Door locked");
    }
  }

  @Override
  public void unlock() {
    // if door is unlocked, you can unlock it
    logger.warn("Tried to unlock an already unlocked door");
  }

  @Override
  public void unlockShortly() {
    // if door is unlocked, it's not necessary to unlock it shortly
    logger.info("Door is already unlocked, it's not necessary to unlock it shortly");
  }

}
