package baseNoStates.userGroups;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import baseNoStates.partitions.Area;

public class UserGroupEmployee extends UserGroup{

  public UserGroupEmployee(String role) {
    super(role);
  }

  @Override
  public boolean canSendRequests(LocalDateTime now) {
    // Sept 1 2024
    LocalDateTime firstDate = LocalDateTime.of(now.getYear(), 9, 1, 0, 0);

    // Mar 1 2025
    LocalDateTime finalDate = LocalDateTime.of(now.plusYears(1).getYear(), 3, 1, 0, 0);

    // Comprobar si 'now' está en días de lunes a viernes
    DayOfWeek dayOfWeek = now.getDayOfWeek();
    boolean weekDay = (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY);

    // Comprobar si la hora está entre las 9:00 y las 17:00
    int hour = now.getHour();
    boolean workingHours = (hour >= 9 && hour < 17);

    // Verificar si 'now' está dentro del rango de fechas, días laborables y horas laborales
    return (now.isEqual(firstDate) || now.isAfter(firstDate)) &&
        now.isBefore(finalDate) && weekDay && workingHours;
  }
  @Override
  public boolean canBeInSpaceAndDoAction(Area toSpace, String action) {
    return (action.equals("unlock_shortly") && toSpace.getId().equals("parking"))
        || action.equals("open")
        || action.equals("close");
  }
}
