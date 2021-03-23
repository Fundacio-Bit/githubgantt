
package org.fundaciobit.githubgantt.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.githubgantt.model.entity.Idioma;
import org.fundaciobit.githubgantt.persistence.IdiomaJPA;
import org.fundaciobit.githubgantt.persistence.IdiomaJPAManager;

import org.fundaciobit.githubgantt.utils.Constants;

@Stateless
public class IdiomaEJB extends IdiomaJPAManager implements IdiomaService {

  @Override
	@RolesAllowed({Constants.GHG_ADMIN,
        Constants.GHG_USER})
	public void delete(Idioma instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.GHG_ADMIN,Constants.GHG_USER})
	public Idioma create(Idioma instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.GHG_ADMIN,
        Constants.GHG_USER})
	public Idioma update(Idioma instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.GHG_ADMIN,
        Constants.GHG_USER})
  public IdiomaJPA findByPrimaryKey(String _ID_) {
    return (IdiomaJPA)super.findByPrimaryKey(_ID_);
  }

}
