package basenostates.usergroups;

import basenostates.partitions.Area;
import java.time.LocalDateTime;

/**
 * Represents a user group for administrators who have unrestricted access.
 * Administrators can perform any action in any area at any time.
 */
public class UserGroupAdministrator extends UserGroup {
  /**
  * Constructs an administrator user group with the specified role.
  */
  public UserGroupAdministrator(String role) {
    super(role);
  }

  // can do anything, anywhere, anytime
  @Override
  public boolean canSendRequests(LocalDateTime now) {
    return true;
  }

  @Override
  public boolean canBeInSpaceAndDoAction(Area toSpace, String action) {
    return true;
  }
}
