package org.fundaciobit.githubgantt.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import java.util.List;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.githubgantt.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.githubgantt.persistence.validator.AccountValidator;

import org.fundaciobit.githubgantt.back.form.webdb.AccountForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.githubgantt.model.entity.Account;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class AccountWebValidator extends AbstractWebValidator<AccountForm, Account>
     implements Validator, AccountFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected AccountValidator<Account> validator = new AccountValidator<Account>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.githubgantt.ejb.AccountService.JNDI_NAME)
  protected org.fundaciobit.githubgantt.ejb.AccountService accountEjb;



  public AccountWebValidator() {
    super();    
  }
  
  @Override
  public Account getBeanOfForm(AccountForm form) {
    return  form.getAccount();
  }

  @Override
  public Class<AccountForm> getClassOfForm() {
    return AccountForm.class;
  }

  @Override
  public void validate(AccountForm __form, Account __bean, Errors errors) {

    WebValidationResult<AccountForm> wvr;
    wvr = new WebValidationResult<AccountForm>(errors);

    boolean isNou;
    {
        Object objNou = errors.getFieldValue("nou");
        if (objNou == null) {
            isNou = false;
        } else { 
         Boolean nou = Boolean.parseBoolean((String)objNou);
         isNou =  nou != null && nou.booleanValue();
        }
    }

    validate(__form, __bean , errors, wvr, isNou);
  }


  public void validate(AccountForm __form, Account __bean, Errors errors,
    WebValidationResult<AccountForm> wvr, boolean isNou) {

    BeanValidatorResult<Account> __vr = new BeanValidatorResult<Account>();
    validator.validate(__vr, __bean,
      isNou, accountEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }


  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public AccountValidator<Account> getValidator() {
    return validator;
  }

  public void setValidator(AccountValidator<Account> validator) {
    this.validator = validator;
  }

}