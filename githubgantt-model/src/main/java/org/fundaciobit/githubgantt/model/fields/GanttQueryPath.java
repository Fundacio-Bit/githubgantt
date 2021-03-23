
package org.fundaciobit.githubgantt.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class GanttQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public GanttQueryPath() {
  }

  protected GanttQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField GANTTID() {
    return new LongField(getQueryPath(), GanttFields.GANTTID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), GanttFields.NOM);
  }

  public LongField ACCOUNTID() {
    return new LongField(getQueryPath(), GanttFields.ACCOUNTID);
  }

  public StringField ORGANIZATION() {
    return new StringField(getQueryPath(), GanttFields.ORGANIZATION);
  }

  public StringField REPOSITORY() {
    return new StringField(getQueryPath(), GanttFields.REPOSITORY);
  }

  public StringField PROJECTNOM() {
    return new StringField(getQueryPath(), GanttFields.PROJECTNOM);
  }

  public StringField PROJECT() {
    return new StringField(getQueryPath(), GanttFields.PROJECT);
  }

  public TimestampField STARTDATE() {
    return new TimestampField(getQueryPath(), GanttFields.STARTDATE);
  }

  public IntegerField NUMEROPROGRAMADORS() {
    return new IntegerField(getQueryPath(), GanttFields.NUMEROPROGRAMADORS);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (GanttFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public AccountQueryPath ACCOUNT() {
    return new AccountQueryPath(new QueryPath() {
      public String getQueryPath() {
          return GanttQueryPath.this.getQueryPath() + "account" + ".";
      }
    });
  }

}
