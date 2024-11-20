package basenostates.usergroups;

import basenostates.partitions.Area;
import java.time.LocalDateTime;


/**
* Represents a system user group that performs backend operations and has full access
* to perform actions in all spaces, regardless of time or area restrictions.
*/
public class UserGroupSystem extends UserGroup {
  /**
  * Represents a system user group that performs backend operations and has full access
  * to perform actions in all spaces, regardless of time or area restrictions.
  */
  public UserGroupSystem(String role) {
    super(role);
  }

  // does actions in the back-end, not a user. All actions
  @Override
  public boolean canSendRequests(LocalDateTime now) {
    return true;
  }

  @Override
  public boolean canBeInSpaceAndDoAction(Area toSpace, String action) {
    return true;
  }
}
