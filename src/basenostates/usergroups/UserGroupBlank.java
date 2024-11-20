package basenostates.usergroups;

/**
* Represents a user group with no privileges.
*/
public class UserGroupBlank extends UserGroup {
  /**
  * Constructs a blank user group with the specified role.
  */
  public UserGroupBlank(String role) {
    super(role);
  }
  // users without any privilege, just to keep temporally users instead of deleting them,
  // this is to withdraw all permissions but still to keep user data to give back
  // Actions default = false then canSendRequest isn't overwritten
}
