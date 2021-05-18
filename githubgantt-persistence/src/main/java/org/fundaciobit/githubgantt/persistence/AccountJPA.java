
package org.fundaciobit.githubgantt.persistence;
import org.fundaciobit.githubgantt.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "ghg_account" )
@SequenceGenerator(name="ACCOUNT_SEQ", sequenceName="ghg_account_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class AccountJPA implements Account {



private static final long serialVersionUID = 1190110862L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ACCOUNT_SEQ")
    @Index(name="ghg_account_pk_i")
    @Column(name="accountid",nullable = false,length = 19)
    long accountID;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="username",nullable = false,length = 255)
    java.lang.String username;

    @Column(name="token",nullable = false,length = 255)
    java.lang.String token;



  /** Constructor Buit */
  public AccountJPA() {
  }

  /** Constructor amb tots els camps  */
  public AccountJPA(long accountID , java.lang.String nom , java.lang.String username , java.lang.String token) {
    this.accountID=accountID;
    this.nom=nom;
    this.username=username;
    this.token=token;
}
  /** Constructor sense valors autoincrementals */
  public AccountJPA(java.lang.String nom , java.lang.String username , java.lang.String token) {
    this.nom=nom;
    this.username=username;
    this.token=token;
}
  public AccountJPA(Account __bean) {
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Account) {
      Account __instance = (Account)__obj;
      __result = true;
      __result = __result && (this.getAccountID() == __instance.getAccountID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:accountid | Table: ghg_gantt | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private Set<GanttJPA> gantts = new HashSet<GanttJPA>(0);
    public  Set<GanttJPA> getGantts() {
    return this.gantts;
  }

    public void setGantts(Set<GanttJPA> gantts) {
      this.gantts = gantts;
    }



 // ---------------  STATIC METHODS ------------------
  public static AccountJPA toJPA(Account __bean) {
    if (__bean == null) { return null;}
    AccountJPA __tmp = new AccountJPA();
    __tmp.setAccountID(__bean.getAccountID());
    __tmp.setNom(__bean.getNom());
    __tmp.setUsername(__bean.getUsername());
    __tmp.setToken(__bean.getToken());
		return __tmp;
	}


  public static AccountJPA copyJPA(AccountJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<AccountJPA> copyJPA(java.util.Set<AccountJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<AccountJPA> __tmpSet = (java.util.Set<AccountJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<AccountJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (AccountJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static AccountJPA copyJPA(AccountJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    AccountJPA __tmp = (AccountJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"GanttJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.gantts) || org.hibernate.Hibernate.isInitialized(__jpa.getGantts())) ) {
      __tmp.setGantts(GanttJPA.copyJPA(__jpa.getGantts(), __alreadyCopied,"AccountJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
