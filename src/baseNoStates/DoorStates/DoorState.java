package baseNoStates.DoorStates;

import baseNoStates.*;

import java.time.LocalDateTime;

// Abstract class representing the base state of a door
public abstract class DoorState {
  // Reference to the Door object associated with this state
  protected Door door;
  // Name of the state, used primarily for display purposes
  protected String name;

  // Constructor initializes the Door object and state name
  public DoorState(Door door, String name) {
    this.door = door;
    this.name = name;
  }

  // Method to retrieve the name of the current door state
  public String getDoorStateName() { return name; }

  // Method for opening the door, left unimplemented for specific states to define
  public void open() {}

  // Method for closing the door, left unimplemented for specific states to define
  public void close() {}

  // Method for locking the door, left unimplemented for specific states to define
  public void lock() {}

  // Method for unlocking the door, left unimplemented for specific states to define
  public void unlock() {}

  // Method for unlocking the door temporarily, left unimplemented for specific states to define
  public void unlock_shortly() {}
}
