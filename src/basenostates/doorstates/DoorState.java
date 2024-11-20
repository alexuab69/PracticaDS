package basenostates.doorstates;

import basenostates.Door;

/**
 * Abstract class representing the state of a door.
 * It defines the common interface for all door states,
 * allowing specific behavior for actions like open, close, lock, and unlock.
 */
public abstract class DoorState {
  protected Door door;
  protected String name;

  /**
   * Constructor initializing the door state with the associated door and state name.
   */
  public DoorState(Door door, String name) {
    this.door = door;
    this.name = name;
  }

  public String getDoorStateName() {
    return name;
  }

  /**
   * Opens the door (can be overridden by specific states).
   */
  public void open() {}

  /**
   * Closes the door (can be overridden by specific states).
   */
  public void close() {}

  /**
   * Locks the door (can be overridden by specific states).
   */
  public void lock() {}

  /**
   * Unlocks the door (can be overridden by specific states).
   */
  public void unlock() {}

  /**
   * Temporarily unlocks the door (can be overridden by specific states).
   */
  public void unlockShortly() {}
}
