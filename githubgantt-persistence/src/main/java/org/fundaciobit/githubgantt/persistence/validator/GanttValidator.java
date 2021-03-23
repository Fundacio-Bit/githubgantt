package org.fundaciobit.githubgantt.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.githubgantt.model.entity.Gantt;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.githubgantt.model.fields.GanttFields;
import org.fundaciobit.githubgantt.model.fields.AccountFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class GanttValidator<I extends Gantt>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements GanttFields {

    protected final Logger log = Logger.getLogger(getClass());


  public GanttValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.githubgantt.model.dao.IAccountManager __accountManager
    ,org.fundaciobit.githubgantt.model.dao.IGanttManager __ganttManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ACCOUNTID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ACCOUNTID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ORGANIZATION, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ORGANIZATION)));

    __vr.rejectIfEmptyOrWhitespace(__target__,REPOSITORY, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REPOSITORY)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PROJECTNOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROJECTNOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PROJECT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROJECT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,STARTDATE, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(STARTDATE)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NUMEROPROGRAMADORS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NUMEROPROGRAMADORS)));

    // Check size
    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = __target__.getNom();
      if (__nom!= null && __nom.length() > 255) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ORGANIZATION) == 0) {
      java.lang.String __organization = __target__.getOrganization();
      if (__organization!= null && __organization.length() > 255) {
        __vr.rejectValue(ORGANIZATION, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ORGANIZATION)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ORGANIZATION) == 0) {
      String val = __target__.getOrganization();
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("(1|2|3)");
        if (!p.matcher(val).matches()) {
          __vr.rejectValue(ORGANIZATION, "genapp.validation.malformed",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString(val), new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ORGANIZATION)));
        }
      }
    }

    if (__vr.getFieldErrorCount(REPOSITORY) == 0) {
      java.lang.String __repository = __target__.getRepository();
      if (__repository!= null && __repository.length() > 255) {
        __vr.rejectValue(REPOSITORY, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REPOSITORY)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(REPOSITORY) == 0) {
      String val = __target__.getRepository();
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("(1|2|3)");
        if (!p.matcher(val).matches()) {
          __vr.rejectValue(REPOSITORY, "genapp.validation.malformed",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString(val), new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REPOSITORY)));
        }
      }
    }

    if (__vr.getFieldErrorCount(PROJECTNOM) == 0) {
      java.lang.String __projectnom = __target__.getProjectNom();
      if (__projectnom!= null && __projectnom.length() > 255) {
        __vr.rejectValue(PROJECTNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROJECTNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(PROJECT) == 0) {
      java.lang.String __project = __target__.getProject();
      if (__project!= null && __project.length() > 255) {
        __vr.rejectValue(PROJECT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROJECT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(PROJECT) == 0) {
      String val = __target__.getProject();
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("(1|2|3)");
        if (!p.matcher(val).matches()) {
          __vr.rejectValue(PROJECT, "genapp.validation.malformed",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString(val), new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROJECT)));
        }
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(ACCOUNTID) == 0) {
      java.lang.Long __accountid = __target__.getAccountID();
      Long __count_ = null;
      try { __count_ = __accountManager.count(AccountFields.ACCOUNTID.equal(__accountid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ACCOUNTID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("account.account"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("account.accountID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__accountid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}