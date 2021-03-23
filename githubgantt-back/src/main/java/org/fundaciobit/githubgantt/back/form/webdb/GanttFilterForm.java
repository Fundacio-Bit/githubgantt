
package org.fundaciobit.githubgantt.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.githubgantt.back.form.GithubGanttBaseFilterForm;

import org.fundaciobit.githubgantt.model.fields.GanttFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class GanttFilterForm extends GithubGanttBaseFilterForm implements GanttFields {

  private java.lang.Long ganttIDDesde;

  public java.lang.Long getGanttIDDesde() {
    return this.ganttIDDesde;
  }

  public void setGanttIDDesde(java.lang.Long ganttIDDesde) {
    this.ganttIDDesde = ganttIDDesde;
  }


  private java.lang.Long ganttIDFins;

  public java.lang.Long getGanttIDFins() {
    return this.ganttIDFins;
  }

  public void setGanttIDFins(java.lang.Long ganttIDFins) {
    this.ganttIDFins = ganttIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.Long accountIDDesde;

  public java.lang.Long getAccountIDDesde() {
    return this.accountIDDesde;
  }

  public void setAccountIDDesde(java.lang.Long accountIDDesde) {
    this.accountIDDesde = accountIDDesde;
  }


  private java.lang.Long accountIDFins;

  public java.lang.Long getAccountIDFins() {
    return this.accountIDFins;
  }

  public void setAccountIDFins(java.lang.Long accountIDFins) {
    this.accountIDFins = accountIDFins;
  }


  private java.lang.String organization;

  public java.lang.String getOrganization() {
    return this.organization;
  }

  public void setOrganization(java.lang.String organization) {
    this.organization = organization;
  }


  private java.lang.String repository;

  public java.lang.String getRepository() {
    return this.repository;
  }

  public void setRepository(java.lang.String repository) {
    this.repository = repository;
  }


  private java.lang.String projectNom;

  public java.lang.String getProjectNom() {
    return this.projectNom;
  }

  public void setProjectNom(java.lang.String projectNom) {
    this.projectNom = projectNom;
  }


  private java.lang.String project;

  public java.lang.String getProject() {
    return this.project;
  }

  public void setProject(java.lang.String project) {
    this.project = project;
  }


  private java.sql.Timestamp startDateDesde;

  public java.sql.Timestamp getStartDateDesde() {
    return this.startDateDesde;
  }

  public void setStartDateDesde(java.sql.Timestamp startDateDesde) {
    this.startDateDesde = startDateDesde;
  }


  private java.sql.Timestamp startDateFins;

  public java.sql.Timestamp getStartDateFins() {
    return this.startDateFins;
  }

  public void setStartDateFins(java.sql.Timestamp startDateFins) {
    this.startDateFins = startDateFins;
  }


  private java.lang.Integer numeroProgramadorsDesde;

  public java.lang.Integer getNumeroProgramadorsDesde() {
    return this.numeroProgramadorsDesde;
  }

  public void setNumeroProgramadorsDesde(java.lang.Integer numeroProgramadorsDesde) {
    this.numeroProgramadorsDesde = numeroProgramadorsDesde;
  }


  private java.lang.Integer numeroProgramadorsFins;

  public java.lang.Integer getNumeroProgramadorsFins() {
    return this.numeroProgramadorsFins;
  }

  public void setNumeroProgramadorsFins(java.lang.Integer numeroProgramadorsFins) {
    this.numeroProgramadorsFins = numeroProgramadorsFins;
  }


  public GanttFilterForm() {
  }
  
  public GanttFilterForm(GanttFilterForm __toClone) {
    super(__toClone);
    this.ganttIDDesde = __toClone.ganttIDDesde;
    this.ganttIDFins = __toClone.ganttIDFins;
    this.nom = __toClone.nom;
    this.accountIDDesde = __toClone.accountIDDesde;
    this.accountIDFins = __toClone.accountIDFins;
    this.organization = __toClone.organization;
    this.repository = __toClone.repository;
    this.projectNom = __toClone.projectNom;
    this.project = __toClone.project;
    this.startDateDesde = __toClone.startDateDesde;
    this.startDateFins = __toClone.startDateFins;
    this.numeroProgramadorsDesde = __toClone.numeroProgramadorsDesde;
    this.numeroProgramadorsFins = __toClone.numeroProgramadorsFins;
    this.mapOfAccountForAccountID = __toClone.mapOfAccountForAccountID;
    this.mapOfValuesForOrganization = __toClone.mapOfValuesForOrganization;
    this.mapOfValuesForRepository = __toClone.mapOfValuesForRepository;
    this.mapOfValuesForProject = __toClone.mapOfValuesForProject;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }


  protected OrderBy[] defaultOrderBy = null;


  public OrderBy[] getDefaultOrderBy() {
    return this.defaultOrderBy;
  }

  public void setDefaultOrderBy(OrderBy[] defOrderBy) {
    this.defaultOrderBy = defOrderBy;
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

   // -----------------------
   // Maps de referencies.
   // -----------------------
  private Map<String, String> mapOfAccountForAccountID;

  public Map<String, String> getMapOfAccountForAccountID() {
    return this.mapOfAccountForAccountID;
  }

  public void setMapOfAccountForAccountID(Map<String, String> mapOfAccountForAccountID) {
    this.mapOfAccountForAccountID = mapOfAccountForAccountID;
  }



  private Map<String, String> mapOfValuesForOrganization;

  public Map<String, String> getMapOfValuesForOrganization() {
    return this.mapOfValuesForOrganization;
  }

  public void setMapOfValuesForOrganization(Map<String, String> mapOfValuesForOrganization) {
    this.mapOfValuesForOrganization = mapOfValuesForOrganization;
  }



  private Map<String, String> mapOfValuesForRepository;

  public Map<String, String> getMapOfValuesForRepository() {
    return this.mapOfValuesForRepository;
  }

  public void setMapOfValuesForRepository(Map<String, String> mapOfValuesForRepository) {
    this.mapOfValuesForRepository = mapOfValuesForRepository;
  }



  private Map<String, String> mapOfValuesForProject;

  public Map<String, String> getMapOfValuesForProject() {
    return this.mapOfValuesForProject;
  }

  public void setMapOfValuesForProject(Map<String, String> mapOfValuesForProject) {
    this.mapOfValuesForProject = mapOfValuesForProject;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
