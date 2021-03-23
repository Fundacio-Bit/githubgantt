
package org.fundaciobit.githubgantt.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.githubgantt.persistence.FitxerJPA;
import org.fundaciobit.githubgantt.persistence.FitxerIJPAManager;
import org.fundaciobit.githubgantt.model.dao.IFitxerManager;

@Local
public interface FitxerService extends FitxerIJPAManager,IFitxerManager {

 public static final String JNDI_NAME = "java:app/githubgantt-ejb/FitxerEJB!org.fundaciobit.githubgantt.ejb.FitxerService";

    public FitxerJPA findByPrimaryKey(Long _ID_);
}
