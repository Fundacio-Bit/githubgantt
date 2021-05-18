
package org.fundaciobit.githubgantt.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.githubgantt.persistence.GanttJPA;
import org.fundaciobit.githubgantt.persistence.GanttIJPAManager;
import org.fundaciobit.githubgantt.model.dao.IGanttManager;

@Local
public interface GanttService extends GanttIJPAManager,IGanttManager {

    public static final String JNDI_NAME = "java:app/githubgantt-ejb/GanttEJB!org.fundaciobit.githubgantt.ejb.GanttService";

    public GanttJPA findByPrimaryKey(Long _ID_);
}
