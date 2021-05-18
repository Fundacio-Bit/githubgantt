
package org.fundaciobit.githubgantt.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.githubgantt.model.entity.Fitxer;
import org.fundaciobit.githubgantt.persistence.FitxerJPA;
import org.fundaciobit.githubgantt.persistence.FitxerJPAManager;

import org.fundaciobit.githubgantt.commons.utils.Constants;

@Stateless
public class FitxerEJB extends FitxerJPAManager implements FitxerService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(Fitxer instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Fitxer create(Fitxer instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Fitxer update(Fitxer instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public FitxerJPA findByPrimaryKey(Long _ID_) {
        return (FitxerJPA)super.findByPrimaryKey(_ID_);
    }

}
