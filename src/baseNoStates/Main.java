package baseNoStates;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

import baseNoStates.userGroups.DirectoryUserGroups;
import baseNoStates.userGroups.User;

public class Main {
  public static void main(String[] args) {
    DirectoryUserGroups directory = new DirectoryUserGroups();
    directory.makeUserGroups("src/baseNoStates/userGroups/user_roles.txt");
    User user1 = directory.findUserByCredential("12345");
    User user2 = directory.findUserByCredential("77532");
    User user3= directory.findUserByCredential("74984");
    User user4 = directory.findUserByCredential("43295");
    User user5 = directory.findUserByCredential("95783");
    User user6 =directory.findUserByCredential("05827");
    User user7 =directory.findUserByCredential("11343");
    User user8 = directory.findUserByCredential("00000");

    System.out.println(user1.toString());
    System.out.println(user2.toString());
    System.out.println(user3.toString());
    System.out.println(user4.toString());
    System.out.println(user5.toString());
    System.out.println(user6.toString());
    System.out.println(user7.toString());
    System.out.println(user8.toString());
  }
}
