package org.fundaciobit.githubgantt.model;

public class GithubGanttDaoManager {
  
  private static IGithubGanttDaoManagers instance = null;
  
  public static void setDaoManagers(IGithubGanttDaoManagers managers) {
    instance = managers;
  }
  
  public static IGithubGanttDaoManagers getDaoManagers() throws Exception {
    if(instance == null) {
      throw new Exception("Ha de inicialitzar el sistema de Managers cridant "
          + " al mètode GithubGanttDaoManager.setDaoManagers(...)");
    }
    return instance;
  }
  
}
