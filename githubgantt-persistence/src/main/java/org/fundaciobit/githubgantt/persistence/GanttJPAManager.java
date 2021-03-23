
package org.fundaciobit.githubgantt.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.githubgantt.model.entity.*;
import org.fundaciobit.githubgantt.model.fields.*;
import org.fundaciobit.githubgantt.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class GanttJPAManager
		 extends AbstractJPAManager<Gantt, Long>
		 implements GanttIJPAManager, IGanttManager, GanttFields {




  private static final long serialVersionUID = -652474714L;

	 public static final TableName<Gantt> _TABLENAME =  new TableName<Gantt>("GanttJPA");


  @PersistenceContext
  protected EntityManager __em;

  public GanttJPAManager() {
  }

  protected GanttJPAManager(EntityManager __em) {
    this.__em = __em;
  }

  @Override
  public EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return GanttJPA. class;
	}



	public TableName<Gantt> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Gantt[] listToArray(List<Gantt> list)  {
		if(list == null) { return null; };
		return list.toArray(new Gantt[list.size()]);
	};

	public synchronized Gantt create( java.lang.String _nom_, long _accountID_, java.lang.String _organization_, java.lang.String _repository_, java.lang.String _projectNom_, java.lang.String _project_, java.sql.Timestamp _startDate_, int _numeroProgramadors_) throws I18NException {
		GanttJPA __bean =  new GanttJPA(_nom_,_accountID_,_organization_,_repository_,_projectNom_,_project_,_startDate_,_numeroProgramadors_);
		return create(__bean);
	}



 public void delete(long _ganttID_) {
   delete(findByPrimaryKey(_ganttID_));
 }




	public Gantt findByPrimaryKey(long _ganttID_) {
	  return __em.find(GanttJPA.class, _ganttID_);  
	}
	@Override
	protected Gantt getJPAInstance(Gantt __bean) {
		return convertToJPA(__bean);
	}


	public static GanttJPA convertToJPA(Gantt __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof GanttJPA) {
	    return (GanttJPA)__bean;
	  }
	  
	  return GanttJPA.toJPA(__bean);
	}


}