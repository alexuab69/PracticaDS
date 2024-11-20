package basenostates.partitions;

import basenostates.Door;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a physical space that contains doors and has a unique identifier.
 */
public class Space extends Area {
  private final String id;
  private final ArrayList<Door> doors;

  /**
   * Constructs a Space with a specified ID and associated doors.
   */
  public Space(String id, Door... doors) {

    this.id = id;
    // Create a new ArrayList and add all the doors to it
    this.doors = new ArrayList<>();
    this.doors.addAll(Arrays.asList(doors));
  }


  @Override
  public String getId() {
    return id;
  }

  /**
   * Does nothing as spaces do not create buildings.
   */
  public void createBuilding(Area... areas) {
    // do nothing
  }

  public ArrayList<Door> getDoors() {
    return doors;
  }
}
