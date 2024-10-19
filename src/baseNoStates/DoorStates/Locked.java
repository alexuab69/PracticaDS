package baseNoStates.DoorStates;

import baseNoStates.Clock;
import baseNoStates.Door;

import java.time.LocalDateTime;
import java.util.Observer;

public class Locked extends DoorState{

  public Locked(Door door, String stateName) {
    super(door, stateName);
  }

  @Override
  public void open() {
    // if door is locked, you can't open it
    System.out.println("Door is locked, you can't open it");
  }

  @Override
  public void close() {
    // if door is locked, you can't close it
    System.out.println("Door is locked, it should be already closed");
    door.setClosed(true); // just in case
  }

  @Override
  public void lock() {
    // if door is locked, you can't lock it
    System.out.println("Door is already locked");
  }

  @Override
  public void unlock() {
    // if door is locked, you can unlock it
    door.setState(new Unlocked(door, State.UNLOCKED));
    System.out.println("Door unlocked");
  }

  @Override
  public void unlock_shortly() {
    // if door is locked, you can unlock it shortly
    door.setState(new UnlockShortly(door, State.UNLOCK_SHORTLY));
    System.out.println("Door unlocked shortly");
  }

}
