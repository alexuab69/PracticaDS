package baseNoStates.DoorStates;

import baseNoStates.Door;

public class UnlockShortly extends DoorState {

  public UnlockShortly(Door door, String stateName) {
    super(door, stateName);
  }

  @Override
  public void open() {
    // if door is unlocked shortly, you can open it
    if (door.isClosed()) {
      door.setClosed(false);
      System.out.println("Door opened");
    } else {
      System.out.println("Door is already open");
    }
  }

  @Override
  public void close() {
    // if door is unlocked shortly, you can close it
    if (!door.isClosed()) {
      door.setClosed(true);
      System.out.println("Door closed");
    } else {
      System.out.println("Door is already closed");
    }
  }

  @Override
  public void lock() {
    // if door is open, then is propped
    if (!door.isClosed()) {
      System.out.println("After 10 seconds, the door still open. Set the door as propped");
      this.name = State.PROPPED;
    } else {
      // if door is unlocked shortly, you can lock it
      door.setState(new Locked(door, State.LOCKED));
      System.out.println("Door locked");
    }
  }

  @Override
  public void unlock() {
    // if door is unlocked shortly, you can unlock it
    door.setState(new Unlocked(door, State.UNLOCKED));
    System.out.println("Door unlocked");
  }

  @Override
  public void unlock_shortly() {
    // if door is unlocked shortly, you can't unlock it shortly
    System.out.println("Door is already unlocked shortly");
  }

}
