package org.fundaciobit.githubgantt.persistence.validator;

import org.fundaciobit.githubgantt.persistence.AccountJPA;
import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import java.util.List;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.validation.AbstractBeanValidator;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class AccountBeanValidator 
      extends AbstractBeanValidator<AccountJPA> {


  // EJB's
  protected final org.fundaciobit.githubgantt.model.dao.IAccountManager __accountManager;


  public final AccountValidator<AccountJPA> _validator;


  public AccountBeanValidator(org.fundaciobit.githubgantt.model.dao.IAccountManager __accountManager) { 
    this.__accountManager = __accountManager;
    _validator = new AccountValidator<AccountJPA>();
  }

  public AccountBeanValidator(AccountValidator<AccountJPA> _validator,
     org.fundaciobit.githubgantt.model.dao.IAccountManager __accountManager) {
    this.__accountManager = __accountManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(AccountJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<AccountJPA> _bvr_ = new BeanValidatorResult<AccountJPA>();
    _validator.validate(_bvr_, target, isNou, __accountManager);
    return _bvr_.getErrors();
  }
}
