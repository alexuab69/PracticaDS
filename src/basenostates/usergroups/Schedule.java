package basenostates.usergroups;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Represents a schedule that defines the allowed working days, hours, and valid date range.
 */
public class Schedule {
  // A list to store allowed hours as tuples (DayOfWeek, LocalTime start, LocalTime end)
  private final ArrayList<DayOfWeek> workingDays;
  private final LocalTime startWorkingHour;
  private final LocalTime endWorkingHour;
  //The start and end time when the schedule is valid
  private final LocalDateTime startDate;
  private final LocalDateTime endDate;

  /**
   * Constructs a Schedule with specified working days, hours, and date range.
   */
  public Schedule(ArrayList<DayOfWeek> workingDays, LocalTime startWorkingHour,
                  LocalTime endWorkingHour, LocalDateTime startDate, LocalDateTime endDate) {
    this.workingDays = workingDays;
    this.startWorkingHour = startWorkingHour;
    this.endWorkingHour = endWorkingHour;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  /**
   * Checks if a given time falls within the schedule's allowed range in order to
   * know if the user can enter the door.
   */
  public boolean inRange(LocalDateTime time) {
    DayOfWeek day = time.getDayOfWeek();
    LocalTime localTime = time.toLocalTime();
    if (time.isBefore(startDate) || time.isAfter(endDate)) {
      return false;
    }
    // Check if the day is a working day
    if (workingDays.contains(day)) {
      // Check if the time falls within the allowed range
      return localTime.isBefore(endWorkingHour) && localTime.isAfter(startWorkingHour);
    }
    return false;
  }
}
