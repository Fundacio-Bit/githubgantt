
package org.fundaciobit.githubgantt.model.bean;

import org.fundaciobit.githubgantt.model.entity.Account;


public class AccountBean implements Account {



private static final long serialVersionUID = -1761485987L;

	long accountID;// PK
	java.lang.String nom;
	java.lang.String username;
	java.lang.String token;


  /** Constructor Buit */
  public AccountBean() {
  }

  /** Constructor amb tots els camps  */
  public AccountBean(long accountID , java.lang.String nom , java.lang.String username , java.lang.String token) {
    this.accountID=accountID;
    this.nom=nom;
    this.username=username;
    this.token=token;
}
  /** Constructor sense valors autoincrementals */
  public AccountBean(java.lang.String nom , java.lang.String username , java.lang.String token) {
    this.nom=nom;
    this.username=username;
    this.token=token;
}
  public AccountBean(Account __bean) {
    this.setAccountID(__bean.getAccountID());
    this.setNom(__bean.getNom());
    this.setUsername(__bean.getUsername());
    this.setToken(__bean.getToken());
	}

	public long getAccountID() {
		return(accountID);
	};
	public void setAccountID(long _accountID_) {
		this.accountID = _accountID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getUsername() {
		return(username);
	};
	public void setUsername(java.lang.String _username_) {
		this.username = _username_;
	};

	public java.lang.String getToken() {
		return(token);
	};
	public void setToken(java.lang.String _token_) {
		this.token = _token_;
	};



  // ======================================

  public static AccountBean toBean(Account __bean) {
    if (__bean == null) { return null;}
    AccountBean __tmp = new AccountBean();
    __tmp.setAccountID(__bean.getAccountID());
    __tmp.setNom(__bean.getNom());
    __tmp.setUsername(__bean.getUsername());
    __tmp.setToken(__bean.getToken());
		return __tmp;
	}



}
