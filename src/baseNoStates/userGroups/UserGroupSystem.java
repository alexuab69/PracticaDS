package baseNoStates.userGroups;

import baseNoStates.partitions.Area;

import java.time.LocalDateTime;

public class UserGroupSystem extends UserGroup {
  public UserGroupSystem(String role) {
    super(role);
  }

  @Override
  public boolean canSendRequests(LocalDateTime now) { return true;}

  @Override
  public boolean canBeInSpaceAndDoAction(Area toSpace, String action) {
    return false;
  }
}
