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

public class RequestReader implements Request {
  private final String credential; // The user's unique identifier
  private final String action;     // Action the user wants to perform (e.g., open or close a door)
  private final LocalDateTime now; // Timestamp of the request
  private final String doorId;     // Identifier for the door being accessed
  private String userName;         // Name of the user making the request
  private boolean authorized;      // Status of request authorization
  private final ArrayList<String> reasons; // Reasons for denied authorization (if applicable)
  private String doorStateName;    // Current state of the door
  private boolean doorClosed;      // Whether the door is closed or not

  // Constructor to initialize essential request details
  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
  }

  // Set the name of the door's current state
  public void setDoorStateName(String name) {
    doorStateName = name;
  }

  // Retrieve the action requested by the user
  public String getAction() {
    return action;
  }

  // Check if the request is authorized
  public boolean isAuthorized() {
    return authorized;
  }

  // Add a reason to the list of authorization failure reasons
  public void addReason(String reason) {
    reasons.add(reason);
  }

  // Format the request details as a string for logging or debugging
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

  // Convert the request response into a JSON object for API or frontend use
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

  // Process the request: check authorization, then send request to door, log details
  public void process() {
    User user = DirectoryUserGroups.findUserByCredential(credential);
    Door door = DirectoryAreas.findDoorById(doorId);
    assert door != null : "door " + doorId + " not found"; // Ensure door exists
    authorize(user, door); // Check authorization for user and door
    door.processRequest(this); // Send the request to be processed by the door
    doorClosed = door.isClosed(); // Update door's closed state
  }

  // Authorization check: validates the user's privileges and action
  private void authorize(User user, Door door) {
    if (user == null) {
      authorized = false;
      addReason("user doesn't exist"); // User not found
    } else {
      UserGroup userGroup = user.getUserGroup();
      // Check if the user group allows the action in the specified areas and time
      authorized = userGroup.canSendRequests(now) // Validate request time
          && userGroup.canBeInSpaceAndDoAction(door.getFromSpace(), action) // Validate origin space
          && userGroup.canBeInSpaceAndDoAction(door.getToSpace(), action);   // Validate destination space
    }
  }
}
