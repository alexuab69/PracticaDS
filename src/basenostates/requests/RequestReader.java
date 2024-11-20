package basenostates.requests;

import basenostates.Door;
import basenostates.User;
import basenostates.partitions.DirectoryAreas;
import basenostates.usergroups.DirectoryUserGroups;
import basenostates.usergroups.UserGroup;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents a request to perform an action (e.g., open or close a door).
 * It handles the authorization, processing, and response generation for the request.
 */
public class RequestReader implements Request {
  private final String credential; // The user's unique identifier
  private final String action;     // Action the user wants to perform (e.g., open or close a door)
  private final LocalDateTime now; // Timestamp of the request
  private final String doorId;    // Identifier for the door being accessed
  private String userName;         // Name of the user making the request
  private boolean authorized;      // Status of request authorization
  private final ArrayList<String> reasons; // Reasons for denied authorization (if applicable)
  private String doorStateName;    // Current state of the door
  private boolean doorClosed;      // Whether the door is closed or not

  /**
   * Constructs a RequestReader to initialize essential request details.
   */
  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
  }

  /**
   * Sets the current state name of the door for the request.
   * name is the name of the door's current state
   */
  public void setDoorStateName(String name) {
    doorStateName = name;
  }

  /**
   * Retrieves the action requested by the user (e.g., open or close).
   * returns the action requested by the user
   */
  public String getAction() {
    return action;
  }

  /**
   * Checks if the request has been authorized.
   * returns true if the request is authorized, false otherwise
   */
  public boolean isAuthorized() {
    return authorized;
  }

  /**
   * Adds a reason for why the request was denied authorization.
   * reason is the reason for the denied authorization
   */
  public void addReason(String reason) {
    reasons.add(reason);
  }

  @Override
  public String toString() {
    if (userName == null) {
      userName = "unknown";
    }
    return "Request{"
        + "credential=" + credential
        + ", userName=" + userName
        + ", action=" + action
        + ", now=" + now
        + ", doorID=" + doorId
        + ", closed=" + doorClosed
        + ", authorized=" + authorized
        + ", reasons=" + reasons
        + "}";
  }

  /**
   * Converts the request response into a JSON object for API or frontend use.
   * returns the JSON representation of the request response
   */
  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("authorized", authorized);
    json.put("action", action);
    json.put("doorId", doorId);
    json.put("closed", doorClosed);
    json.put("state", doorStateName);
    json.put("reasons", new JSONArray(reasons));
    return json;
  }

  /**
   * Processes the request by checking authorization, sending the request to the door,
   * and logging the details.
   */
  public void process() {
    User user = DirectoryUserGroups.findUserByCredential(credential);
    Door door = DirectoryAreas.findDoorById(doorId);
    assert door != null : "door " + doorId + " not found"; // Ensure door exists
    authorize(user, door); // Check authorization for user and door
    door.processRequest(this); // Send the request to be processed by the door
    doorClosed = door.isClosed(); // Update door's closed state
  }

  private void authorize(User user, Door door) {
    if (user == null) {
      authorized = false;
      addReason("user doesn't exist"); // User not found
    } else {
      UserGroup userGroup = user.getUserGroup();
      // Check if the user group allows the action in the specified areas and time
      authorized = userGroup.canSendRequests(now)
          && userGroup.canBeInSpaceAndDoAction(door.getFromSpace(), action)
          && userGroup.canBeInSpaceAndDoAction(door.getToSpace(), action);
      // Validate request time, origin space and destination space
    }
  }
}
