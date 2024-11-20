package basenostates.usergroups;

import basenostates.partitions.Area;
// Importing Area to manage access to different spaces
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
* Represents a user group for employees with specific work schedule and access permissions.
*/
public class UserGroupEmployee extends UserGroup {
  /**
  * Constructs a UserGroupEmployee with the specified role and predefined schedule.
  */
  public UserGroupEmployee(String role) {
    super(role); // Call to the superclass constructor to set the role

    ArrayList<DayOfWeek> workingDays = new ArrayList<>();
    // List to hold the days the employee works
    workingDays.add(DayOfWeek.MONDAY);   // Adding Monday to working days
    workingDays.add(DayOfWeek.TUESDAY);  // Adding Tuesday to working days
    workingDays.add(DayOfWeek.WEDNESDAY); // Adding Wednesday to working days
    workingDays.add(DayOfWeek.THURSDAY); // Adding Thursday to working days
    workingDays.add(DayOfWeek.FRIDAY);   // Adding Friday to working days

    // Step 2: Define the working hours
    LocalTime startWorkingHour = LocalTime.of(9, 0);
    // Setting the start time of the workday (09:00)
    LocalTime endWorkingHour = LocalTime.of(17, 0);
    // Setting the end time of the workday (17:00)

    // Step 3: Define the start and end dates for the working period
    LocalDateTime startDate = LocalDateTime.of(2024, 9, 1, 0, 0);  // September 1, 2024
    LocalDateTime endDate = LocalDateTime.of(2025, 3, 1, 0, 0);    // March 1, 2025

    // Step 4: Create the Schedule instance with the defined parameters
    schedule = new Schedule(workingDays, startWorkingHour, endWorkingHour, startDate, endDate);
  }

  // Method to check if the user can send requests based on the current time
  @Override
  public boolean canSendRequests(LocalDateTime now) {
    System.out.println(schedule.inRange(now)); // Print the result of the schedule check
    return schedule.inRange(now); // Return whether the current time is within the schedule range
  }

  // Method to check if the user can perform an action in a specific area
  @Override
  public boolean canBeInSpaceAndDoAction(Area toSpace, String action) {
    // Check if the action is allowed based on the user's permissions
    return (action.equals("unlock_shortly") && toSpace.getId().equals("parking"))
        // Special condition for parking
        || action.equals("open") // Allow open action
        || action.equals("close"); // Allow close action
  }
}
