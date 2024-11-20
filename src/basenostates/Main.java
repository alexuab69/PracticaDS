package basenostates;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

import basenostates.partitions.DirectoryAreas;
import basenostates.usergroups.DirectoryUserGroups;

public class Main {
  public static void main(String[] args) {
    DirectoryAreas.makeAreas();
    DirectoryUserGroups.makeUserGroups();
    new WebServer();
  }
}
