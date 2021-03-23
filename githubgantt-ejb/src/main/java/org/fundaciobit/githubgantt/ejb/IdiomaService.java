
package org.fundaciobit.githubgantt.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.githubgantt.persistence.IdiomaJPA;
import org.fundaciobit.githubgantt.persistence.IdiomaIJPAManager;
import org.fundaciobit.githubgantt.model.dao.IIdiomaManager;

@Local
public interface IdiomaService extends IdiomaIJPAManager,IIdiomaManager {

 public static final String JNDI_NAME = "java:app/githubgantt-ejb/IdiomaEJB!org.fundaciobit.githubgantt.ejb.IdiomaService";

    public IdiomaJPA findByPrimaryKey(String _ID_);
}
