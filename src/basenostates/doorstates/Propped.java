package basenostates.doorstates;

import basenostates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the "propped" state of a door, where the door is partially open.
 */
public class Propped extends DoorState {
  private static final Logger logger = LoggerFactory.getLogger(Propped.class); // Logger instance

  /**
   * Constructor to initialize the propped state of the door.
   */
  public Propped(Door door, String stateName) {
    super(door, stateName);
  }

  @Override
  public void open() {
    // if door is propped, it should be open
    logger.info("Door is propped, it should already be open.");
    door.setClosed(false); // Just in case
  }

  @Override
  public void close() {
    // if door is propped, you can close it and it will be locked
    door.setClosed(true);
    logger.info("Door closed.");
    door.setState(new Locked(door, State.LOCKED));
  }

  @Override
  public void lock() {
    // if door is propped, you can't lock it
    logger.error("Cannot lock the door while it is propped.");
  }

  @Override
  public void unlock() {
    // if door is propped, you can't unlock it
    logger.error("Cannot unlock the door while it is propped. First, close it.");
  }

  @Override
  public void unlockShortly() {
    // if door is propped, you can't unlock it shortly
    logger.error("Cannot unlock the door shortly while it is propped. First, close it.");
  }
}
