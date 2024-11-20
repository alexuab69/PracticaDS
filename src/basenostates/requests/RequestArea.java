package basenostates.requests;

import basenostates.Door;
import basenostates.doorstates.Actions;
import basenostates.partitions.Area;
import basenostates.partitions.DirectoryAreas;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents a request to perform an action (LOCK/UNLOCK) on an area.
 * This class processes requests and forwards them to the doors within the area.
 */
public class RequestArea implements Request {
  private final String credential;
  private final String action;
  private final String areaId;
  private final LocalDateTime now;
  private final ArrayList<RequestReader> requests = new ArrayList<>();

  /**
   * Constructs a RequestArea with the provided credential, action, time, and area ID.
   */
  public RequestArea(String credential, String action, LocalDateTime now, String areaId) {
    this.credential = credential;
    this.areaId = areaId;
    assert action.equals(Actions.LOCK) || action.equals(Actions.UNLOCK)
        : "invalid action " + action + " for an area request";
    this.action = action;
    this.now = now;
  }

  public String getAction() {
    return action;
  }

  @Override
  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("action", action);
    json.put("areaId", areaId);

    JSONArray jsonRequests = new JSONArray();
    for (RequestReader rd : requests) {
      jsonRequests.put(rd.answerToJson());
    }

    json.put("requestsDoors", jsonRequests);
    return json;
  }

  @Override
  public String toString() {
    String requestsDoorsStr = requests.isEmpty() ? "" : requests.toString();
    return "Request{"
        + "credential=" + credential
        + ", action=" + action
        + ", now=" + now
        + ", areaId=" + areaId
        + ", requestsDoors=" + requestsDoorsStr
        + "}";
  }

  @Override
  public void process() {
    Area area = DirectoryAreas.findAreaById(areaId);
    if (area != null) {
      for (Door door : area.getDoorsGivingAccess()) {
        RequestReader requestReader = new RequestReader(credential, action, now, door.getId());
        requestReader.process();
        requests.add(requestReader);
      }
    }
  }
}
