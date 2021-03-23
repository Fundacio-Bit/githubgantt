
package org.fundaciobit.githubgantt.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.githubgantt.persistence.TraduccioJPA;
import org.fundaciobit.githubgantt.persistence.TraduccioIJPAManager;
import org.fundaciobit.githubgantt.model.dao.ITraduccioManager;

@Local
public interface TraduccioService extends TraduccioIJPAManager,ITraduccioManager {

 public static final String JNDI_NAME = "java:app/githubgantt-ejb/TraduccioEJB!org.fundaciobit.githubgantt.ejb.TraduccioService";

    public TraduccioJPA findByPrimaryKey(Long _ID_);
}
