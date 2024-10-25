package baseNoStates.userGroups;

import baseNoStates.partitions.Area;

import java.time.LocalDateTime;

public class UserGroupBlank extends UserGroup{

  public UserGroupBlank(String role) {
    super(role);
  }

    public boolean canSendRequests(LocalDateTime now) {return false;}
  @Override
  public boolean canBeInSpaceAndDoAction(Area toSpace, String action) {
    return false;
  }
}
