package baseNoStates.userGroups;

import baseNoStates.partitions.Area;

import java.time.LocalDateTime;

public class UserGroupAdministrator extends UserGroup{
  @Override
  public boolean canSendRequests(LocalDateTime now) {
    // Primer día del año actual
    LocalDateTime startOfYear = LocalDateTime.of(now.getYear(), 1, 1, 0, 0);

    // 1 de enero del año 2100
    LocalDateTime startOfYear2100 = LocalDateTime.of(2100, 1, 1, 0, 0);

    // Verificar si 'now' está entre el 1 de enero del año actual y el 1 de enero de 2100
    return (now.isEqual(startOfYear) || now.isAfter(startOfYear)) &&
        now.isBefore(startOfYear2100);
  }
  @Override
  public boolean canBeInSpace(Area area) {
    return true;
  }
  @Override
  public boolean canDoAction(String action) {
    return true;
  }
}
