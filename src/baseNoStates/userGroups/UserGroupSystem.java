package baseNoStates.userGroups;

import baseNoStates.partitions.Area;

import java.time.LocalDateTime;

public class UserGroupSystem extends UserGroup {
  @Override
  public boolean canSendRequests(LocalDateTime now) {
    return true;
  }
  @Override
  public boolean canBeInSpace(Area area) {
    return true;
  }
  @Override
  public boolean canDoAction(String action) {
    return true;
  }
}
