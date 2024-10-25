package baseNoStates.partitions;

import baseNoStates.Door;

import java.util.ArrayList;
import java.util.Arrays;

// This class organizes and manages areas within a building, including the doors that connect them.
public class DirectoryAreas {
  // Root area representing the entire building
  private static Area rootArea;
  // List of all doors in the building
  private static ArrayList<Door> allDoors;

  // Method to initialize areas and create connections between doors and spaces
  public static void makeAreas() {
    rootArea = new Partition("building"); // Initialize the building root area

    // Create and initialize all doors in the building
    allDoors = initializeDoors();

    // Build the structure of the building by adding floors, rooms, and spaces
    rootArea.createBuilding(
        new Partition("basement",
            new Space("parking",
                allDoors.get(0), // D1 - door to parking area
                allDoors.get(1)  // D2 - door to parking area
            )
        ),
        new Partition("ground floor",
            new Space("hall",
                allDoors.get(2), // D3 - door to hall
                allDoors.get(3)  // D4 - door to hall
            ),
            new Space("room1",
                allDoors.get(4)  // D5 - door to room1
            ),
            new Space("room2",
                allDoors.get(5)  // D6 - door to room2
            )
        ),
        new Partition("floor 1",
            new Space("corridor",
                allDoors.get(6) // D7 - door to corridor
            ),
            new Space("room3",
                allDoors.get(7) // D8 - door to room3
            ),
            new Space("IT",
                allDoors.get(8) // D9 - door to IT room
            )
        ),
        new Space("stairs"),    // Stairs space without specific doors
        new Space("exterior")   // Exterior space without specific doors
    );

    // Set up the spaces that each door connects to on each side
    tellDoorsWhichSpacesTheyConnect();
  }

  // Method to define which spaces each door connects
  private static void tellDoorsWhichSpacesTheyConnect() {
    // Door D1 connects the exterior to the parking area
    allDoors.get(0).setFromSpace(findAreaById("exterior"));
    allDoors.get(0).setToSpace(findAreaById("parking"));

    // Door D2 connects the stairs to the parking area
    allDoors.get(1).setFromSpace(findAreaById("stairs"));
    allDoors.get(1).setToSpace(findAreaById("parking"));

    // Door D3 connects the exterior to the hall
    allDoors.get(2).setFromSpace(findAreaById("exterior"));
    allDoors.get(2).setToSpace(findAreaById("hall"));

    // Door D4 connects the stairs to the hall
    allDoors.get(3).setFromSpace(findAreaById("stairs"));
    allDoors.get(3).setToSpace(findAreaById("hall"));

    // Door D5 connects the hall to room1
    allDoors.get(4).setFromSpace(findAreaById("hall"));
    allDoors.get(4).setToSpace(findAreaById("room1"));

    // Door D6 connects the hall to room2
    allDoors.get(5).setFromSpace(findAreaById("hall"));
    allDoors.get(5).setToSpace(findAreaById("room2"));

    // Door D7 connects the stairs to the corridor
    allDoors.get(6).setFromSpace(findAreaById("stairs"));
    allDoors.get(6).setToSpace(findAreaById("corridor"));

    // Door D8 connects the corridor to room3
    allDoors.get(7).setFromSpace(findAreaById("corridor"));
    allDoors.get(7).setToSpace(findAreaById("room3"));

    // Door D9 connects the corridor to the IT room
    allDoors.get(8).setFromSpace(findAreaById("corridor"));
    allDoors.get(8).setToSpace(findAreaById("IT"));
  }

  // Method to initialize and return a list of all doors in the building
  private static ArrayList<Door> initializeDoors() {
    // Doors are created here with unique identifiers and then added to the list
    Door d1 = new Door("D1"); // Door to the parking area from the exterior
    Door d2 = new Door("D2"); // Door to the parking area from the stairs

    // Ground floor doors
    Door d3 = new Door("D3"); // Door to hall from the exterior
    Door d4 = new Door("D4"); // Door to hall from stairs
    Door d5 = new Door("D5"); // Door to room1 from hall
    Door d6 = new Door("D6"); // Door to room2 from hall

    // First floor doors
    Door d7 = new Door("D7"); // Door to corridor from stairs
    Door d8 = new Door("D8"); // Door to room3 from corridor
    Door d9 = new Door("D9"); // Door to IT room from corridor

    return new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
  }

  // Finds and returns an Area by its ID, or null if it doesn't exist
  public static Area findAreaById(String id) {
    return rootArea.findAreaById(id);
  }

  // Finds and returns a Door by its ID, or null if not found
  public static Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    System.out.println("door with id " + id + " not found");
    return null;
  }

  // Returns all doors in the building and prints them to the console
  public static ArrayList<Door> getAllDoors() {
    System.out.println(allDoors);
    return allDoors;
  }
}
