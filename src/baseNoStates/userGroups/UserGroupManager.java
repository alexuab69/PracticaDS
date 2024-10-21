package baseNoStates.userGroups;

import baseNoStates.partitions.Area;

import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.EnumSet;

public class UserGroupManager extends UserGroup{

  public UserGroupManager(String role) {

    super(role);

    // Set default values for Manager
    // Sep. 1 this year to Mar. 1 next year
    // week days + saturday, 8-20h
    LocalDateTime firstDate = LocalDateTime.of(LocalDateTime.now().getYear(), 9, 1, 0, 0);
    LocalDateTime finalDate = LocalDateTime.of(LocalDateTime.now().plusYears(1).getYear(), 3, 1, 0, 0);
    setDateRange(firstDate, finalDate);
    setWorkingDays(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY));
    setWorkingHours(8, 20);  // 8:00 AM - 8:00 PM
  }

  // all actions
  // all spaces
  @Override
  public boolean canBeInSpaceAndDoAction(Area toSpace, String action) {
    return true;
  }

}
