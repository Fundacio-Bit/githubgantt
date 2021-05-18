
package org.fundaciobit.githubgantt.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.githubgantt.model.entity.Gantt;
import org.fundaciobit.githubgantt.persistence.GanttJPA;
import org.fundaciobit.githubgantt.persistence.GanttJPAManager;

import org.fundaciobit.githubgantt.commons.utils.Constants;

@Stateless
public class GanttEJB extends GanttJPAManager implements GanttService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(Gantt instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Gantt create(Gantt instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Gantt update(Gantt instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public GanttJPA findByPrimaryKey(Long _ID_) {
        return (GanttJPA)super.findByPrimaryKey(_ID_);
    }

}
