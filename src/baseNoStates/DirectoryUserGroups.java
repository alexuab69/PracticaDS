package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

public final class DirectoryUserGroups extends UserGroup {
  private static final ArrayList<UserGroup> userGroups = new ArrayList<>();
  private final String FILE_NAME_USERS = "user_roles.txt";

  public void makeUserGroups(String filePath) {

    String line;
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(" ");
        if(parts.length == 3) {
          String name = parts[0];
          String credential = parts[1];
          String role = parts[2];

          User user = new User(name,credential,role);

          // Find or create a UserGroup for this role
          UserGroup userGroup = findOrCreateUserGroup(role);

          // Add the user to the corresponding UserGroup
          userGroup.setUsers(user);

        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
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

  // Method to find a UserGroup by its role or create it if it doesn't exist
  private static UserGroup findOrCreateUserGroup(String role) {
    for (UserGroup group : userGroups) {
      if (group.getRole().equals(role)) {
        return group; // Return the group if it already exists
      }
    }
    // If no group exists for that role, create it and add it to the list
    UserGroup newGroup = new UserGroup();
    newGroup.setRole(role);
    userGroups.add(newGroup);
    return newGroup;
  }
}
