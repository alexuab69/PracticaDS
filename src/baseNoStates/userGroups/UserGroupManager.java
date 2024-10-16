package baseNoStates.userGroups;

import baseNoStates.partitions.Area;

import java.time.LocalDateTime;
import java.time.DayOfWeek;

public class UserGroupManager extends UserGroup{

  public UserGroupManager(String role) {
    super(role);
  }

  @Override
  public boolean canSendRequests(LocalDateTime now) {
    // Sept 1 2024
    LocalDateTime firstDate = LocalDateTime.of(now.getYear(), 9, 1, 0, 0);

    // Mar 1 2025
    LocalDateTime finalDate = LocalDateTime.of(now.plusYears(1).getYear(), 3, 1, 0, 0);

    // Comprobar si 'now' está en días de lunes a sábado
    DayOfWeek dayOfWeek = now.getDayOfWeek();
    boolean isntSunday = (dayOfWeek != DayOfWeek.SUNDAY); // Excluye domingo

    // Comprobar si la hora está entre las 8:00 y las 20:00
    int hour = now.getHour();
    boolean workingHours = (hour >= 8 && hour < 20);

    // Verificar si 'now' está dentro del rango de fechas, días laborables y horas laborales
    return (now.isEqual(firstDate) || now.isAfter(firstDate)) &&
        now.isBefore(finalDate) && isntSunday && workingHours;
  }
  @Override
  public boolean canBeInSpaceAndDoAction(Area toSpace, String action) {
    return true;
  }

}
