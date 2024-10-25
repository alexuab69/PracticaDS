package baseNoStates.partitions;

import baseNoStates.Door;

import java.util.ArrayList;
import java.util.Arrays;

public class Space extends Area {
  private final String id;
  private ArrayList<Door> doors;

  public Space(String id, Door... doors) {

    this.id = id;
    // Create a new ArrayList and add all the doors to it
    this.doors = new ArrayList<Door>();
    this.doors.addAll(Arrays.asList(doors));
  }


  @Override
  public String getId() {
    return id;
  }

  public void createBuilding(Area... areas) {
    // do nothing
  }

  public ArrayList<Door> getDoors() {
    return doors;
  }
}
