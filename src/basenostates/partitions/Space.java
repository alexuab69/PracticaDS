package basenostates.partitions;

import basenostates.Door;
import org.json.JSONArray;
import org.json.JSONObject;

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

  /**
   * Converts the space to a JSON object.
   * the parameter is the depth of the JSON object to convert
   */
  public JSONObject toJson(int depth) { // depth not used here
    JSONObject json = new JSONObject();
    json.put("class", "space");
    json.put("id", id);
    JSONArray jsonDoors = new JSONArray();
    for (Door d : getDoorsGivingAccess()) {
      jsonDoors.put(d.toJson(1));
    }
    json.put("access_doors", jsonDoors);
    return json;
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

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return doors;
  }
}
