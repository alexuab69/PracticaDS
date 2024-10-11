package baseNoStates.partitions;

import baseNoStates.Door;

import java.util.ArrayList;
import java.util.Arrays;

public class DirectoryAreas {
  private static Area rootArea;
  private static ArrayList<Door> allDoors;

  public static void makeAreas() {
    rootArea = new Partition("building");

    // First, create all doors
    allDoors = initializeDoors();

    rootArea.createBuilding(
        new Partition("basement",
            new Space("parking",
                allDoors.get(0), // D1
                allDoors.get(1)  // D2
            )
        ),
        new Partition("ground floor",
            new Space("hall",
                allDoors.get(2), // D3
                allDoors.get(3)  // D4
            ),
            new Space("room1",
                allDoors.get(4)  // D5
            ),
            new Space("room2",
                allDoors.get(5)  // D6
            )
        ),
        new Partition("floor 1",
            new Space("corridor",
                allDoors.get(6) // D7
            ),
            new Space("room3",
                allDoors.get(7) // D8
            ),
            new Space("IT",
                allDoors.get(8) // D9
            )
        ),
        new Space("stairs"),
        new Space("exterior")
    );

    // Now, tell the doors which spaces they connect
    tellDoorsWhichSpacesTheyConnect();
  }

  private static void tellDoorsWhichSpacesTheyConnect() {
    // D1
    allDoors.get(0).setFromSpace(findAreaById("exterior"));
    allDoors.get(0).setToSpace(findAreaById("parking"));
    // D2
    allDoors.get(1).setFromSpace(findAreaById("stairs"));
    allDoors.get(1).setToSpace(findAreaById("parking"));
    // D3
    allDoors.get(2).setFromSpace(findAreaById("exterior"));
    allDoors.get(2).setToSpace(findAreaById("hall"));
    // D4
    allDoors.get(3).setFromSpace(findAreaById("stairs"));
    allDoors.get(3).setToSpace(findAreaById("hall"));
    // D5
    allDoors.get(4).setFromSpace(findAreaById("hall"));
    allDoors.get(4).setToSpace(findAreaById("room1"));
    // D6
    allDoors.get(5).setFromSpace(findAreaById("hall"));
    allDoors.get(5).setToSpace(findAreaById("room2"));
    // D7
    allDoors.get(6).setFromSpace(findAreaById("stairs"));
    allDoors.get(6).setToSpace(findAreaById("corridor"));
    // D8
    allDoors.get(7).setFromSpace(findAreaById("corridor"));
    allDoors.get(7).setToSpace(findAreaById("room3"));
    // D9
    allDoors.get(8).setFromSpace(findAreaById("corridor"));
    allDoors.get(8).setToSpace(findAreaById("IT"));
  }

  private static ArrayList<Door> initializeDoors() {
    // basement
    Door d1 = new Door("D1"); // exterior, parking
    Door d2 = new Door("D2"); // stairs, parking

    // ground floor
    Door d3 = new Door("D3"); // exterior, hall
    Door d4 = new Door("D4"); // stairs, hall
    Door d5 = new Door("D5"); // hall, room1
    Door d6 = new Door("D6"); // hall, room2

    // first floor
    Door d7 = new Door("D7"); // stairs, corridor
    Door d8 = new Door("D8"); // corridor, room3
    Door d9 = new Door("D9"); // corridor, IT

    return new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
  }

  public static Area findAreaById(String id) {
    return rootArea.findAreaById(id); // an Area or null if not found
  }

  public static Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    System.out.println("door with id " + id + " not found");
    return null; // otherwise we get a Java error
  }

  public static ArrayList<Door> getAllDoors() {
    System.out.println(allDoors);
    return allDoors;
  }

}
