package basenostates.usergroups;

import basenostates.User;
import basenostates.partitions.Area;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents an abstract user group that manages a collection of users and defines permissions
 * such as schedule constraints and area-based actions.
 * Subclasses should implement specific behavior for sending requests and area access permissions.
 */
public abstract class UserGroup {
  private final ArrayList<User> users = new ArrayList<>();
  private final String role;
  protected Schedule schedule;

  /**
   * Constructs a UserGroup with the specified role.
   */
  public UserGroup(String role) {
    this.role = role;
  }

  /**
  * Adds a user to this user group.
  */
  public void addUser(User user) {
    users.add(user);
  }

  /**
  * Finds a user in this group by their unique credential.
  */
  public User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    return null; // otherwise we get a Java error
  }

  public String getRole() {
    return role;
  }

  /**
  * Checks if users in this group can send requests at a given time.
  * This should be overridden in subclasses to provide specific behavior.
  */
  public boolean canSendRequests(LocalDateTime now) {
    return false;
  }

  /**
  * Determines if users in this group are allowed to be in a specified space and perform an action.
  * This should be overridden in subclasses to provide specific behavior.
  */
  public boolean canBeInSpaceAndDoAction(Area toSpace, String action) {
    return false;
  }
}
