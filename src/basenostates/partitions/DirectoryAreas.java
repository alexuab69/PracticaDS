package basenostates.partitions;

import basenostates.Door;
import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The DirectoryAreas class represents the directory of all areas and doors in a building structure.
 * It allows for the creation of areas and doors, defining their relationships, and provides methods
 * to find areas and doors by their ID.
 */
public class DirectoryAreas {
  private static final Logger logger = LoggerFactory.getLogger(DirectoryAreas.class);
  // Logger instance


  private static Area rootArea;           // Root area representing the entire building structure
  private static ArrayList<Door> allDoors; // List to store all doors in the building

  /**
   * Initializes the building structure by creating the root area, defining partitions and spaces,
   * and setting up connections between doors and spaces.
   */
  public static void makeAreas() {
    rootArea = new Partition("building"); // Initialize the root area as the main building

    // First, create all doors
    allDoors = initializeDoors();

    // Define the building layout with partitions and spaces, each containing specific doors
    rootArea.createBuilding(
        new Partition("basement",
            new Space("parking",
                allDoors.get(0), // D1
                allDoors.get(1)  // D2
            )
        ),
        new Partition("ground_floor",
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
        new Partition("floor1",
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

    // Set up door connections between spaces
    tellDoorsWhichSpacesTheyConnect();
  }

  /**
   * Defines the connections for each door, linking them to the appropriate spaces.
   * This establishes the relationships between different areas and the doors that connect them.
   */
  private static void tellDoorsWhichSpacesTheyConnect() {
    // D1 connects exterior to parking
    allDoors.get(0).setFromSpace(findAreaById("exterior"));
    allDoors.get(0).setToSpace(findAreaById("parking"));
    // D2 connects stairs to parking
    allDoors.get(1).setFromSpace(findAreaById("stairs"));
    allDoors.get(1).setToSpace(findAreaById("parking"));
    // D3 connects exterior to hall
    allDoors.get(2).setFromSpace(findAreaById("exterior"));
    allDoors.get(2).setToSpace(findAreaById("hall"));
    // D4 connects stairs to hall
    allDoors.get(3).setFromSpace(findAreaById("stairs"));
    allDoors.get(3).setToSpace(findAreaById("hall"));
    // D5 connects hall to room1
    allDoors.get(4).setFromSpace(findAreaById("hall"));
    allDoors.get(4).setToSpace(findAreaById("room1"));
    // D6 connects hall to room2
    allDoors.get(5).setFromSpace(findAreaById("hall"));
    allDoors.get(5).setToSpace(findAreaById("room2"));
    // D7 connects stairs to corridor
    allDoors.get(6).setFromSpace(findAreaById("stairs"));
    allDoors.get(6).setToSpace(findAreaById("corridor"));
    // D8 connects corridor to room3
    allDoors.get(7).setFromSpace(findAreaById("corridor"));
    allDoors.get(7).setToSpace(findAreaById("room3"));
    // D9 connects corridor to IT
    allDoors.get(8).setFromSpace(findAreaById("corridor"));
    allDoors.get(8).setToSpace(findAreaById("IT"));
  }

  /**
   * Initializes all doors in the building and returns a list of them.
   * returns an ArrayList of Door objects representing all doors in the building
   */
  private static ArrayList<Door> initializeDoors() {
    // basement
    Door d1 = new Door("D1"); // Connects exterior to parking
    Door d2 = new Door("D2"); // Connects stairs to parking

    // ground floor
    Door d3 = new Door("D3"); // Connects exterior to hall
    Door d4 = new Door("D4"); // Connects stairs to hall
    Door d5 = new Door("D5"); // Connects hall to room1
    Door d6 = new Door("D6"); // Connects hall to room2

    // first floor
    Door d7 = new Door("D7"); // Connects stairs to corridor
    Door d8 = new Door("D8"); // Connects corridor to room3
    Door d9 = new Door("D9"); // Connects corridor to IT

    return new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
  }

  /**
   * Finds an area in the building by its unique ID.
   * id is the unique identifier of the area to find
   * returns the Area with the specified ID, or null if no such area exists
   */
  public static Area findAreaById(String id) {
    return rootArea.findAreaById(id); // Returns an Area or null if not found
  }

  /**
   * Finds a door in the building by its unique ID.
   * id is the unique identifier of the door to find
   * returns the Door with the specified ID, or null if no such door exists
   */
  public static Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    logger.warn("Door with ID '{}' not found.", id);
    return null; // Returns null if door is not found
  }

  /**
   * Returns a list of all doors in the building.
   * returns an ArrayList of Door objects representing all doors in the building
   */
  public static ArrayList<Door> getAllDoors() {
    logger.debug("Retrieving all doors. '{}' ", allDoors);
    return allDoors;
  }
}
