package baseNoStates;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

import baseNoStates.partitions.DirectoryAreas;
import baseNoStates.userGroups.DirectoryUserGroups;

public class Main {
  public static void main(String[] args) {
    DirectoryAreas.makeAreas();
    DirectoryUserGroups.makeUserGroups();
    new WebServer();
  }
}
