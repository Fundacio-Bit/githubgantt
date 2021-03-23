package org.fundaciobit.githubgantt.back.form;

import org.fundaciobit.genapp.common.web.form.BaseForm;

/**
 * 
 * @author anadal
 *
 */
public abstract class GithubGanttBaseForm extends BaseForm {

  public GithubGanttBaseForm() {
  }
  
  public GithubGanttBaseForm(boolean nou) {
    super(nou);
  }
  
  public GithubGanttBaseForm(GithubGanttBaseForm __toClone) {
    super(__toClone);
  }
  
}
