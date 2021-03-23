
package org.fundaciobit.githubgantt.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface AccountFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "ghg_account";


  public static final String _TABLE_MODEL = "account";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ACCOUNTID = new LongField(_TABLE_MODEL, "accountID", "accountid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField USERNAME = new StringField(_TABLE_MODEL, "username", "username");
	 public static final StringField TOKEN = new StringField(_TABLE_MODEL, "token", "token");


  public static final Field<?>[] ALL_ACCOUNT_FIELDS = {
    ACCOUNTID,
    NOM,
    USERNAME,
    TOKEN
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ACCOUNTID
  };
}
