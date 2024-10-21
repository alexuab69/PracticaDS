package baseNoStates.userGroups;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.EnumSet;

import baseNoStates.partitions.Area;

public class UserGroupEmployee extends UserGroup{

  public UserGroupEmployee(String role) {

    super(role);

    // Set default values for Employee
    // Sept 1 2024 to Mar 1 2025
    // week days 9-17h
    LocalDateTime firstDate = LocalDateTime.of(LocalDateTime.now().getYear(), 9, 1, 0, 0);
    LocalDateTime finalDate = LocalDateTime.of(LocalDateTime.now().plusYears(1).getYear(), 3, 1, 0, 0);
    setDateRange(firstDate, finalDate);
    setWorkingDays(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
    setWorkingHours(9, 17);  // 9:00 AM - 5:00 PM
  }

  @Override
  public boolean canBeInSpaceAndDoAction(Area toSpace, String action) {
    // just shortly unlock
    // ground floor, floor1, exterior, stairs (this, for all), that is, everywhere but the parking
    return (action.equals("unlock_shortly") && toSpace.getId().equals("parking"))
        || action.equals("open")
        || action.equals("close");
  }
}
