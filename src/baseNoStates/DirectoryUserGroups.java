package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public final class DirectoryUserGroups extends UserGroup {
  private static ArrayList<UserGroup> userGroups = new ArrayList<>();

  public void makeUserGroups() {
    UserGroup userGroup1 = new UserGroup();
    userGroup1.setRole("Any_privilege");
    // users without any privilege, just to keep temporally users instead of deleting them,
    // this is to withdraw all permissions but still to keep user data to give back
    // permissions later
    User user = new User("Bernat", "12345");
    User user1 = new User("Blai", "77532");
    userGroup1.setUsers(new User[]{user, user1});

    UserGroup userGroup2 = new UserGroup();
    userGroup2.setRole("Employee");
    // employees :
    // Sep. 1 this year to Mar. 1 next year
    // week days 9-17h
    // just shortly unlock
    // ground floor, floor1, exterior, stairs (this, for all), that is, everywhere but the parking
    User user2 = new User("Ernest", "74984", "Employee");
    User user3 =new User("Eulalia", "43295", "Employee");
    userGroup2.setUsers(new User[]{user2, user3});

    UserGroup userGroup3 = new UserGroup();
    userGroup3.setRole("Manager");
    // managers :
    // Sep. 1 this year to Mar. 1 next year
    // week days + saturday, 8-20h
    // all actions
    // all spaces
    User user4 = new User("Manel", "95783", "Manager");
    User user5 = new User("Marta", "05827", "Manager");
    userGroup3.setUsers(new User[]{user4, user5});

    UserGroup userGroup4 = new UserGroup();
    userGroup4.setRole("Administrator");
    // admin :
    // always=Jan. 1 this year to 2100
    // all days of the week
    // all actions
    // all spaces
    User user6 = new User("Ana", "11343", "Administrator");
    userGroup4.setUsers(new User[]{user6});

    UserGroup userGroup5 = new UserGroup();
    userGroup5.setRole("System");
    // does actions in the back-end, not a user. All actions
    User user7 = new User("System", "00000", "System");
    userGroup5.setUsers(new User[]{user7});

    userGroups = new ArrayList<>(Arrays.asList(userGroup1, userGroup2, userGroup3, userGroup4, userGroup5));
  }

  public User findUserByCredential(String credential) {
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
