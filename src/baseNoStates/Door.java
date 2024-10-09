package baseNoStates;

import baseNoStates.DoorStates.Actions;
import baseNoStates.DoorStates.DoorState;
import baseNoStates.DoorStates.State;
import baseNoStates.DoorStates.Unlocked;
import baseNoStates.requests.RequestReader;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.Observable;


public class Door extends Observable {
  private final String id;
  private DoorState state;
  private boolean closed;
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  public Door(String id) {
    this.id = id;
    closed = true;
  }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  public boolean isClosed() {return closed;}
  public String getId() {return id;}

  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      notifyObservers(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  public String getStateName() {
    return state.getDoorStateName();
  }


  @Override
  public String toString() {

    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }
}
