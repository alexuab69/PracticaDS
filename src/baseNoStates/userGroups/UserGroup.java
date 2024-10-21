package baseNoStates.userGroups;

import baseNoStates.partitions.Area;
import baseNoStates.User;
import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.EnumSet;


public abstract class UserGroup {
  private final static ArrayList<User> users = new ArrayList<>();
  private final String role;

  // Attributes to control the date range, working days, and working hours
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private EnumSet<DayOfWeek> workingDays = EnumSet.noneOf(DayOfWeek.class);
  private int startHour = 9; // Start of working hours (default)
  private int endHour = 17;  // End of working hours (default)

  public UserGroup(String role) {
    this.role = role;
  }

  public void setUsers(User user) {
    users.add(user);
  }

  // Methods to set the attributes
  public void setWorkingHours(int startHour, int endHour) {
    this.startHour = startHour;
    this.endHour = endHour;
  }

  public void setWorkingDays(EnumSet<DayOfWeek> workingDays) {
    this.workingDays = workingDays;
  }

  public void setDateRange(LocalDateTime startDate, LocalDateTime endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public static User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    return null; // otherwise we get a Java error
  }

  public String getRole() {return role;}
  public boolean canSendRequests(LocalDateTime now) {
    if (startDate == null || endDate == null) {
      return false;  // If the date range has not been set
    }

    boolean withinDateRange = (now.isEqual(startDate) || now.isAfter(startDate)) && now.isBefore(endDate);
    DayOfWeek dayOfWeek = now.getDayOfWeek();
    boolean isWorkingDay = workingDays.contains(dayOfWeek);
    int hour = now.getHour();
    boolean withinWorkingHours = (hour >= startHour && hour < endHour);

    return withinDateRange && isWorkingDay && withinWorkingHours;
  }

  public boolean canBeInSpaceAndDoAction(Area toSpace, String action) { return false;}
}