package baseNoStates;

import baseNoStates.DoorStates.Actions;
import baseNoStates.DoorStates.DoorState;
import baseNoStates.DoorStates.State;
import baseNoStates.DoorStates.Unlocked;
import baseNoStates.partitions.Area;
import baseNoStates.partitions.Space;
import baseNoStates.requests.RequestReader;
import org.json.JSONObject;

// Represents a door within a building, managing its state, connected areas, and processing requests
public class Door extends Area {
  private final String id;      // Unique identifier for the door
  private DoorState state;      // Current state of the door (e.g., locked, unlocked)
  private boolean closed;       // Indicates if the door is physically closed
  private Area fromSpace;       // Area on one side of the door
  private Area toSpace;         // Area on the other side of the door

  // Constructor initializing the door's ID and setting its initial state as unlocked and closed
  public Door(String id) {
    this.id = id;
    this.state = new Unlocked(this, State.UNLOCKED);
    closed = true;
  }

  // Setter for closed status of the door
  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  // Setter to update the door's current state
  public void setState(DoorState state) {
    this.state = state;
  }

  // Setter to specify the area on one side of the door
  public void setFromSpace(Area fromSpace) {
    this.fromSpace = fromSpace;
  }

  // Setter to specify the area on the other side of the door
  public void setToSpace(Area toSpace) {
    this.toSpace = toSpace;
  }

  // Returns if the door is closed
  public boolean isClosed() { return closed; }

  // Returns the ID of the door
  @Override
  public String getId() { return id; }

  // Returns the area on one side of the door
  public Area getFromSpace() { return fromSpace; }

  // Returns the area on the other side of the door
  public Area getToSpace() { return toSpace; }

  // Returns the current state of the door
  public DoorState getState() { return state; }

  // Processes the request made to the door
  public void processRequest(RequestReader request) {
    // Only process the request if it is authorized; otherwise, print "not authorized"
    if (request.isAuthorized()) {
      String action = request.getAction(); // Retrieve the action from the request
      doAction(action);                    // Perform the action on the door
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName()); // Set the door's current state in the request
  }

  // Executes an action on the door based on the provided action type
  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        state.open();
        break;
      case Actions.CLOSE:
        state.close();
        break;
      case Actions.LOCK:
        state.lock();
        break;
      case Actions.UNLOCK:
        state.unlock();
        break;
      case Actions.UNLOCK_SHORTLY:
        state.unlock_shortly();
        break;
      default:
        System.out.println("action not recognized"); // Prints an error if the action is unrecognized
    }
  }

  // Returns the name of the door's current state as a string
  public String getStateName() {
    return state.getDoorStateName();
  }

  // Converts the door's properties to a string for logging and debugging
  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  // Converts the door's properties to a JSON object
  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }

  // This method is overridden but does not perform any operations for doors
  public void createBuilding(Area... areas) {
    // do nothing
  }
}
