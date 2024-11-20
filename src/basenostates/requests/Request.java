package basenostates.requests;

import org.json.JSONObject;

/**
 * Interface representing a request. It defines the structure for handling requests
 * and provides methods for responding, converting to a string, and processing the request.
 */
public interface Request {

  /**
   * Converts the request response to a JSON object.
   */
  JSONObject answerToJson();

  @Override
  String toString();

  /**
   * Processes the request, performing the necessary actions based on the request's data.
   */
  void process();
}
