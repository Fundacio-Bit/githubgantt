package org.fundaciobit.githubgantt.model.entity;

public interface Gantt extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getGanttID();
	public void setGanttID(long _ganttID_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public long getAccountID();
	public void setAccountID(long _accountID_);

	public java.lang.String getOrganization();
	public void setOrganization(java.lang.String _organization_);

	public java.lang.String getRepository();
	public void setRepository(java.lang.String _repository_);

	public java.lang.String getProjectNom();
	public void setProjectNom(java.lang.String _projectNom_);

	public java.lang.String getProject();
	public void setProject(java.lang.String _project_);

	public java.sql.Timestamp getStartDate();
	public void setStartDate(java.sql.Timestamp _startDate_);

	public int getNumeroProgramadors();
	public void setNumeroProgramadors(int _numeroProgramadors_);



  // ======================================

}
