package basenostates.doorstates;

import basenostates.Door;

/**
 * Represents the "unlocked" state of a door, allowing it to be opened or closed
 * freely, and enabling it to transition to the locked state if closed.
 */
public class Unlocked extends DoorState {
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
      System.out.println("Door opened");
    } else {
      System.out.println("Door is already open");
    }
  }

  @Override
  public void close() {
    // if door is unlocked, you can close it
    if (!door.isClosed()) {
      door.setClosed(true);
      System.out.println("Door closed");
    } else {
      System.out.println("Door is already closed");
    }
  }

  @Override
  public void lock() {
    // if door is unlocked, you can lock it
    // if door is open, can't lock it
    if (!door.isClosed()) {
      System.out.println("Door is open, you can't lock it until it's closed");
    } else {
      door.setState(new Locked(door, State.LOCKED));
      System.out.println("Door locked");
    }
  }

  @Override
  public void unlock() {
    // if door is unlocked, you can unlock it
    System.out.println("Door is already unlocked");
  }

  @Override
  public void unlockShortly() {
    // if door is unlocked, it's not necessary to unlock it shortly
    System.out.println("Door is already unlocked");
  }

}
