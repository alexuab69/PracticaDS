package basenostates.partitions;

import basenostates.Door;
import java.util.ArrayList;

/**
 * This abstract class represents spaces and partitions.
 */
public abstract class Area {

  /**
   * This method return the area's id.
   */
  public abstract Object getId();

  /**
   *This method will be used to create the building by diverse
   * areas that will be spaces or partitions.
   */
  public abstract void createBuilding(Area... areas);

  /**
   * Finds an area using its unique ID.
   */
  public Area findAreaById(String id) {
    return null;
  }

  public ArrayList<Space> getSpaces() {
    return null;
  }

  public ArrayList<Door> getDoorsGivingAccess() {
    return null;
  }
}
