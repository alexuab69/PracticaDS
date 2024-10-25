package baseNoStates.partitions;

import baseNoStates.Door;

import java.util.ArrayList;
import java.util.List;

// Partition represents a composite area, such as a building or floor, containing other areas (like rooms or spaces).
public class Partition extends Area {
  // Unique identifier for the partition
  private String id;
  // List of areas within this partition (could be other partitions or spaces)
  protected List<Area> areas;

  // Constructor to initialize the partition with an ID and a variable number of contained areas
  public Partition(String id, Area... areas) {
    this.id = id;
    this.areas = List.of(areas); // Initialize areas with the provided areas list
  }

  // Sets or updates the areas within this partition
  @Override
  public void createBuilding(Area... areas) {
    this.areas = List.of(areas);
  }

  // Recursive search method to locate an area by ID within this partition
  @Override
  public Area findAreaById(String id) {
    // Check if the current partition's ID matches the requested ID
    if (this.id.equals(id)) {
      return this;
    }
    // Loop through each area in the 'areas' list
    for (Area area : areas) {
      if (area instanceof Partition partition) { // If the area is a partition, search recursively
        Area foundArea = partition.findAreaById(id);
        if (foundArea != null) { // If found, return the area
          return foundArea;
        }
      } else if (area instanceof Space space) { // If the area is a space, check its ID directly
        if (space.getId().equals(id)) {
          return space;
        }
      }
    }
    return null; // Return null if no matching area is found
  }

  // Method to retrieve the ID of this partition
  @Override
  public Object getId() {
    return id;
  }

  // Recursively collects all spaces within this partition
  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();
    for (Area area : areas) {
      if (area instanceof Partition partition) {
        spaces.addAll(partition.getSpaces()); // Add spaces from nested partitions
      } else if (area instanceof Space space) {
        spaces.add(space); // Add individual spaces
      }
    }
    return spaces; // Return the list of spaces within this partition
  }

  // Retrieves all doors that provide access to areas within this partition
  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> accessDoors = new ArrayList<>(); // List to store all access doors

    // Loop through each area in the partition
    for (Area area : areas) {
      if (area instanceof Partition partition) {
        accessDoors.addAll(partition.getDoorsGivingAccess()); // Recursively add doors from nested partitions
      } else if (area instanceof Space space) {
        accessDoors.addAll(space.getDoors()); // Add doors directly from spaces
      }
    }
    return accessDoors; // Return the list of doors that provide access
  }
}
