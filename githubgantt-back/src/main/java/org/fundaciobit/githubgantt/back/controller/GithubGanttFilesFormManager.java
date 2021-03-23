package org.fundaciobit.githubgantt.back.controller;

import org.fundaciobit.githubgantt.persistence.FitxerJPA;
import org.fundaciobit.githubgantt.model.entity.Fitxer;

import org.fundaciobit.genapp.common.filesystem.IFileManager;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;


/**
 * Gestiona Multiples Fitxers d'un Form
 * 
 * @author anadal
 * 
 */
public class GithubGanttFilesFormManager extends FilesFormManager<Fitxer> {

  public GithubGanttFilesFormManager(IFileManager<Fitxer> fitxerEjb) {
    super(fitxerEjb);
  }

  @Override
  public FitxerJPA createEmptyFile() {    
    return new FitxerJPA();
  }

}
