package org.fundaciobit.githubgantt.back.form.webdb;

import org.fundaciobit.githubgantt.back.form.GithubGanttBaseForm;
import org.fundaciobit.githubgantt.persistence.AccountJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class AccountForm extends GithubGanttBaseForm {
  
  private AccountJPA account;
  
  public AccountForm() {
  }
  
  public AccountForm(AccountForm __toClone) {
    super(__toClone);
      this.account = __toClone.account;
  }
  
  public AccountForm(AccountJPA account, boolean nou) {
    super(nou);
    this.account = account;
  }
  
  public AccountJPA getAccount() {
    return account;
  }
  public void setAccount(AccountJPA account) {
    this.account = account;
  }
  
  
  
} // Final de Classe 
