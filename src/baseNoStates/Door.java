package baseNoStates;

import baseNoStates.DoorStates.Actions;
import baseNoStates.DoorStates.DoorState;
import baseNoStates.DoorStates.Unlocked;
import baseNoStates.requests.RequestReader;
import baseNoStates.DoorStates.State;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;


public class Door {
  private final String id;
  private DoorState state;
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  public Door(String id) {
    this.id = id;
    state = new Unlocked(this);
  }

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
        System.out.println("Unlocking door " + id + " shortly");
        state.unlock();
        scheduler.schedule(() -> {
          System.out.println("Locking door " + id + " after 10 seconds");
          state.lock();
        }, 10, TimeUnit.SECONDS);
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public boolean isClosed() {
    return state.getStateName().equals(State.CLOSED);
  }

  public boolean isLocked() {
    return locked;
  }

  public String getId() {
    return id;
  }

  public String getStateName() {
    if (propped) {
      return "propped";
    } else {
      if (unlockedShortly) {
        return "unlocked_shortly";
      } else {
        if (locked) {
          return "locked";
        } else {
          return "unlocked";
        }
      }
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

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }
}
