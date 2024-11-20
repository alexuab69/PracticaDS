package basenostates.usergroups;

import basenostates.User;
import java.util.ArrayList;

/**
 * The DirectoryUserGroups class manages all user groups and their associated users.
 * It provides methods to create user groups, assign users to them, and find users
 * by their credentials.
 */
public final class DirectoryUserGroups {
  private static final ArrayList<UserGroup> userGroups = new ArrayList<>();
  // List to store all user groups

  /**
   * Creates and initializes all user groups, their users, and
   * establishes the relationships between them.
   * This method sets up the entire user group directory structure.
   */
  public static void makeUserGroups() {
    // 1. Create the UserGroups
    UserGroup userGroupBlank = new UserGroupBlank("blank");
    UserGroup userGroupEmployee = new UserGroupEmployee("employee");
    UserGroup userGroupManager = new UserGroupManager("manager");
    UserGroup userGroupAdministrator = new UserGroupAdministrator("administrator");
    UserGroup userGroupSystem = new UserGroupSystem("system");

    // 2. Add the UserGroups to the ArrayList
    userGroups.add(userGroupBlank);
    userGroups.add(userGroupEmployee);
    userGroups.add(userGroupManager);
    userGroups.add(userGroupAdministrator);
    userGroups.add(userGroupSystem);

    // 3. Create the users and assign them to their respective UserGroups
    userGroupBlank.addUser(new User("Bernat", "12345", userGroupBlank));
    userGroupBlank.addUser(new User("Blai", "77532", userGroupBlank));

    userGroupEmployee.addUser(new User("Ernest", "74984", userGroupEmployee));
    userGroupEmployee.addUser(new User("Eulalia", "43295", userGroupEmployee));

    userGroupManager.addUser(new User("Manel", "95783", userGroupManager));
    userGroupManager.addUser(new User("Marta", "05827", userGroupManager));

    userGroupAdministrator.addUser(new User("Ana", "11343", userGroupAdministrator));
    userGroupSystem.addUser(new User("System", "00000", userGroupSystem));
  }

  /**
   * Searches for a user across all user groups using their unique credential.
   * credential is the unique number each user has to search for
   * returns the User with the matching credential, or null if no user is found
   */
  public static User findUserByCredential(String credential) {
    for (UserGroup userGroup : userGroups) {
      User user = userGroup.findUserByCredential(credential);
      if (user != null) {
        return user;
      }
    }
    System.out.println("user with credential " + credential + " not found");
    return null;
  }
}
