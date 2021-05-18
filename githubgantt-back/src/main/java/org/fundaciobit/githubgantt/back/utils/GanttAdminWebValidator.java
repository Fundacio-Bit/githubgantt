package org.fundaciobit.githubgantt.back.utils;

import org.apache.log4j.Logger;
import org.fundaciobit.githubgantt.back.form.webdb.GanttForm;
import org.fundaciobit.githubgantt.back.validator.webdb.GanttWebValidator;
import org.fundaciobit.githubgantt.model.entity.Gantt;
import org.fundaciobit.githubgantt.persistence.validator.GanttValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * 
 * @author anadal
 *
 */
@Component
public class GanttAdminWebValidator extends GanttWebValidator {

     protected final Logger log = Logger.getLogger(getClass());

  protected GanttValidator<Gantt> validator = new GanttValidator<Gantt>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.githubgantt.ejb.AccountService.JNDI_NAME)
  protected org.fundaciobit.githubgantt.ejb.AccountService accountEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.githubgantt.ejb.GanttService.JNDI_NAME)
  protected org.fundaciobit.githubgantt.ejb.GanttService ganttEjb;



  public GanttAdminWebValidator() {
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
      
  }
}