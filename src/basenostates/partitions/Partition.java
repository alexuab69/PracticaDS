package basenostates.partitions;

import basenostates.Door;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a partition which can contain other areas such as spaces or other partitions.
 */
public class Partition extends Area {
  private final String id;
  protected List<Area> areas;

  /**
   * Constructor to initialize a partition with an id and areas.
   */
  public Partition(String id, Area... areas) {
    this.id = id;
    this.areas = List.of(areas);
  }

  @Override
  public void createBuilding(Area... areas) {
    this.areas = List.of(areas);
  }

  @Override
  public Area findAreaById(String id) {
    // Check if the current partition has the same id as the one we are looking for
    if (this.id.equals(id)) {
      return this;
    }
    // Loop through each 'Area' object in the 'areas' collection
    for (Area area : areas) {
      if (area instanceof Partition partition) {
        Area foundArea = partition.findAreaById(id);
        if (foundArea != null) {
          return foundArea;
        }
      } else if (area instanceof Space space) {
        if (space.getId().equals(id)) {
          return space;
        }
      }
    }
    return null;
  }

  @Override
  public Object getId() {
    return id;
  }

  /**
   * Gets the spaces contained within this partition.
   *
   * @return a list of spaces.
   */
  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();
    for (Area area : areas) {
      if (area instanceof Partition partition) {
        spaces.addAll(partition.getSpaces());
      } else if (area instanceof Space space) {
        spaces.add(space);
      }
    }
    return spaces;
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    // Create a new ArrayList to store all doors that provide access
    ArrayList<Door> accessDoors = new ArrayList<>();

    // Loop through each 'Area' object in the 'areas' collection
    for (Area area : areas) {
      // Check if the area is an instance of 'Partition'
      if (area instanceof Partition partition) {
        // If it's a Partition, recursively get the doors from the partition
        accessDoors.addAll(partition.getDoorsGivingAccess());
      } else if (area instanceof Space space) {
        // If it's a Space, get the doors directly from the space
        accessDoors.addAll(space.getDoors());
      }
    }
    // Return the list of doors that provide access
    return accessDoors;
  }
}
