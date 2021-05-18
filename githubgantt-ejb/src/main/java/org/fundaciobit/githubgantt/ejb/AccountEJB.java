
package org.fundaciobit.githubgantt.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.githubgantt.model.entity.Account;
import org.fundaciobit.githubgantt.persistence.AccountJPA;
import org.fundaciobit.githubgantt.persistence.AccountJPAManager;

import org.fundaciobit.githubgantt.commons.utils.Constants;

@Stateless
public class AccountEJB extends AccountJPAManager implements AccountService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(Account instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Account create(Account instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Account update(Account instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public AccountJPA findByPrimaryKey(Long _ID_) {
        return (AccountJPA)super.findByPrimaryKey(_ID_);
    }

}
