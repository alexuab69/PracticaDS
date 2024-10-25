package baseNoStates.userGroups;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.ArrayList;

public class Schedule {
    // A list to store allowed hours as tuples (DayOfWeek, LocalTime start, LocalTime end)

    private final ArrayList<DayOfWeek> workingDays;

    private final LocalTime startWorkingHour;
    private final LocalTime endWorkingHour;

    //The start and end time when the schedule is valid
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Schedule(ArrayList<DayOfWeek> workingDays, LocalTime startWorkingHour, LocalTime endWorkingHour, LocalDateTime startDate, LocalDateTime endDate) {
        this.workingDays = workingDays;
        this.startWorkingHour = startWorkingHour;
        this.endWorkingHour = endWorkingHour;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Method to check if the user can enter the door at a given time
    public boolean inRange(LocalDateTime time) {
        DayOfWeek day = time.getDayOfWeek();
        LocalTime localTime = time.toLocalTime();
        if(time.isBefore(startDate) || time.isAfter(endDate)){
            return false;
        }
        // Check if the day is a working day
        if(workingDays.contains(time.getDayOfWeek())){
            // Check if the time falls within the allowed range
            if (time.toLocalTime().isBefore(endWorkingHour) && time.toLocalTime().isAfter(startWorkingHour)) {
                return true;
            }
        }
        return false;
    }
}
