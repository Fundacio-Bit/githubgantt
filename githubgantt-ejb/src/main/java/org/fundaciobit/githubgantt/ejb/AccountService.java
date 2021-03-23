
package org.fundaciobit.githubgantt.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.githubgantt.persistence.AccountJPA;
import org.fundaciobit.githubgantt.persistence.AccountIJPAManager;
import org.fundaciobit.githubgantt.model.dao.IAccountManager;

@Local
public interface AccountService extends AccountIJPAManager,IAccountManager {

 public static final String JNDI_NAME = "java:app/githubgantt-ejb/AccountEJB!org.fundaciobit.githubgantt.ejb.AccountService";

    public AccountJPA findByPrimaryKey(Long _ID_);
}
