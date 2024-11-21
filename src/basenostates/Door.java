package basenostates;

import basenostates.doorstates.Actions;
import basenostates.doorstates.DoorState;
import basenostates.doorstates.State;
import basenostates.doorstates.Unlocked;
import basenostates.partitions.Area;
import basenostates.requests.RequestReader;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Represents a Door that connects two areas and has a state (e.g., locked, unlocked).
 * Handles requests to perform actions like open, close, lock, or unlock.
 * Extends the Area class.
 */
public class Door extends Area {
  private static final Logger logger = LoggerFactory.getLogger(Door.class); // Logger instance

  private final String id;           // Unique identifier for the door
  private DoorState state;           // Current state of the door
  private boolean closed;            // Indicates if the door is closed
  private Area fromSpace;            // The area the door connects from
  private Area toSpace;              // The area the door connects to

  /**
   * Constructor to initialize a door with a unique ID and default state.
   * The door is initially closed and in an unlocked state.
   * id is the unique identifier for the door.
   */
  public Door(String id) {
    this.id = id;
    this.state = new Unlocked(this, State.UNLOCKED);
    closed = true; // Door is initially closed
  }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  public void setState(DoorState state) {
    this.state = state;
  }

  public void setFromSpace(Area fromSpace) {
    this.fromSpace = fromSpace;
  }

  public void setToSpace(Area toSpace) {
    this.toSpace = toSpace;
  }

  public boolean isClosed() {
    return closed;
  }

  @Override
  public String getId() {
    return id;
  }

  public Area getFromSpace() {
    return fromSpace;
  }

  public String getStateName() {
    return state.getDoorStateName();
  }

  public Area getToSpace() {
    return toSpace;
  }

  public DoorState getState() {
    return state;
  }

  /**
   * Processes a request to perform an action on the door.
   * Checks if the request is authorized and performs the requested action if allowed.
   * request is the RequestReader containing the action and authorization.
   */
  public void processRequest(RequestReader request) {
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action); // Perform the specified action on the door
    } else {
      logger.warn("Unauthorized access attempt on door ID: {}", id);
    }
    request.setDoorStateName(getStateName()); // Update the request with the current door state
  }


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
        state.unlockShortly();
        break;
      default:
        logger.error("Action not recognized: {}", action);
    }
  }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  /**
   * Converts the door's information into a JSON object.
   * Useful for APIs or frontend applications.
   * returns a JSONObject containing the door's ID, state, and closed status.
   */
  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }

  /**
   * Placeholder method for creating a building structure.
   * Does nothing in this implementation.
   * areas are the areas to be included in the building.
   */
  public void createBuilding(Area... areas) {
    // do nothing
  }
}
