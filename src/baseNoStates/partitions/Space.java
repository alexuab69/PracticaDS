package baseNoStates.partitions;

import baseNoStates.Door;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Space extends Area {
  private String id;
  private ArrayList<Door> doors;

  public Space(String id, Area... areas) {
    this.id = id;
  }

  public void setId(String id) {
    this.id = id;
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
