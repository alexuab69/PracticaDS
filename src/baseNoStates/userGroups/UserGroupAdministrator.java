package baseNoStates.userGroups;

import baseNoStates.partitions.Area;

import java.time.LocalDateTime;

public class UserGroupAdministrator extends UserGroup{
  public UserGroupAdministrator(String role) {
    super(role);
  }

  // can do anything, anywhere, anytime
  @Override
  public boolean canSendRequests(LocalDateTime now) { return true; }
  @Override
  public boolean canBeInSpaceAndDoAction(Area toSpace, String action) {
    return true;
  }
}
