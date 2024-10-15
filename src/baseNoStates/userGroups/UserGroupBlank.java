package baseNoStates.userGroups;

import baseNoStates.partitions.Area;

import java.time.LocalDateTime;

public class UserGroupBlank extends UserGroup{
  @Override
  public boolean canSendRequests(LocalDateTime now) {
    return false;
  }
  @Override
  public boolean canBeInSpace(Area area) {
    return false;
  }
  @Override
  public boolean canDoAction(String action) {
    return false;
  }
}
