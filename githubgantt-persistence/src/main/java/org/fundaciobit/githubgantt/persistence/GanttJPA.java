
package org.fundaciobit.githubgantt.persistence;
import org.fundaciobit.githubgantt.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "ghg_gantt" )
@SequenceGenerator(name="GANTT_SEQ", sequenceName="ghg_gantt_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class GanttJPA implements Gantt {



private static final long serialVersionUID = -1661109497L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GANTT_SEQ")
	@Index(name="ghg_gantt_pk_i")
	@Column(name="ganttid",nullable = false,length = 19)
	long ganttID;

	@Column(name="nom",nullable = false,length = 255)
	java.lang.String nom;

	@Index(name="ghg_gantt_accountid_fk_i")
	@Column(name="accountid",nullable = false,length = 19)
	long accountID;

	@Column(name="organization",nullable = false,length = 255)
	java.lang.String organization;

	@Column(name="repository",nullable = false,length = 255)
	java.lang.String repository;

	@Column(name="projectnom",nullable = false,length = 255)
	java.lang.String projectNom;

	@Column(name="project",nullable = false,length = 255)
	java.lang.String project;

	@Column(name="startdate",nullable = false,length = 29,precision = 6)
	java.sql.Timestamp startDate;

	@Column(name="numeroprogramadors",nullable = false,length = 10)
	int numeroProgramadors;



  /** Constructor Buit */
  public GanttJPA() {
  }

  /** Constructor amb tots els camps  */
  public GanttJPA(long ganttID , java.lang.String nom , long accountID , java.lang.String organization , java.lang.String repository , java.lang.String projectNom , java.lang.String project , java.sql.Timestamp startDate , int numeroProgramadors) {
    this.ganttID=ganttID;
    this.nom=nom;
    this.accountID=accountID;
    this.organization=organization;
    this.repository=repository;
    this.projectNom=projectNom;
    this.project=project;
    this.startDate=startDate;
    this.numeroProgramadors=numeroProgramadors;
}
  /** Constructor sense valors autoincrementals */
  public GanttJPA(java.lang.String nom , long accountID , java.lang.String organization , java.lang.String repository , java.lang.String projectNom , java.lang.String project , java.sql.Timestamp startDate , int numeroProgramadors) {
    this.nom=nom;
    this.accountID=accountID;
    this.organization=organization;
    this.repository=repository;
    this.projectNom=projectNom;
    this.project=project;
    this.startDate=startDate;
    this.numeroProgramadors=numeroProgramadors;
}
  public GanttJPA(Gantt __bean) {
    this.setGanttID(__bean.getGanttID());
    this.setNom(__bean.getNom());
    this.setAccountID(__bean.getAccountID());
    this.setOrganization(__bean.getOrganization());
    this.setRepository(__bean.getRepository());
    this.setProjectNom(__bean.getProjectNom());
    this.setProject(__bean.getProject());
    this.setStartDate(__bean.getStartDate());
    this.setNumeroProgramadors(__bean.getNumeroProgramadors());
	}

	public long getGanttID() {
		return(ganttID);
	};
	public void setGanttID(long _ganttID_) {
		this.ganttID = _ganttID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public long getAccountID() {
		return(accountID);
	};
	public void setAccountID(long _accountID_) {
		this.accountID = _accountID_;
	};

	public java.lang.String getOrganization() {
		return(organization);
	};
	public void setOrganization(java.lang.String _organization_) {
		this.organization = _organization_;
	};

	public java.lang.String getRepository() {
		return(repository);
	};
	public void setRepository(java.lang.String _repository_) {
		this.repository = _repository_;
	};

	public java.lang.String getProjectNom() {
		return(projectNom);
	};
	public void setProjectNom(java.lang.String _projectNom_) {
		this.projectNom = _projectNom_;
	};

	public java.lang.String getProject() {
		return(project);
	};
	public void setProject(java.lang.String _project_) {
		this.project = _project_;
	};

	public java.sql.Timestamp getStartDate() {
		return(startDate);
	};
	public void setStartDate(java.sql.Timestamp _startDate_) {
		this.startDate = _startDate_;
	};

	public int getNumeroProgramadors() {
		return(numeroProgramadors);
	};
	public void setNumeroProgramadors(int _numeroProgramadors_) {
		this.numeroProgramadors = _numeroProgramadors_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Gantt) {
      Gantt __instance = (Gantt)__obj;
      __result = true;
      __result = __result && (this.getGanttID() == __instance.getGanttID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:accountid | Table: ghg_account | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="ghg_gantt_account_aco_fk")
	@JoinColumn(name = "accountid", referencedColumnName ="accountID", nullable = false, insertable=false, updatable=false)
	private AccountJPA account;

	public AccountJPA getAccount() {
    return this.account;
  }

	public  void setAccount(AccountJPA account) {
    this.account = account;
  }


 // ---------------  STATIC METHODS ------------------
  public static GanttJPA toJPA(Gantt __bean) {
    if (__bean == null) { return null;}
    GanttJPA __tmp = new GanttJPA();
    __tmp.setGanttID(__bean.getGanttID());
    __tmp.setNom(__bean.getNom());
    __tmp.setAccountID(__bean.getAccountID());
    __tmp.setOrganization(__bean.getOrganization());
    __tmp.setRepository(__bean.getRepository());
    __tmp.setProjectNom(__bean.getProjectNom());
    __tmp.setProject(__bean.getProject());
    __tmp.setStartDate(__bean.getStartDate());
    __tmp.setNumeroProgramadors(__bean.getNumeroProgramadors());
		return __tmp;
	}


  public static GanttJPA copyJPA(GanttJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<GanttJPA> copyJPA(java.util.Set<GanttJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<GanttJPA> __tmpSet = (java.util.Set<GanttJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<GanttJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (GanttJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static GanttJPA copyJPA(GanttJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    GanttJPA __tmp = (GanttJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"AccountJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.account) || org.hibernate.Hibernate.isInitialized(__jpa.getAccount()) ) ) {
      __tmp.setAccount(AccountJPA.copyJPA(__jpa.getAccount(), __alreadyCopied,"GanttJPA"));
    }

    return __tmp;
  }




}
