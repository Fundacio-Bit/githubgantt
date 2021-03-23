
package org.fundaciobit.githubgantt.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.githubgantt.back.form.GithubGanttBaseFilterForm;

import org.fundaciobit.githubgantt.model.fields.AccountFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class AccountFilterForm extends GithubGanttBaseFilterForm implements AccountFields {

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


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String username;

  public java.lang.String getUsername() {
    return this.username;
  }

  public void setUsername(java.lang.String username) {
    this.username = username;
  }


  private java.lang.String token;

  public java.lang.String getToken() {
    return this.token;
  }

  public void setToken(java.lang.String token) {
    this.token = token;
  }


  public AccountFilterForm() {
  }
  
  public AccountFilterForm(AccountFilterForm __toClone) {
    super(__toClone);
    this.accountIDDesde = __toClone.accountIDDesde;
    this.accountIDFins = __toClone.accountIDFins;
    this.nom = __toClone.nom;
    this.username = __toClone.username;
    this.token = __toClone.token;
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

   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
