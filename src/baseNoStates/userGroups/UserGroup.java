package baseNoStates.userGroups;

import baseNoStates.partitions.Area;
import baseNoStates.User;
import java.time.LocalDateTime;
import java.util.ArrayList;


public abstract class UserGroup {
  private final static ArrayList<User> users = new ArrayList<>();
  private String role = "";
  protected Schedule schedule;

  public UserGroup(String role) {
    this.role = role;
  }

  public void setUsers(User user) {
    users.add(user);
  }

  public static User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    return null; // otherwise we get a Java error
  }

  public String getRole() {return role;}
  public void setRole(String role) {this.role = role;}
  public boolean canSendRequests(LocalDateTime now) {
    return false;
  }

  public boolean canBeInSpaceAndDoAction(Area toSpace, String action) {
    return false;
  }
}
