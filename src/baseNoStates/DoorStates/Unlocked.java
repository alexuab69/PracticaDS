package baseNoStates.DoorStates;

import baseNoStates.Door;

public class Unlocked extends DoorState{

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
    door.setState(new Locked(door, State.LOCKED));
    System.out.println("Door locked");
  }

  @Override
  public void unlock() {
    // if door is unlocked, you can unlock it
    System.out.println("Door is already unlocked");
  }

  @Override
  public void unlock_shortly() {
    // if door is unlocked, it's not necessary to unlock it shortly
    System.out.println("Door is already unlocked");
  }

}
