package org.fundaciobit.githubgantt.logic;


import javax.ejb.Local;
/*
import org.fundaciobit.githubgantt.ejb.AnnexService;
import org.fundaciobit.githubgantt.jpa.AnnexJPA;

import org.fundaciobit.genapp.common.i18n.I18NException;
*/
/**
 * 
 * @author anadal
 *
 */
@Local
public interface SampleLogicaService /* extends AnnexService */ {
	
	public static final String JNDI_NAME = "java:app/githubgantt-logic/SampleLogicaEJB!org.fundaciobit.githubgantt.logic.SampleLogicaService";

/*
  public Set<Long> deleteFull(AnnexJPA annex) throws I18NException;
  
  public AnnexJPA createFull(AnnexJPA annex) throws I18NException;
  */
}

