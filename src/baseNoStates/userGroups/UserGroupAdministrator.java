package baseNoStates.userGroups;

import baseNoStates.partitions.Area;

import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.EnumSet;

public class UserGroupAdministrator extends UserGroup{
  public UserGroupAdministrator(String role) {
    super(role);

    // Set default values for Admin
    // always=Jan. 1 this year to 2100
    // all days of the week
    LocalDateTime firstDate = LocalDateTime.of(LocalDateTime.now().getYear(), 1, 1, 0, 0);
    LocalDateTime finalDate = LocalDateTime.of(2100, 1, 1, 0, 0);
    setDateRange(firstDate, finalDate);
    setWorkingDays(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY));
    setWorkingHours(0, 24);  // 12:00 AM - 11:59 PM
  }

  // all actions
  // all spaces
  @Override
  public boolean canBeInSpaceAndDoAction(Area toSpace, String action) {
    return true;
  }
}
