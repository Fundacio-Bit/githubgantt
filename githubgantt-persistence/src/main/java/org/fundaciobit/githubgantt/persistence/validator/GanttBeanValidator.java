package org.fundaciobit.githubgantt.persistence.validator;

import org.fundaciobit.githubgantt.persistence.GanttJPA;
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
public class GanttBeanValidator 
      extends AbstractBeanValidator<GanttJPA> {


  // EJB's
  protected final org.fundaciobit.githubgantt.model.dao.IAccountManager __accountManager;

  protected final org.fundaciobit.githubgantt.model.dao.IGanttManager __ganttManager;


  public final GanttValidator<GanttJPA> _validator;


  public GanttBeanValidator(org.fundaciobit.githubgantt.model.dao.IAccountManager __accountManager,
     org.fundaciobit.githubgantt.model.dao.IGanttManager __ganttManager) { 
    this.__accountManager = __accountManager;
    this.__ganttManager = __ganttManager;
    _validator = new GanttValidator<GanttJPA>();
  }

  public GanttBeanValidator(GanttValidator<GanttJPA> _validator,
     org.fundaciobit.githubgantt.model.dao.IAccountManager __accountManager,
     org.fundaciobit.githubgantt.model.dao.IGanttManager __ganttManager) {
    this.__accountManager = __accountManager;
    this.__ganttManager = __ganttManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(GanttJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<GanttJPA> _bvr_ = new BeanValidatorResult<GanttJPA>();
    _validator.validate(_bvr_, target, isNou, __accountManager, __ganttManager);
    return _bvr_.getErrors();
  }
}
