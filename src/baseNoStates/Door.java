package baseNoStates;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;


public class Door {
  private final String id;
  private boolean closed; // physically
  private boolean locked; // the door cannot be opened until unlock
  private boolean propped; // the door is open and should be closed
  private boolean unlocked_shortly; // the door will be locked shortly
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  public Door(String id) {
    this.id = id;
    closed = true;
    locked = false;
    propped = false;
    unlocked_shortly = false;
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
        if (locked) {
          System.out.println("Can't open door " + id + " because it's locked");
        } else
          if (closed) {
            closed = false;
          } else {
            System.out.println("Can't open door " + id + " because it's already open");
          }
        break;
      case Actions.CLOSE:
        if (locked) {
          System.out.println("Can't close door " + id + " because it's locked and closed");
        } else
          if (closed) {
            System.out.println("Can't close door " + id + " because it's already closed");
          } else {
            if (propped) {
              propped = false;
              System.out.println("Closing door " + id + " that was propped");
              new RequestReader("", Actions.LOCK, LocalDateTime.now(), id).process();
            }
            closed = true;
          }
        break;
      case Actions.LOCK:
        if (closed) {
          if (locked) {
            System.out.println("Can't lock door " + id + " because it's already locked");
          } else {
            locked = true;
          }
        } else {
          System.out.println("Can't lock door " + id + " because it's already open. Set state to propped");
          propped = true;
        }
        break;
      case Actions.UNLOCK:
        if (locked) {
          locked = false;
        } else {
          System.out.println("Can't unlock door " + id + " because it's already unlocked");
        }
        break;
      case Actions.UNLOCK_SHORTLY:
        System.out.println("Unlocking door " + id + " shortly");
        if (locked) {
          locked = false;
          scheduler.schedule(() -> {
            System.out.println("Locking door " + id + " after 10 seconds");
            new RequestReader("", Actions.LOCK, LocalDateTime.now(), id).process();
          }, 10, TimeUnit.SECONDS);
        } else {
          System.out.println("Can't unlock door " + id + " because it's already unlocked");
        }
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public boolean isClosed() {
    return closed;
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
      if (unlocked_shortly) {
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
