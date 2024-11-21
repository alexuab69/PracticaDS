package basenostates;

import basenostates.requests.Request;
import basenostates.requests.RequestArea;
import basenostates.requests.RequestReader;
import basenostates.requests.RequestRefresh;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple HTTP web server that listens for client requests, processes them, and
 * sends back a response in JSON format. The server listens on a specific port
 * and creates a dedicated thread to handle each client connection.
 */
public class WebServer {
  private static final Logger logger = LoggerFactory.getLogger(WebServer.class);
  private static final int PORT = 8080; // port to listen connection
  private static final DateTimeFormatter formatter =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

  /**
   * Constructor that initializes the web server, starts listening for incoming
   * client connections, and handles them in separate threads.
   */
  public WebServer() {
    try {
      ServerSocket serverConnect = new ServerSocket(PORT);
      logger.info("Server started. Listening for connections on port: {}", PORT);

      // we listen until user halts server execution
      while (true) {
        // each client connection will be managed in a dedicated Thread
        new SocketThread(serverConnect.accept());
        // create dedicated thread to manage the client connection
      }
    } catch (IOException e) {
      logger.error("Server connection error: {}", e.getMessage(), e);
    }
  }

  private static class SocketThread extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(SocketThread.class);
    private final Socket inSocket; // client connection via Socket class

    SocketThread(Socket insocket) {
      this.inSocket = insocket;
      this.start();
    }

    @Override
    public void run() {
      BufferedReader in;
      PrintWriter out;
      String resource;

      try {
        in = new BufferedReader(new InputStreamReader(inSocket.getInputStream()));
        out = new PrintWriter(inSocket.getOutputStream());
        String input = in.readLine();

        StringTokenizer parse = new StringTokenizer(input);
        String method = parse.nextToken().toUpperCase();

        if (!method.equals("GET")) {
          logger.warn("501 Not Implemented: {} method", method);
        } else {
          resource = parse.nextToken();
          logger.debug("Input: {}, Method: {}, Resource: {}", input, method, resource);

          parse = new StringTokenizer(resource, "/[?]=&");
          int i = 0;
          String[] tokens = new String[20]; // more than the actual number of parameters
          while (parse.hasMoreTokens()) {
            tokens[i] = parse.nextToken();
            i++;
          }

          Request request = makeRequest(tokens);
          if (request != null) {
            String typeRequest = tokens[0];
            logger.debug("Created request: {} {}", typeRequest, request);
            request.process();
            logger.debug("Processed request: {} {}", typeRequest, request);

            String answer = makeJsonAnswer(request);
            logger.debug("Answer: {}", answer);
            out.println(answer);
            out.flush(); // flush character output stream buffer
          }
        }

        in.close();
        out.close();
        inSocket.close(); // close socket connection
        logger.debug("Connection closed for socket: {}", inSocket);
      } catch (Exception e) {
        logger.error("Exception occurred in socket thread: {}", e.getMessage(), e);
      }
    }

    private Request makeRequest(String[] tokens) {
      logger.debug("Tokens: {}", (Object) tokens);

      Request request;
      switch (tokens[0]) {
        case "refresh":
          request = new RequestRefresh();
          break;
        case "reader":
          request = makeRequestReader(tokens);
          break;
        case "area":
          request = makeRequestArea(tokens);
          break;
        case "get_children":
          logger.warn("Request get_children is not yet implemented.");
          request = null;
          System.exit(-1);
          break;
        default:
          logger.error("Unknown request: {}", tokens[0]);
          request = null;
          System.exit(-1);
      }
      return request;
    }

    private RequestReader makeRequestReader(String[] tokens) {
      String credential = tokens[2];
      String action = tokens[4];
      LocalDateTime dateTime = LocalDateTime.parse(tokens[6], formatter);
      String doorId = tokens[8];
      return new RequestReader(credential, action, dateTime, doorId);
    }

    private RequestArea makeRequestArea(String[] tokens) {
      String credential = tokens[2];
      String action = tokens[4];
      LocalDateTime dateTime = LocalDateTime.parse(tokens[6], formatter);
      String areaId = tokens[8];
      return new RequestArea(credential, action, dateTime, areaId);
    }

    private String makeHeaderAnswer() {
      String answer = "";
      answer += "HTTP/1.0 200 OK\r\n";
      answer += "Content-type: application/json\r\n";
      answer += "Access-Control-Allow-Origin: *\r\n";
      // SUPER IMPORTANT to avoid the CORS problem :
      // "Cross-Origin Request Blocked: The Same Origin Policy disallows reading
      // the remote resource..."
      answer += "\r\n"; // blank line between headers and content, very important !
      return answer;
    }

    private String makeJsonAnswer(Request request) {
      String answer = makeHeaderAnswer();
      answer += request.answerToJson().toString();
      return answer;
    }
  }
}
