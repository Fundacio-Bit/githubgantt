package org.fundaciobit.githubgantt.persistence;

import org.fundaciobit.githubgantt.model.*;
import org.fundaciobit.githubgantt.model.dao.*;
import javax.persistence.EntityManager;

public final class GithubGanttJPADaoManagers implements IGithubGanttDaoManagers{

   private final AccountJPAManager ghg_account;
   private final FitxerJPAManager ghg_fitxer;
   private final GanttJPAManager ghg_gantt;
   private final IdiomaJPAManager ghg_idioma;
   private final TraduccioJPAManager ghg_traduccio;

  public  GithubGanttJPADaoManagers(EntityManager __em) {
    this.ghg_account = new AccountJPAManager(__em);
    this.ghg_fitxer = new FitxerJPAManager(__em);
    this.ghg_gantt = new GanttJPAManager(__em);
    this.ghg_idioma = new IdiomaJPAManager(__em);
    this.ghg_traduccio = new TraduccioJPAManager(__em);
  }

    public IAccountManager getAccountManager() {
        return this.ghg_account;
    };

    public IFitxerManager getFitxerManager() {
        return this.ghg_fitxer;
    };

    public IGanttManager getGanttManager() {
        return this.ghg_gantt;
    };

    public IIdiomaManager getIdiomaManager() {
        return this.ghg_idioma;
    };

    public ITraduccioManager getTraduccioManager() {
        return this.ghg_traduccio;
    };


}