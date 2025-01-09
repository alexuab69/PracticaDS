package basenostates.requests;

import basenostates.Door;
import basenostates.User;
import basenostates.partitions.DirectoryAreas;
import basenostates.usergroups.DirectoryUserGroups;
import basenostates.usergroups.UserGroup;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class RequestChildren implements Request{
    private final String areaId;
    private JSONObject jsonTree; // 1 level tree, root and children

    public RequestChildren(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaId() {
        return areaId;
    }

    @Override
    public JSONObject answerToJson() {
        return jsonTree;
    }

    @Override
    public String toString() {
        return "RequestChildren{areaId=" + areaId + "}";
    }

    public void process() {
        Area area = DirectoryAreas.getInstance().findAreaById(areaId);
        jsonTree = area.toJson(1);
    }
}