package baseNoStates.userGroups;

import baseNoStates.partitions.Area;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class UserGroup {
  private final static ArrayList<User> users = new ArrayList<>();

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

  public boolean canSendRequests(LocalDateTime now) {
    return false;
  }
  public boolean canBeInSpace(Area area) {
    return false;
  }
  public boolean canDoAction(String action) {
    return false;
  }

}