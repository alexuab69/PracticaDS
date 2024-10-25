package baseNoStates;

import baseNoStates.userGroups.UserGroup;

public class User {
  private final String name;           // The name of the user
  private final String credential;     // Unique credential identifier for the user
  private final UserGroup userGroup;   // User's group, which defines permissions and roles

  // Constructor initializes the user with name, credential, and user group
  public User(String name, String credential, UserGroup userGroup) {
    this.name = name;
    this.credential = credential;
    this.userGroup = userGroup;
  }

  // Getter for user's credential
  public String getCredential() {
    return credential;
  }

  // Getter for user's group
  public UserGroup getUserGroup() {
    return userGroup;
  }

  // Getter for user's name
  public String getName() {
    return name;
  }

  // Converts the user's details to a string format for logging or debugging
  public String toString() {
    return "User{name=" + getName() + ", credential=" + getCredential() + ", role=" + getUserGroup().getRole() + "}";
  }
}
