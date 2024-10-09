package baseNoStates.DoorStates;

import baseNoStates.requests.RequestReader;
import baseNoStates.*;

public abstract class DoorState {
  protected Door door;
  protected State name;

  public DoorState(Door door) {
    this.door = door;
  }

  public State getStateName() {
    return name;
  }

  public void open() {}
  public void close() {}
  public void lock() {}
  public void unlock() {}
}
