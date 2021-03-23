package org.fundaciobit.githubgantt.back.form.webdb;

import org.fundaciobit.githubgantt.back.form.GithubGanttBaseForm;
import org.fundaciobit.githubgantt.persistence.TraduccioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TraduccioForm extends GithubGanttBaseForm {
  
  private TraduccioJPA traduccio;
  
  public TraduccioForm() {
  }
  
  public TraduccioForm(TraduccioForm __toClone) {
    super(__toClone);
      this.traduccio = __toClone.traduccio;
  }
  
  public TraduccioForm(TraduccioJPA traduccio, boolean nou) {
    super(nou);
    this.traduccio = traduccio;
  }
  
  public TraduccioJPA getTraduccio() {
    return traduccio;
  }
  public void setTraduccio(TraduccioJPA traduccio) {
    this.traduccio = traduccio;
  }
  
  
  
} // Final de Classe 
