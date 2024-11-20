package basenostates.doorstates;

import basenostates.Door;

/**
 * Represents the "propped" state of a door, where the door is partially open.
 */
public class Propped extends DoorState {

  /**
   * Constructor to initialize the propped state of the door.
   */
  public Propped(Door door, String stateName) {
    super(door, stateName);
  }

  @Override
  public void open() {
    // if door is propped, it should be open
    System.out.println("Door is propped, it should be already open");
    door.setClosed(false); // just in case
  }

  @Override
  public void close() {
    // if door is propped, you can close it and it will be locked
    door.setClosed(true);
    System.out.println("Door closed");
    door.setState(new Locked(door, State.LOCKED));
  }

  @Override
  public void lock() {
    // if door is propped, you can't lock it
    System.out.println("Door is propped, you can't lock it");
  }

  @Override
  public void unlock() {
    // if door is propped, you can't unlock it
    System.out.println("Door is propped, you can't unlock it. First, close it");
  }

  @Override
  public void unlockShortly() {
    // if door is propped, you can't unlock it shortly
    System.out.println("Door is propped, you can't unlock it shortly. First, close it");
  }

}
