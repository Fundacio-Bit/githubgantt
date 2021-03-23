
package org.fundaciobit.githubgantt.model.bean;

import org.fundaciobit.githubgantt.model.entity.Gantt;


public class GanttBean implements Gantt {



private static final long serialVersionUID = 44996100L;

	long ganttID;// PK
	java.lang.String nom;
	long accountID;
	java.lang.String organization;
	java.lang.String repository;
	java.lang.String projectNom;
	java.lang.String project;
	java.sql.Timestamp startDate;
	int numeroProgramadors;


  /** Constructor Buit */
  public GanttBean() {
  }

  /** Constructor amb tots els camps  */
  public GanttBean(long ganttID , java.lang.String nom , long accountID , java.lang.String organization , java.lang.String repository , java.lang.String projectNom , java.lang.String project , java.sql.Timestamp startDate , int numeroProgramadors) {
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
  public GanttBean(java.lang.String nom , long accountID , java.lang.String organization , java.lang.String repository , java.lang.String projectNom , java.lang.String project , java.sql.Timestamp startDate , int numeroProgramadors) {
    this.nom=nom;
    this.accountID=accountID;
    this.organization=organization;
    this.repository=repository;
    this.projectNom=projectNom;
    this.project=project;
    this.startDate=startDate;
    this.numeroProgramadors=numeroProgramadors;
}
  public GanttBean(Gantt __bean) {
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



  // ======================================

  public static GanttBean toBean(Gantt __bean) {
    if (__bean == null) { return null;}
    GanttBean __tmp = new GanttBean();
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



}
