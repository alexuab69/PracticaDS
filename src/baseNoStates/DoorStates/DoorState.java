package baseNoStates.DoorStates;

import baseNoStates.*;

public abstract class DoorState {
  protected Door door;
  protected String name; // Name of the state just for prints

  public DoorState(Door door, String name) {
    this.door = door;
    this.name = name;
  }
  public void update(String action) {
    switch (action) {
      case Actions.OPEN:
        open();
        break;
      case Actions.CLOSE:
        close();
        break;
      case Actions.LOCK:
        lock();
        break;
      case Actions.UNLOCK:
        unlock();
        break;
      case Actions.UNLOCK_SHORTLY:
        unlock_shortly();
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public String getDoorStateName() {return name;}

  public void open() {}
  public void close() {}
  public void lock() {}
  public void unlock() {}
  public void unlock_shortly() {}
}
