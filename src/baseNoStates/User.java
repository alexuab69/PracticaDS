package baseNoStates;

public class User {
  private final String name;
  private final String credential;
  private String role = "No_privilege";

  public User(String name, String credential) {
    this.name = name;
    this.credential = credential;
  }

  public User(String name, String credential, String role) {
    this.name = name;
    this.credential = credential;
    this.role = role;
  }

  public String getCredential() {
    return credential;
  }

  public String toString() {
    return "User{name=" + name + ", credential=" + credential + ", role=" + role + "}";
  }

}
