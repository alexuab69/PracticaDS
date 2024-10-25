package baseNoStates;

import baseNoStates.userGroups.UserGroup;

// Represents a user in the system with credentials and assigned user group
public class User {
  private final String name;         // Name of the user
  private final String credential;   // Unique credential for user identification
  private final UserGroup userGroup; // Group to which the user belongs, defining permissions

  // Constructor initializes the user's name, credential, and user group
  public User(String name, String credential, UserGroup userGroup) {
    this.name = name;
    this.credential = credential;
    this.userGroup = userGroup;
  }

  // Returns the credential associated with the user
  public String getCredential() { return credential; }

  // Returns the user group to which the user belongs
  public UserGroup getUserGroup() { return userGroup; }

  // Returns the name of the user
  public String getName() { return name; }

  // Converts the user's details to a string representation
  public String toString() {
    return "User{name=" + getName() + ", credential=" + getCredential() + ", role=" + getUserGroup().getRole() + "}";
  }
}
