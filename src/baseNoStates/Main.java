package baseNoStates;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

public class Main {
  public static void main(String[] args) {
    DirectoryUserGroups.makeUserGroups("users_roles.txt");
    new WebServer();
  }
}
