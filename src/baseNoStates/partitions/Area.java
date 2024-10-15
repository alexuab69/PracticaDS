package baseNoStates.partitions;

import baseNoStates.Door;

import java.util.ArrayList;

public abstract class Area {

  public ArrayList<Space> getSpaces() {return null;}
  public abstract void createBuilding(Area... areas);
  public Area findAreaById(String id) {return null;}
  public void createBuilding(Partition partition) {}
  public abstract Object getId();
  public ArrayList<Door> getDoorsGivingAccess() {return null;}
}
