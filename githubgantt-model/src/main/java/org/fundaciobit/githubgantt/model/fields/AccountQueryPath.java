
package org.fundaciobit.githubgantt.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class AccountQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public AccountQueryPath() {
  }

  protected AccountQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ACCOUNTID() {
    return new LongField(getQueryPath(), AccountFields.ACCOUNTID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), AccountFields.NOM);
  }

  public StringField USERNAME() {
    return new StringField(getQueryPath(), AccountFields.USERNAME);
  }

  public StringField TOKEN() {
    return new StringField(getQueryPath(), AccountFields.TOKEN);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (AccountFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public GanttQueryPath GANTTS() {
    return new GanttQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AccountQueryPath.this.getQueryPath() + "gantts" + ".";
      }
    });
  }
*/

}
