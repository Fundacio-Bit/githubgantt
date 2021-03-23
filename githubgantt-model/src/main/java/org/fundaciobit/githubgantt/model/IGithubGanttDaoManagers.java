package org.fundaciobit.githubgantt.model;

import org.fundaciobit.githubgantt.model.dao.*;

public interface IGithubGanttDaoManagers {
	public IAccountManager getAccountManager();
	public IFitxerManager getFitxerManager();
	public IGanttManager getGanttManager();
	public IIdiomaManager getIdiomaManager();
	public ITraduccioManager getTraduccioManager();

}