package org.fundaciobit.githubgantt.model.dao;

import org.fundaciobit.githubgantt.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IGanttManager extends org.fundaciobit.genapp.common.query.ITableManager<Gantt, Long> {


	public Gantt create( java.lang.String _nom_, long _accountID_, java.lang.String _organization_, java.lang.String _repository_, java.lang.String _projectNom_, java.lang.String _project_, java.sql.Timestamp _startDate_, int _numeroProgramadors_) throws I18NException;

	public Gantt findByPrimaryKey(long _ganttID_);

	public void delete(long _ganttID_);

}
