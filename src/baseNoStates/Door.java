package baseNoStates;

import baseNoStates.DoorStates.Actions;
import baseNoStates.DoorStates.DoorState;
import baseNoStates.DoorStates.State;
import baseNoStates.DoorStates.Unlocked;
import baseNoStates.requests.RequestReader;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class Door {
  private final String id;
  private DoorState state;
  private boolean closed;
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  public Door(String id) {
    this.id = id;
    this.state = new Unlocked(this, State.UNLOCKED);
    closed = true;
  }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  public void setState(DoorState state) {
    this.state = state;
  }

  public boolean isClosed() {return closed;}
  public String getId() {return id;}

  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
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
        state.unlock_shortly();
        break;
      default:
        System.out.println("action not recognized");
    }
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
