package baseNoStates;

import baseNoStates.DoorStates.Actions;
import baseNoStates.DoorStates.DoorState;
import baseNoStates.DoorStates.State;
import baseNoStates.DoorStates.Unlocked;
import baseNoStates.partitions.Area;
import baseNoStates.requests.RequestReader;
import org.json.JSONObject;

public class Door extends Area {
  private final String id;           // Unique identifier for the door
  private DoorState state;           // Current state of the door
  private boolean closed;            // Indicates if the door is closed
  private Area fromSpace;            // The area the door connects from
  private Area toSpace;              // The area the door connects to

  // Constructor initializing the door with an ID and unlocked state
  public Door(String id) {
    this.id = id;
    this.state = new Unlocked(this, State.UNLOCKED);
    closed = true; // Door is initially closed
  }

  // Setter for door closed status
  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  // Setter for door state
  public void setState(DoorState state) {
    this.state = state;
  }

  // Setter for the area the door connects from
  public void setFromSpace(Area fromSpace) {
    this.fromSpace = fromSpace;
  }

  // Setter for the area the door connects to
  public void setToSpace(Area toSpace) {
    this.toSpace = toSpace;
  }

  // Getter for door closed status
  public boolean isClosed() {
    return closed;
  }

  // Override to get the door ID
  @Override
  public String getId() {
    return id;
  }

  // Getter for the area the door connects from
  public Area getFromSpace() {
    return fromSpace;
  }

  // Getter for the area the door connects to
  public Area getToSpace() {
    return toSpace;
  }

  // Getter for the current door state
  public DoorState getState() {
    return state;
  }

  // Processes a request by checking authorization and performing the action if authorized
  public void processRequest(RequestReader request) {
    // The door processes the request since it knows its state and if it’s closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action); // Perform the specified action on the door
    } else {
      System.out.println("not authorized"); // Print message if unauthorized
    }
    request.setDoorStateName(getStateName()); // Update the request with the current door state
  }

  // Executes the specified action on the door's state
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
        System.out.println("action not recognized"); // Notify if action is unknown
    }
  }

  // Retrieves the name of the current door state
  public String getStateName() {
    return state.getDoorStateName();
  }

  // Converts the door's information to a string for logging or debugging
  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  // Converts the door's information to a JSON object for API or frontend use
  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }

  // Placeholder method for creating building structure; does nothing in this context
  public void createBuilding(Area... areas) {
    // do nothing
  }
}
