package baseNoStates.userGroups;

import baseNoStates.partitions.Area;

import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

public class UserGroupManager extends UserGroup{

    public UserGroupManager(String role) {
        super(role);

        ArrayList<DayOfWeek> workingDays = new ArrayList<>();
        workingDays.add(DayOfWeek.MONDAY);
        workingDays.add(DayOfWeek.TUESDAY);
        workingDays.add(DayOfWeek.WEDNESDAY);
        workingDays.add(DayOfWeek.THURSDAY);
        workingDays.add(DayOfWeek.FRIDAY);
        workingDays.add(DayOfWeek.SATURDAY);

        // Step 2: Define the working hours
        LocalTime startWorkingHour = LocalTime.of(8, 0);  // 08:00
        LocalTime endWorkingHour = LocalTime.of(20, 0);    // 20:00

        // Step 3: Define the start and end dates
        LocalDateTime startDate = LocalDateTime.of(2024, 9, 1, 0, 0);  // September 1, 2024
        LocalDateTime endDate = LocalDateTime.of(2025, 3, 1, 0, 0);    // March 1, 2025

        // Step 4: Create the Schedule instance
        schedule = new Schedule(workingDays, startWorkingHour, endWorkingHour, startDate, endDate);
    }
    @Override
    public boolean canSendRequests(LocalDateTime now) {return schedule.inRange(now);}

    @Override
    public boolean canBeInSpaceAndDoAction(Area toSpace, String action) {return true;}
}
