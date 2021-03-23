
package org.fundaciobit.githubgantt.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface GanttFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "ghg_gantt";


  public static final String _TABLE_MODEL = "gantt";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField GANTTID = new LongField(_TABLE_MODEL, "ganttID", "ganttid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final LongField ACCOUNTID = new LongField(_TABLE_MODEL, "accountID", "accountid");
	 public static final StringField ORGANIZATION = new StringField(_TABLE_MODEL, "organization", "organization");
	 public static final StringField REPOSITORY = new StringField(_TABLE_MODEL, "repository", "repository");
	 public static final StringField PROJECTNOM = new StringField(_TABLE_MODEL, "projectNom", "projectnom");
	 public static final StringField PROJECT = new StringField(_TABLE_MODEL, "project", "project");
	 public static final TimestampField STARTDATE = new TimestampField(_TABLE_MODEL, "startDate", "startdate");
	 public static final IntegerField NUMEROPROGRAMADORS = new IntegerField(_TABLE_MODEL, "numeroProgramadors", "numeroprogramadors");


  public static final Field<?>[] ALL_GANTT_FIELDS = {
    GANTTID,
    NOM,
    ACCOUNTID,
    ORGANIZATION,
    REPOSITORY,
    PROJECTNOM,
    PROJECT,
    STARTDATE,
    NUMEROPROGRAMADORS
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
GANTTID
  };
}
