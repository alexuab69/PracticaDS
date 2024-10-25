package baseNoStates.requests;

import baseNoStates.Door;
import baseNoStates.User;
import baseNoStates.userGroups.UserGroup;
import java.time.LocalDateTime;
import java.util.ArrayList;

import baseNoStates.partitions.DirectoryAreas;
import baseNoStates.userGroups.DirectoryUserGroups;
import org.json.JSONArray;
import org.json.JSONObject;

// Class to read and process a request, checking authorization and handling door actions
public class RequestReader implements Request {
  private final String credential; // Identifier of the user making the request
  private final String action;     // Action requested (e.g., open, close, lock, etc.)
  private final LocalDateTime now; // Timestamp for when the request was made
  private final String doorId;     // ID of the door involved in the request
  private String userName;         // Name of the user (if available)
  private boolean authorized;      // Authorization status of the request
  private final ArrayList<String> reasons; // Reasons for lack of authorization
  private String doorStateName;    // Current state of the door (e.g., open, closed)
  private boolean doorClosed;      // Whether the door is closed

  // Constructor to initialize the request reader with user credentials, action, timestamp, and door ID
  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
  }

  // Sets the door state name
  public void setDoorStateName(String name) {
    doorStateName = name;
  }

  // Returns the action associated with the request
  public String getAction() {
    return action;
  }

  // Returns the authorization status of the request
  public boolean isAuthorized() {
    return authorized;
  }

  // Adds a reason to the list explaining why the request wasn't authorized
  public void addReason(String reason) {
    reasons.add(reason);
  }

  // Converts the request details to a string for easy logging and debugging
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

  // Creates a JSON object with key details of the request, including authorization status and reasons if unauthorized
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

  // Checks if the request is authorized and processes the action with the door if authorized
  public void process() {
    // Retrieves the user associated with the credential and the door by its ID
    User user = DirectoryUserGroups.findUserByCredential(credential);
    Door door = DirectoryAreas.findDoorById(doorId);
    assert door != null : "door " + doorId + " not found";

    // Determines if the user is authorized to perform the requested action on the door
    authorize(user, door);

    // Sends the request to the door for further processing (e.g., logging, actual action)
    door.processRequest(this);

    // Updates the closed status of the door after processing the request
    doorClosed = door.isClosed();
  }

  // Checks authorization based on user's permissions; adds reasons if authorization fails (used for testing)
  private void authorize(User user, Door door) {
    if (user == null) {
      authorized = false; // Deny access if user does not exist
      addReason("user doesn't exist");
    } else {
      // Get user's group and check permissions
      UserGroup userGroup = user.getUserGroup();
      authorized = userGroup.canSendRequests(now) // Check if the user can send requests at this time
          && userGroup.canBeInSpaceAndDoAction(door.getFromSpace(), action) // Check if action is allowed in the space from which the door leads
          && userGroup.canBeInSpaceAndDoAction(door.getToSpace(), action);  // Check if action is allowed in the space to which the door leads
    }
  }
}
