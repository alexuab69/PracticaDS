package baseNoStates.userGroups;

import baseNoStates.partitions.Area;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class UserGroupEmployee extends UserGroup{
  @Override
  public boolean canSendRequests(LocalDateTime now) {
    // Sep. 1 this year
    LocalDateTime firstDate = LocalDateTime.of(now.getYear(), 9, 1, 0, 0);

    // Mar. 1 next year
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
  public boolean canBeInSpace(Area area) {
    return area.getId().equals("parking");
  }
  @Override
  public boolean canDoAction(String action) {
    return action.equals("unlock_shortly");
  }
}
