
package org.fundaciobit.githubgantt.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.githubgantt.model.entity.*;
import org.fundaciobit.githubgantt.model.fields.*;
import org.fundaciobit.githubgantt.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class AccountJPAManager
		 extends AbstractJPAManager<Account, Long>
		 implements AccountIJPAManager, IAccountManager, AccountFields {




  private static final long serialVersionUID = 355607871L;

	 public static final TableName<Account> _TABLENAME =  new TableName<Account>("AccountJPA");


  @PersistenceContext
  protected EntityManager __em;

  public AccountJPAManager() {
  }

  protected AccountJPAManager(EntityManager __em) {
    this.__em = __em;
  }

  @Override
  public EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return AccountJPA. class;
	}



	public TableName<Account> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Account[] listToArray(List<Account> list)  {
		if(list == null) { return null; };
		return list.toArray(new Account[list.size()]);
	};

	public synchronized Account create( java.lang.String _nom_, java.lang.String _username_, java.lang.String _token_) throws I18NException {
		AccountJPA __bean =  new AccountJPA(_nom_,_username_,_token_);
		return create(__bean);
	}



 public void delete(long _accountID_) {
   delete(findByPrimaryKey(_accountID_));
 }




	public Account findByPrimaryKey(long _accountID_) {
	  return __em.find(AccountJPA.class, _accountID_);  
	}
	@Override
	protected Account getJPAInstance(Account __bean) {
		return convertToJPA(__bean);
	}


	public static AccountJPA convertToJPA(Account __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof AccountJPA) {
	    return (AccountJPA)__bean;
	  }
	  
	  return AccountJPA.toJPA(__bean);
	}


}