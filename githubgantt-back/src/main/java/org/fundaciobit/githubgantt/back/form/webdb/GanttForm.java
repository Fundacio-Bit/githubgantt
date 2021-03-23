package org.fundaciobit.githubgantt.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.githubgantt.back.form.GithubGanttBaseForm;
import org.fundaciobit.githubgantt.persistence.GanttJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class GanttForm extends GithubGanttBaseForm {
  
  private GanttJPA gantt;
  
  public GanttForm() {
  }
  
  public GanttForm(GanttForm __toClone) {
    super(__toClone);
      this.gantt = __toClone.gantt;
    this.listOfAccountForAccountID = __toClone.listOfAccountForAccountID;
    this.listOfValuesForOrganization = __toClone.listOfValuesForOrganization;
    this.listOfValuesForRepository = __toClone.listOfValuesForRepository;
    this.listOfValuesForProject = __toClone.listOfValuesForProject;
  }
  
  public GanttForm(GanttJPA gantt, boolean nou) {
    super(nou);
    this.gantt = gantt;
  }
  
  public GanttJPA getGantt() {
    return gantt;
  }
  public void setGantt(GanttJPA gantt) {
    this.gantt = gantt;
  }
  
  
  private List<StringKeyValue> listOfAccountForAccountID;

  public List<StringKeyValue> getListOfAccountForAccountID() {
    return this.listOfAccountForAccountID;
  }

  public void setListOfAccountForAccountID(List<StringKeyValue> listOfAccountForAccountID) {
    this.listOfAccountForAccountID = listOfAccountForAccountID;
  }



  private List<StringKeyValue> listOfValuesForOrganization;

  public List<StringKeyValue> getListOfValuesForOrganization() {
    return this.listOfValuesForOrganization;
  }

  public void setListOfValuesForOrganization(List<StringKeyValue> listOfValuesForOrganization) {
    this.listOfValuesForOrganization = listOfValuesForOrganization;
  }



  private List<StringKeyValue> listOfValuesForRepository;

  public List<StringKeyValue> getListOfValuesForRepository() {
    return this.listOfValuesForRepository;
  }

  public void setListOfValuesForRepository(List<StringKeyValue> listOfValuesForRepository) {
    this.listOfValuesForRepository = listOfValuesForRepository;
  }



  private List<StringKeyValue> listOfValuesForProject;

  public List<StringKeyValue> getListOfValuesForProject() {
    return this.listOfValuesForProject;
  }

  public void setListOfValuesForProject(List<StringKeyValue> listOfValuesForProject) {
    this.listOfValuesForProject = listOfValuesForProject;
  }



  
} // Final de Classe 
