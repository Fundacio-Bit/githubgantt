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
import org.fundaciobit.githubgantt.persistence.validator.GanttValidator;

import org.fundaciobit.githubgantt.back.form.webdb.GanttForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.githubgantt.model.entity.Gantt;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class GanttWebValidator extends AbstractWebValidator<GanttForm, Gantt>
     implements Validator, GanttFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected GanttValidator<Gantt> validator = new GanttValidator<Gantt>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.githubgantt.ejb.AccountService.JNDI_NAME)
  protected org.fundaciobit.githubgantt.ejb.AccountService accountEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.githubgantt.ejb.GanttService.JNDI_NAME)
  protected org.fundaciobit.githubgantt.ejb.GanttService ganttEjb;



  public GanttWebValidator() {
    super();    
  }
  
  @Override
  public Gantt getBeanOfForm(GanttForm form) {
    return  form.getGantt();
  }

  @Override
  public Class<GanttForm> getClassOfForm() {
    return GanttForm.class;
  }

  @Override
  public void validate(GanttForm __form, Gantt __bean, Errors errors) {

    WebValidationResult<GanttForm> wvr;
    wvr = new WebValidationResult<GanttForm>(errors);

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


  public void validate(GanttForm __form, Gantt __bean, Errors errors,
    WebValidationResult<GanttForm> wvr, boolean isNou) {

    BeanValidatorResult<Gantt> __vr = new BeanValidatorResult<Gantt>();
    validator.validate(__vr, __bean,
      isNou, accountEjb, ganttEjb);

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

  public GanttValidator<Gantt> getValidator() {
    return validator;
  }

  public void setValidator(GanttValidator<Gantt> validator) {
    this.validator = validator;
  }

}