
package org.fundaciobit.githubgantt.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.githubgantt.model.entity.Traduccio;
import org.fundaciobit.githubgantt.persistence.TraduccioJPA;
import org.fundaciobit.githubgantt.persistence.TraduccioJPAManager;

import org.fundaciobit.githubgantt.utils.Constants;

@Stateless
public class TraduccioEJB extends TraduccioJPAManager implements TraduccioService {

  @Override
	@RolesAllowed({Constants.GHG_ADMIN,
        Constants.GHG_USER})
	public void delete(Traduccio instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.GHG_ADMIN,Constants.GHG_USER})
	public Traduccio create(Traduccio instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.GHG_ADMIN,
        Constants.GHG_USER})
	public Traduccio update(Traduccio instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.GHG_ADMIN,
        Constants.GHG_USER})
  public TraduccioJPA findByPrimaryKey(Long _ID_) {
    return (TraduccioJPA)super.findByPrimaryKey(_ID_);
  }

}
