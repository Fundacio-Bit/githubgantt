package org.fundaciobit.githubgantt.model.dao;

import org.fundaciobit.githubgantt.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IAccountManager extends org.fundaciobit.genapp.common.query.ITableManager<Account, Long> {


	public Account create( java.lang.String _nom_, java.lang.String _username_, java.lang.String _token_) throws I18NException;

	public Account findByPrimaryKey(long _accountID_);

	public void delete(long _accountID_);

}
