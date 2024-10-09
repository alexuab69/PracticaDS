package baseNoStates.DoorStates;

import baseNoStates.*;

public abstract class DoorState {
  protected Door door;
  protected String name; // Name of the state just for prints

  public DoorState(Door door, String name) {
    this.door = door;
    this.name = name;
  }

  public String getDoorStateName() {return name;}

  public void open() {}
  public void close() {}
  public void lock() {}
  public void unlock() {}
  public void unlock_shortly() {}
}
