package org.fundaciobit.githubgantt.back.controller.webdb;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.utils.Utils;
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
import org.fundaciobit.githubgantt.back.form.webdb.GanttForm;

import org.fundaciobit.githubgantt.back.validator.webdb.GanttWebValidator;

import org.fundaciobit.githubgantt.persistence.GanttJPA;
import org.fundaciobit.githubgantt.model.entity.Gantt;
import org.fundaciobit.githubgantt.model.fields.*;

/**
 * Controller per gestionar un Gantt
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/gantt")
@SessionAttributes(types = { GanttForm.class, GanttFilterForm.class })
public class GanttController
    extends org.fundaciobit.githubgantt.back.controller.GithubGanttBaseController<Gantt, java.lang.Long> implements GanttFields {

  @EJB(mappedName = org.fundaciobit.githubgantt.ejb.GanttService.JNDI_NAME)
  protected org.fundaciobit.githubgantt.ejb.GanttService ganttEjb;

  @Autowired
  private GanttWebValidator ganttWebValidator;

  @Autowired
  protected GanttRefList ganttRefList;

  // References 
  @Autowired
  protected AccountRefList accountRefList;

  /**
   * Llistat de totes Gantt
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    GanttFilterForm ff;
    ff = (GanttFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Gantt de forma paginada
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
    llistat(mav, request, getGanttFilterForm(pagina, mav, request));
    return mav;
  }

  public GanttFilterForm getGanttFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    GanttFilterForm ganttFilterForm;
    ganttFilterForm = (GanttFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(ganttFilterForm == null) {
      ganttFilterForm = new GanttFilterForm();
      ganttFilterForm.setContexte(getContextWeb());
      ganttFilterForm.setEntityNameCode(getEntityNameCode());
      ganttFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      ganttFilterForm.setNou(true);
    } else {
      ganttFilterForm.setNou(false);
    }
    ganttFilterForm.setPage(pagina == null ? 1 : pagina);
    return ganttFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Gantt de forma paginada
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
      @ModelAttribute GanttFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getGanttFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Gantt de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Gantt> llistat(ModelAndView mav, HttpServletRequest request,
     GanttFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Gantt> gantt = processarLlistat(ganttEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("ganttItems", gantt);

    mav.addObject("ganttFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, gantt, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, gantt);

    return gantt;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(GanttFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Gantt> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field accountID
    {
      _listSKV = getReferenceListForAccountID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfAccountForAccountID(_tmp);
      if (filterForm.getGroupByFields().contains(ACCOUNTID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ACCOUNTID, false);
      };
    }

    // Field organization
    {
      _listSKV = getReferenceListForOrganization(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForOrganization(_tmp);
      if (filterForm.getGroupByFields().contains(ORGANIZATION)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ORGANIZATION, false);
      };
    }

    // Field repository
    {
      _listSKV = getReferenceListForRepository(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForRepository(_tmp);
      if (filterForm.getGroupByFields().contains(REPOSITORY)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, REPOSITORY, false);
      };
    }

    // Field project
    {
      _listSKV = getReferenceListForProject(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForProject(_tmp);
      if (filterForm.getGroupByFields().contains(PROJECT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PROJECT, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    GanttFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Gantt> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_GANTT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ACCOUNTID, filterForm.getMapOfAccountForAccountID());
    __mapping.put(ORGANIZATION, filterForm.getMapOfValuesForOrganization());
    __mapping.put(REPOSITORY, filterForm.getMapOfValuesForRepository());
    __mapping.put(PROJECT, filterForm.getMapOfValuesForProject());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Gantt
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearGanttGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    GanttForm ganttForm = getGanttForm(null, false, request, mav);
    mav.addObject("ganttForm" ,ganttForm);
    fillReferencesForForm(ganttForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public GanttForm getGanttForm(GanttJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    GanttForm ganttForm;
    if(_jpa == null) {
      ganttForm = new GanttForm(new GanttJPA(), true);
    } else {
      ganttForm = new GanttForm(_jpa, false);
      ganttForm.setView(__isView);
    }
    ganttForm.setContexte(getContextWeb());
    ganttForm.setEntityNameCode(getEntityNameCode());
    ganttForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return ganttForm;
  }

  public void fillReferencesForForm(GanttForm ganttForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (ganttForm.getListOfAccountForAccountID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForAccountID(request, mav, ganttForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      ganttForm.setListOfAccountForAccountID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (ganttForm.getListOfValuesForOrganization() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForOrganization(request, mav, ganttForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      ganttForm.setListOfValuesForOrganization(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (ganttForm.getListOfValuesForRepository() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForRepository(request, mav, ganttForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      ganttForm.setListOfValuesForRepository(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (ganttForm.getListOfValuesForProject() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForProject(request, mav, ganttForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      ganttForm.setListOfValuesForProject(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Gantt
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearGanttPost(@ModelAttribute GanttForm ganttForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    GanttJPA gantt = ganttForm.getGantt();

    try {
      preValidate(request, ganttForm, result);
      getWebValidator().validate(ganttForm, result);
      postValidate(request,ganttForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        gantt = create(request, gantt);
        createMessageSuccess(request, "success.creation", gantt.getGanttID());
        ganttForm.setGantt(gantt);
        return getRedirectWhenCreated(request, ganttForm);
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

  @RequestMapping(value = "/view/{ganttID}", method = RequestMethod.GET)
  public ModelAndView veureGanttGet(@PathVariable("ganttID") java.lang.Long ganttID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewGanttGet(ganttID,
        request, response, true);
  }


  protected ModelAndView editAndViewGanttGet(@PathVariable("ganttID") java.lang.Long ganttID,
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
    GanttJPA gantt = findByPrimaryKey(request, ganttID);

    if (gantt == null) {
      createMessageWarning(request, "error.notfound", ganttID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, ganttID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      GanttForm ganttForm = getGanttForm(gantt, __isView, request, mav);
      ganttForm.setView(__isView);
      if(__isView) {
        ganttForm.setAllFieldsReadOnly(ALL_GANTT_FIELDS);
        ganttForm.setSaveButtonVisible(false);
        ganttForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(ganttForm, request, mav);
      mav.addObject("ganttForm", ganttForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Gantt existent
   */
  @RequestMapping(value = "/{ganttID}/edit", method = RequestMethod.GET)
  public ModelAndView editarGanttGet(@PathVariable("ganttID") java.lang.Long ganttID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewGanttGet(ganttID,
        request, response, false);
  }



  /**
   * Editar un Gantt existent
   */
  @RequestMapping(value = "/{ganttID}/edit", method = RequestMethod.POST)
  public String editarGanttPost(@ModelAttribute GanttForm ganttForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    GanttJPA gantt = ganttForm.getGantt();

    try {
      preValidate(request, ganttForm, result);
      getWebValidator().validate(ganttForm, result);
      postValidate(request, ganttForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        gantt = update(request, gantt);
        createMessageSuccess(request, "success.modification", gantt.getGanttID());
        status.setComplete();
        return getRedirectWhenModified(request, ganttForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          gantt.getGanttID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, ganttForm, __e);
    }

  }


  /**
   * Eliminar un Gantt existent
   */
  @RequestMapping(value = "/{ganttID}/delete")
  public String eliminarGantt(@PathVariable("ganttID") java.lang.Long ganttID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Gantt gantt = ganttEjb.findByPrimaryKey(ganttID);
      if (gantt == null) {
        String __msg =createMessageError(request, "error.notfound", ganttID);
        return getRedirectWhenDelete(request, ganttID, new Exception(__msg));
      } else {
        delete(request, gantt);
        createMessageSuccess(request, "success.deleted", ganttID);
        return getRedirectWhenDelete(request, ganttID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", ganttID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, ganttID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute GanttFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarGantt(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __ganttID, Throwable e) {
    java.lang.Long ganttID = (java.lang.Long)__ganttID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (ganttID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(ganttID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "gantt.gantt";
  }

  public String getEntityNameCodePlural() {
    return "gantt.gantt.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("gantt.ganttID");
  }

  @InitBinder("ganttFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("ganttForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("ganttID");

  }

  public GanttWebValidator getWebValidator() {
    return ganttWebValidator;
  }


  public void setWebValidator(GanttWebValidator __val) {
    if (__val != null) {
      this.ganttWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Gantt
   */
  @RequestMapping(value = "/{ganttID}/cancel")
  public String cancelGantt(@PathVariable("ganttID") java.lang.Long ganttID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, ganttID);
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


  public List<StringKeyValue> getReferenceListForAccountID(HttpServletRequest request,
       ModelAndView mav, GanttForm ganttForm, Where where)  throws I18NException {
    if (ganttForm.isHiddenField(ACCOUNTID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (ganttForm.isReadOnlyField(ACCOUNTID)) {
      _where = AccountFields.ACCOUNTID.equal(ganttForm.getGantt().getAccountID());
    }
    return getReferenceListForAccountID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForAccountID(HttpServletRequest request,
       ModelAndView mav, GanttFilterForm ganttFilterForm,
       List<Gantt> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (ganttFilterForm.isHiddenField(ACCOUNTID)
      && !ganttFilterForm.isGroupByField(ACCOUNTID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ACCOUNTID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Gantt _item : list) {
        _pkList.add(_item.getAccountID());
        }
        _w = AccountFields.ACCOUNTID.in(_pkList);
      }
    return getReferenceListForAccountID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForAccountID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return accountRefList.getReferenceList(AccountFields.ACCOUNTID, where );
  }


  public List<StringKeyValue> getReferenceListForOrganization(HttpServletRequest request,
       ModelAndView mav, GanttForm ganttForm, Where where)  throws I18NException {
    if (ganttForm.isHiddenField(ORGANIZATION)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForOrganization(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForOrganization(HttpServletRequest request,
       ModelAndView mav, GanttFilterForm ganttFilterForm,
       List<Gantt> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (ganttFilterForm.isHiddenField(ORGANIZATION)
      && !ganttFilterForm.isGroupByField(ORGANIZATION)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForOrganization(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForOrganization(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForRepository(HttpServletRequest request,
       ModelAndView mav, GanttForm ganttForm, Where where)  throws I18NException {
    if (ganttForm.isHiddenField(REPOSITORY)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForRepository(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForRepository(HttpServletRequest request,
       ModelAndView mav, GanttFilterForm ganttFilterForm,
       List<Gantt> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (ganttFilterForm.isHiddenField(REPOSITORY)
      && !ganttFilterForm.isGroupByField(REPOSITORY)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForRepository(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForRepository(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForProject(HttpServletRequest request,
       ModelAndView mav, GanttForm ganttForm, Where where)  throws I18NException {
    if (ganttForm.isHiddenField(PROJECT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForProject(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForProject(HttpServletRequest request,
       ModelAndView mav, GanttFilterForm ganttFilterForm,
       List<Gantt> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (ganttFilterForm.isHiddenField(PROJECT)
      && !ganttFilterForm.isGroupByField(PROJECT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForProject(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForProject(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    return __tmp;
  }


  public void preValidate(HttpServletRequest request,GanttForm ganttForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,GanttForm ganttForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, GanttFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, GanttFilterForm filterForm,  List<Gantt> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, GanttForm ganttForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, GanttForm ganttForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long ganttID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long ganttID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "ganttFormWebDB";
  }

  public String getTileList() {
    return "ganttListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "GanttWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public GanttJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long ganttID) throws I18NException {
    return (GanttJPA) ganttEjb.findByPrimaryKey(ganttID);
  }


  public GanttJPA create(HttpServletRequest request, GanttJPA gantt)
    throws Exception,I18NException, I18NValidationException {
    return (GanttJPA) ganttEjb.create(gantt);
  }


  public GanttJPA update(HttpServletRequest request, GanttJPA gantt)
    throws Exception,I18NException, I18NValidationException {
    return (GanttJPA) ganttEjb.update(gantt);
  }


  public void delete(HttpServletRequest request, Gantt gantt) throws Exception,I18NException {
    ganttEjb.delete(gantt);
  }

} // Final de Classe

