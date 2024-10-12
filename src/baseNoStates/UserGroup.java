package baseNoStates;

import java.util.ArrayList;


public class UserGroup {
  private static ArrayList<User> users = new ArrayList<>();
  private String role;

  public void setRole(String name) {
    this.role = name;
  }

  public void setUsers(User user) {
    users.add(user);
  }

  public String getRole() {
    return this.role;
  }

  public User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    return null; // otherwise we get a Java error
  }
}