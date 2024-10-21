package baseNoStates;

import baseNoStates.userGroups.UserGroup;

public class User {
  private final String name;
  private final String credential;
  private final UserGroup userGroup;

  public User(String name, String credential, UserGroup userGroup) {
    this.name = name;
    this.credential = credential;
    this.userGroup = userGroup;
  }

  public String getCredential() {return credential;}
  public UserGroup getUserGroup() {return userGroup;}
  public String getName() {return name;}
  
  public String toString() {
    return "User{name=" + getName() + ", credential=" + getCredential() + ", role=" + getUserGroup().getRole() + "}";
  }

}