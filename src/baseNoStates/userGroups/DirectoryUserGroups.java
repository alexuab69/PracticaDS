package baseNoStates.userGroups;


import baseNoStates.User;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;

public final class DirectoryUserGroups {
  private static final ArrayList<UserGroup> userGroups = new ArrayList<>();

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

    // 3. Create the users and tell them to which UserGroup they belong
    User user1 = new User("Bernat","12345", userGroupBlank);
    User user2 = new User("Blai","77532", userGroupBlank);
    User user3 = new User("Ernest","74984", userGroupEmployee);
    User user4 = new User("Eulalia","43295", userGroupEmployee);
    User user5 = new User("Manel","95783", userGroupManager);
    User user6 = new User("Marta","05827", userGroupManager);
    User user7 = new User("Ana","11343", userGroupAdministrator);
    User user8 = new User("System","00000", userGroupSystem);

    // 4. Add the users to each UserGroup
    userGroupBlank.setUsers(user1);
    userGroupBlank.setUsers(user2);
    userGroupEmployee.setUsers(user3);
    userGroupEmployee.setUsers(user4);
    userGroupManager.setUsers(user5);
    userGroupManager.setUsers(user6);
    userGroupAdministrator.setUsers(user7);
    userGroupSystem.setUsers(user8);
  }

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
