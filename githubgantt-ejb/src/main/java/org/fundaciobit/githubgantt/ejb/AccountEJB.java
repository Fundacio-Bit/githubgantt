
package org.fundaciobit.githubgantt.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.githubgantt.model.entity.Account;
import org.fundaciobit.githubgantt.persistence.AccountJPA;
import org.fundaciobit.githubgantt.persistence.AccountJPAManager;

import org.fundaciobit.githubgantt.utils.Constants;

@Stateless
public class AccountEJB extends AccountJPAManager implements AccountService {

  @Override
	@RolesAllowed({Constants.GHG_ADMIN,
        Constants.GHG_USER})
	public void delete(Account instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.GHG_ADMIN,Constants.GHG_USER})
	public Account create(Account instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.GHG_ADMIN,
        Constants.GHG_USER})
	public Account update(Account instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.GHG_ADMIN,
        Constants.GHG_USER})
  public AccountJPA findByPrimaryKey(Long _ID_) {
    return (AccountJPA)super.findByPrimaryKey(_ID_);
  }

}
