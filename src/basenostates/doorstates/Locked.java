package basenostates.doorstates;

import basenostates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the locked state of a door.
 * Defines behavior for actions when the door is locked.
 */
public class Locked extends DoorState {
  private static final Logger logger = LoggerFactory.getLogger(Locked.class); // Logger instance

  /**
   * Constructor to initialize the locked state of the door.
   */
  public Locked(Door door, String stateName) {
    super(door, stateName);
  }

  @Override
  public void open() {
    logger.error("Door is locked, you can't open it.");
  }

  @Override
  public void close() {
    logger.info("Door is locked, it should already be closed.");
    door.setClosed(true); // just in case
  }

  @Override
  public void lock() {
    logger.warn("Tried to lock an already locked door.");
  }

  @Override
  public void unlock() {
    door.setState(new Unlocked(door, State.UNLOCKED));
    logger.info("Door unlocked.");
  }

  @Override
  public void unlockShortly() {
    door.setState(new UnlockShortly(door, State.UNLOCK_SHORTLY));
    logger.info("Door unlocked shortly.");
  }
}
