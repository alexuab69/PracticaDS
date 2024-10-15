package baseNoStates.userGroups;


import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;

public final class DirectoryUserGroups extends UserGroup {
  private static final HashMap<String, UserGroup> userGroups = new HashMap<>();

  public void makeUserGroups(String filePath) {

    String line;
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(" ");
        if(parts.length == 3) {
          String name = parts[0];
          String credential = parts[1];
          String role = parts[2];

          // Find or create a UserGroup for this role
          UserGroup userGroup = findOrCreateUserGroup(role);

          User user = new User(name,credential, userGroup);

          // Add the user to the corresponding UserGroup
          userGroup.setUsers(user);

        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static User findUserByCredential(String credential) {
    for (UserGroup userGroup : userGroups.values()) {
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
    // Buscar en el HashMap si ya existe un UserGroup para el role dado
    if (userGroups.containsKey(role)) {
      return userGroups.get(role); // Retornar el grupo si ya existe
    }
    // Si no existe, crearlo y agregarlo al HashMap

    UserGroup newGroup;

    switch (role) {
      case "Administrator":
        newGroup = new UserGroupAdministrator();
        break;
      case "Manager":
        newGroup = new UserGroupManager();
        break;
      case "Employee":
        newGroup = new UserGroupEmployee();
        break;
      case "System":
        newGroup = new UserGroupSystem();
        break;
      default: // Caso por defecto es para roles no reconocidos, que podrían ser 'Blank' u otro.
        newGroup = new UserGroupBlank();
        break;
    }

    userGroups.put(role, newGroup);
    return newGroup;
  }
}
