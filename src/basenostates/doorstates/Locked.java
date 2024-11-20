package basenostates.doorstates;

import basenostates.Door;

/**
 * Represents the locked state of a door.
 * Defines behavior for actions when the door is locked.
 */
public class Locked extends DoorState {

  /**
   * Constructor to initialize the locked state of the door.
   */
  public Locked(Door door, String stateName) {
    super(door, stateName);
  }

  @Override
  public void open() {
    System.out.println("Door is locked, you can't open it");
  }

  @Override
  public void close() {
    System.out.println("Door is locked, it should be already closed");
    door.setClosed(true); // just in case
  }

  @Override
  public void lock() {
    System.out.println("Door is already locked");
  }

  @Override
  public void unlock() {
    door.setState(new Unlocked(door, State.UNLOCKED));
    System.out.println("Door unlocked");
  }

  @Override
  public void unlockShortly() {
    door.setState(new UnlockShortly(door, State.UNLOCK_SHORTLY));
    System.out.println("Door unlocked shortly");
  }
}
