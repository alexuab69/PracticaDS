package baseNoStates.partitions;

import baseNoStates.Door;

import java.util.ArrayList;
import java.util.List;

public class Partition extends Area {
  private String id;
  protected List<Area> areas;

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
    return null;
  }

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
    ArrayList<Door> accessDoors = new ArrayList<>();
    for (Area area : areas) {
      if (area instanceof Partition partition) {
        accessDoors.addAll(partition.getDoorsGivingAccess());
      } else if (area instanceof Space space) {
        accessDoors.addAll(space.getDoors());
      }
    }
    return accessDoors;
  }

}
