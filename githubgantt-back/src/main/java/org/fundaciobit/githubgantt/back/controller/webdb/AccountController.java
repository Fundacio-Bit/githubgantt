package org.fundaciobit.githubgantt.back.controller.webdb;

import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.validation.ValidationWebUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.fundaciobit.githubgantt.back.form.webdb.*;
import org.fundaciobit.githubgantt.back.form.webdb.AccountForm;

import org.fundaciobit.githubgantt.back.validator.webdb.AccountWebValidator;

import org.fundaciobit.githubgantt.persistence.AccountJPA;
import org.fundaciobit.githubgantt.model.entity.Account;
import org.fundaciobit.githubgantt.model.fields.*;

/**
 * Controller per gestionar un Account
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/account")
@SessionAttributes(types = { AccountForm.class, AccountFilterForm.class })
public class AccountController
    extends org.fundaciobit.githubgantt.back.controller.GithubGanttBaseController<Account, java.lang.Long> implements AccountFields {

  @EJB(mappedName = org.fundaciobit.githubgantt.ejb.AccountService.JNDI_NAME)
  protected org.fundaciobit.githubgantt.ejb.AccountService accountEjb;

  @Autowired
  private AccountWebValidator accountWebValidator;

  @Autowired
  protected AccountRefList accountRefList;

  /**
   * Llistat de totes Account
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    AccountFilterForm ff;
    ff = (AccountFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Account de forma paginada
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.GET)
  public ModelAndView llistatPaginat(HttpServletRequest request,
    HttpServletResponse response, @PathVariable Integer pagina)
      throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileList());
    llistat(mav, request, getAccountFilterForm(pagina, mav, request));
    return mav;
  }

  public AccountFilterForm getAccountFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    AccountFilterForm accountFilterForm;
    accountFilterForm = (AccountFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(accountFilterForm == null) {
      accountFilterForm = new AccountFilterForm();
      accountFilterForm.setContexte(getContextWeb());
      accountFilterForm.setEntityNameCode(getEntityNameCode());
      accountFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      accountFilterForm.setNou(true);
    } else {
      accountFilterForm.setNou(false);
    }
    accountFilterForm.setPage(pagina == null ? 1 : pagina);
    return accountFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Account de forma paginada
   * 
   * @param request
   * @param pagina
   * @param filterForm
   * @return
   * @throws I18NException
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.POST)
  public ModelAndView llistatPaginat(HttpServletRequest request,
      HttpServletResponse response,@PathVariable Integer pagina,
      @ModelAttribute AccountFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getAccountFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Account de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Account> llistat(ModelAndView mav, HttpServletRequest request,
     AccountFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Account> account = processarLlistat(accountEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("accountItems", account);

    mav.addObject("accountFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, account, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, account);

    return account;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(AccountFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Account> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    AccountFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Account> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ACCOUNT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Account
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearAccountGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    AccountForm accountForm = getAccountForm(null, false, request, mav);
    mav.addObject("accountForm" ,accountForm);
    fillReferencesForForm(accountForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public AccountForm getAccountForm(AccountJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    AccountForm accountForm;
    if(_jpa == null) {
      accountForm = new AccountForm(new AccountJPA(), true);
    } else {
      accountForm = new AccountForm(_jpa, false);
      accountForm.setView(__isView);
    }
    accountForm.setContexte(getContextWeb());
    accountForm.setEntityNameCode(getEntityNameCode());
    accountForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return accountForm;
  }

  public void fillReferencesForForm(AccountForm accountForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou Account
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearAccountPost(@ModelAttribute AccountForm accountForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    AccountJPA account = accountForm.getAccount();

    try {
      preValidate(request, accountForm, result);
      getWebValidator().validate(accountForm, result);
      postValidate(request,accountForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        account = create(request, account);
        createMessageSuccess(request, "success.creation", account.getAccountID());
        accountForm.setAccount(account);
        return getRedirectWhenCreated(request, accountForm);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{accountID}", method = RequestMethod.GET)
  public ModelAndView veureAccountGet(@PathVariable("accountID") java.lang.Long accountID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAccountGet(accountID,
        request, response, true);
  }


  protected ModelAndView editAndViewAccountGet(@PathVariable("accountID") java.lang.Long accountID,
      HttpServletRequest request,
      HttpServletResponse response, boolean __isView) throws I18NException {
    if((!__isView) && !isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    } else {
      if(__isView && !isActiveFormView()) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
      }
    }
    AccountJPA account = findByPrimaryKey(request, accountID);

    if (account == null) {
      createMessageWarning(request, "error.notfound", accountID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, accountID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      AccountForm accountForm = getAccountForm(account, __isView, request, mav);
      accountForm.setView(__isView);
      if(__isView) {
        accountForm.setAllFieldsReadOnly(ALL_ACCOUNT_FIELDS);
        accountForm.setSaveButtonVisible(false);
        accountForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(accountForm, request, mav);
      mav.addObject("accountForm", accountForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Account existent
   */
  @RequestMapping(value = "/{accountID}/edit", method = RequestMethod.GET)
  public ModelAndView editarAccountGet(@PathVariable("accountID") java.lang.Long accountID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAccountGet(accountID,
        request, response, false);
  }



  /**
   * Editar un Account existent
   */
  @RequestMapping(value = "/{accountID}/edit", method = RequestMethod.POST)
  public String editarAccountPost(@ModelAttribute AccountForm accountForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    AccountJPA account = accountForm.getAccount();

    try {
      preValidate(request, accountForm, result);
      getWebValidator().validate(accountForm, result);
      postValidate(request, accountForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        account = update(request, account);
        createMessageSuccess(request, "success.modification", account.getAccountID());
        status.setComplete();
        return getRedirectWhenModified(request, accountForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          account.getAccountID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, accountForm, __e);
    }

  }


  /**
   * Eliminar un Account existent
   */
  @RequestMapping(value = "/{accountID}/delete")
  public String eliminarAccount(@PathVariable("accountID") java.lang.Long accountID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Account account = accountEjb.findByPrimaryKey(accountID);
      if (account == null) {
        String __msg =createMessageError(request, "error.notfound", accountID);
        return getRedirectWhenDelete(request, accountID, new Exception(__msg));
      } else {
        delete(request, account);
        createMessageSuccess(request, "success.deleted", accountID);
        return getRedirectWhenDelete(request, accountID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", accountID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, accountID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute AccountFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarAccount(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(request, null,null);
  }

  return redirect;
}



public java.lang.Long stringToPK(String value) {
  return java.lang.Long.parseLong(value, 10);
}

  @Override
  public String[] getArgumentsMissatge(Object __accountID, Throwable e) {
    java.lang.Long accountID = (java.lang.Long)__accountID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (accountID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(accountID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "account.account";
  }

  public String getEntityNameCodePlural() {
    return "account.account.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("account.accountID");
  }

  @InitBinder("accountFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("accountForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("accountID");

  }

  public AccountWebValidator getWebValidator() {
    return accountWebValidator;
  }


  public void setWebValidator(AccountWebValidator __val) {
    if (__val != null) {
      this.accountWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Account
   */
  @RequestMapping(value = "/{accountID}/cancel")
  public String cancelAccount(@PathVariable("accountID") java.lang.Long accountID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, accountID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // Mètodes a sobreescriure 

  public boolean isActiveList() {
    return true;
  }


  public boolean isActiveFormNew() {
    return true;
  }


  public boolean isActiveFormEdit() {
    return true;
  }


  public boolean isActiveDelete() {
    return true;
  }


  public boolean isActiveFormView() {
    return isActiveFormEdit();
  }


  public void preValidate(HttpServletRequest request,AccountForm accountForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,AccountForm accountForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, AccountFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, AccountFilterForm filterForm,  List<Account> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, AccountForm accountForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, AccountForm accountForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long accountID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long accountID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "accountFormWebDB";
  }

  public String getTileList() {
    return "accountListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "AccountWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public AccountJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long accountID) throws I18NException {
    return (AccountJPA) accountEjb.findByPrimaryKey(accountID);
  }


  public AccountJPA create(HttpServletRequest request, AccountJPA account)
    throws Exception,I18NException, I18NValidationException {
    return (AccountJPA) accountEjb.create(account);
  }


  public AccountJPA update(HttpServletRequest request, AccountJPA account)
    throws Exception,I18NException, I18NValidationException {
    return (AccountJPA) accountEjb.update(account);
  }


  public void delete(HttpServletRequest request, Account account) throws Exception,I18NException {
    accountEjb.delete(account);
  }

} // Final de Classe

