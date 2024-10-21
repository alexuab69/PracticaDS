package baseNoStates.userGroups;

import java.time.LocalDateTime;

public class UserGroupBlank extends UserGroup{

  public UserGroupBlank(String role) { super(role); }

  // users without any privilege, just to keep temporally users instead of deleting them,
  // this is to withdraw all permissions but still to keep user data to give back

  @Override
  public boolean canSendRequests(LocalDateTime now) {
    return false;
  }
  //Actions default = false
}
