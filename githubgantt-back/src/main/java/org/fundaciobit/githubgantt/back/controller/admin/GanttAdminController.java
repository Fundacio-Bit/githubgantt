package org.fundaciobit.githubgantt.back.controller.admin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.githubgantt.back.controller.webdb.GanttController;
import org.fundaciobit.githubgantt.back.form.webdb.GanttFilterForm;
import org.fundaciobit.githubgantt.back.form.webdb.GanttForm;
import org.fundaciobit.githubgantt.back.gantt.GitHubGantt;
import org.fundaciobit.githubgantt.back.utils.GanttAdminWebValidator;
import org.fundaciobit.githubgantt.back.validator.webdb.GanttWebValidator;
import org.fundaciobit.githubgantt.model.entity.Account;
import org.fundaciobit.githubgantt.model.entity.Gantt;
import org.fundaciobit.githubgantt.model.fields.GanttFields;
import org.fundaciobit.githubgantt.persistence.GanttJPA;
import org.kohsuke.github.GHProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/gantt")
@SessionAttributes(types = { GanttForm.class, GanttFilterForm.class })
public class GanttAdminController extends GanttController {

    @EJB(mappedName = org.fundaciobit.githubgantt.ejb.AccountService.JNDI_NAME)
    protected org.fundaciobit.githubgantt.ejb.AccountService accountEjb;

    @Autowired
    private GanttAdminWebValidator ganttAdminWebValidator;

    @Override
    public String getTileForm() {
        return "ganttFormAdmin";
    }

    @Override
    public String getTileList() {
        return "ganttListAdmin";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "GanttAdmin_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return null;
    }

    // @PostConstruct
    // public void init() {
    @Override
    public GanttWebValidator getWebValidator() {
        return ganttAdminWebValidator;
    }

    protected GitHubGantt getGitHubGantt(Long accountID) throws I18NException {
        Account account = accountEjb.findByPrimaryKey(accountID);

        GitHubGantt ghg = new GitHubGantt(account.getUsername(), account.getToken());
        return ghg;
    }

    @Override
    public GanttFilterForm getGanttFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        GanttFilterForm ganttFilterForm = super.getGanttFilterForm(pagina, mav, request);

        Set<Field<?>> hiddens = new HashSet<Field<?>>(Arrays.asList(GanttFields.ALL_GANTT_FIELDS));

        hiddens.remove(NOM);
        hiddens.remove(PROJECTNOM);

        ganttFilterForm.setHiddenFields(hiddens);

        //ganttFilterForm.setEditButtonVisible(false);

        return ganttFilterForm;
    }

    @Override
    public GanttForm getGanttForm(GanttJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        GanttForm ganttForm = super.getGanttForm(_jpa, __isView, request, mav);

        if (ganttForm.isNou()) {

            log.info("  NOUUUUUUUUU ");

            Set<Field<?>> hiddens = new HashSet<Field<?>>(Arrays.asList(GanttFields.ALL_GANTT_FIELDS));

            hiddens.remove(ACCOUNTID);

            ganttForm.setHiddenFields(hiddens);
        } else {

            log.info("  EDITTTTTTT ");

            Set<Field<?>> readOnly = new HashSet<Field<?>>(Arrays.asList(GanttFields.ALL_GANTT_FIELDS));
            readOnly.remove(NOM);
            readOnly.remove(NUMEROPROGRAMADORS);
            readOnly.remove(STARTDATE);
            ganttForm.setReadOnlyFields(readOnly);

        }

        return ganttForm;
    }

    @Override
    public void postValidate(HttpServletRequest request, GanttForm ganttForm, BindingResult result)
            throws I18NException {

        
        log.info(" POST VALIDATE :: " + ganttForm.isNou());
        
        log.info(" POST VALIDATE :: ganttForm.getGantt().getAccountID()  ===>  " + ganttForm.getGantt().getAccountID());

        Set<Field<?>> hiddens = new HashSet<Field<?>>(Arrays.asList(GanttFields.ALL_GANTT_FIELDS));

        Set<Field<?>> readOnly = new HashSet<Field<?>>();

        Gantt gantt = ganttForm.getGantt();

        log.info("gantt.getAccountID() = " + gantt.getAccountID());

        if (gantt.getAccountID() == 0) {
            hiddens.remove(ACCOUNTID);

        } else if (gantt.getOrganization() == null) {

            readOnly.add(ACCOUNTID);
            hiddens.remove(ACCOUNTID);

            hiddens.remove(ORGANIZATION);
            ganttForm.setHiddenFields(hiddens);
            ganttForm.setListOfValuesForOrganization(getReferenceListForOrganization(request, null, ganttForm, null));

            // -------------------------------

            // result.getAllErrors().clear();

            result.rejectValue(ORGANIZATION.fullName, "genapp.validation.required",
                    new Object[] { I18NUtils.tradueix(get(ORGANIZATION)) }, null);

            /*
             * List<Field<?>> ignoreFields =new ArrayList<Field<?>>();
             * ignoreFields.add(NUMEROPROGRAMADORS); ignoreFields.add(STARTDATE);
             * ignoreFields.add(PROJECT); ignoreFields.add(PROJECTNOM);
             * 
             * List<FieldError> errors = result.getFieldErrors(); Set<FieldError> toDelete =
             * new HashSet<FieldError>();
             * 
             * for (FieldError fieldError : errors) { for (Field<?> field : ignoreFields) {
             * log.info("   " + fieldError.getField() + "   <=>   " + field.fullName); if
             * (fieldError.getField().equals(field.fullName)) { toDelete.add(fieldError); }
             * } }
             * 
             * for (FieldError fieldError : toDelete) {
             * result.getAllErrors().remove(fieldError); }
             */

        } else if (gantt.getRepository() == null) {

            readOnly.add(ACCOUNTID);
            hiddens.remove(ACCOUNTID);

            readOnly.add(ORGANIZATION);
            hiddens.remove(ORGANIZATION);

            hiddens.remove(REPOSITORY);
            ganttForm.setHiddenFields(hiddens);
            ganttForm.setListOfValuesForRepository(getReferenceListForRepository(request, null, ganttForm, null));

            result.rejectValue(REPOSITORY.fullName, "genapp.validation.required",
                    new Object[] { I18NUtils.tradueix(get(REPOSITORY)) }, null);

        } else if (gantt.getProject() == null) {

            readOnly.add(ACCOUNTID);
            hiddens.remove(ACCOUNTID);

            readOnly.add(ORGANIZATION);
            hiddens.remove(ORGANIZATION);

            readOnly.add(REPOSITORY);
            hiddens.remove(REPOSITORY);

            hiddens.remove(PROJECT);
            ganttForm.setHiddenFields(hiddens);
            ganttForm.setListOfValuesForProject(getReferenceListForProject(request, null, ganttForm, null));

            result.rejectValue(PROJECT.fullName, "genapp.validation.required",
                    new Object[] { I18NUtils.tradueix(get(PROJECT)) }, null);

        } else {

            readOnly.add(ACCOUNTID);
            hiddens.remove(ACCOUNTID);

            readOnly.add(ORGANIZATION);
            hiddens.remove(ORGANIZATION);

            readOnly.add(REPOSITORY);
            hiddens.remove(REPOSITORY);

            {

                log.info(" XXXXXXXXXXXXXXXXXXXXX  gantt.getProject() ID ===> " + gantt.getProject());

                GitHubGantt ghg = getGitHubGantt(gantt.getAccountID());
                GHProject proj = ghg.getProject(gantt.getOrganization(), gantt.getRepository(),
                        Long.parseLong(gantt.getProject()));
                /*
                 * Map<String, String> projects =
                 * Utils.listToMap(ganttForm.getListOfValuesForProject()); String projectNom =
                 * projects.get(String.valueOf(gantt.getProject()));
                 */
                if (gantt.getProjectNom() == null) {
                            String projectNom = proj.getName();
                  gantt.setProjectNom(projectNom);
                  log.info(" XXXXXXXXXXXXXXXXXXXXX  gantt.getProject() projectNom ===> " + projectNom);
                }
                
                if (gantt.getNom() == null) {

                  gantt.setNom(gantt.getOrganization() + "::" + gantt.getRepository() + "::" + gantt.getProjectNom());

                  log.info(" XXXXXXXXXXXXXXXXXXXXX  gantt.getProject() Nom ===> " + gantt.getNom());
                }
            }

            hiddens.remove(PROJECT);
            readOnly.add(PROJECT);

            hiddens.remove(PROJECTNOM);
            readOnly.add(PROJECTNOM);

            hiddens.remove(NOM);
            hiddens.remove(NUMEROPROGRAMADORS);
            hiddens.remove(STARTDATE);

            ganttForm.setHiddenFields(hiddens);

            ValidationUtils.rejectIfEmpty(result, NOM.fullName, "genapp.validation.required",
                    new Object[] { I18NUtils.tradueix(get(NOM)) });
            ValidationUtils.rejectIfEmpty(result, NUMEROPROGRAMADORS.fullName, "genapp.validation.required",
                    new Object[] { I18NUtils.tradueix(get(NUMEROPROGRAMADORS)) });
            ValidationUtils.rejectIfEmpty(result, STARTDATE.fullName, "genapp.validation.required",
                    new Object[] { I18NUtils.tradueix(get(STARTDATE)) }, null);

        }

        ganttForm.setReadOnlyFields(readOnly);

    }

    @Override
    public List<StringKeyValue> getReferenceListForOrganization(HttpServletRequest request, ModelAndView mav,
            GanttForm ganttForm, Where where) throws I18NException {
        if (ganttForm.isHiddenField(ORGANIZATION)) {
            log.info("getReferenceListForOrganization IS HIDDEN !!!");
            return EMPTY_STRINGKEYVALUE_LIST;
        }

        if (ganttForm.isNou() && !ganttForm.isReadOnlyField(ORGANIZATION)) {
            log.info("getReferenceListForOrganization CALCULAM ORGANIZATIONS de ACCOUNT "
                    + ganttForm.getGantt().getAccountID() + "!!!");

            GitHubGantt ghg = getGitHubGantt(ganttForm.getGantt().getAccountID());

            Set<String> orgs = ghg.getOrganizations();

            List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
            for (String org : orgs) {
                __tmp.add(new StringKeyValue(org, org));
            }

            return __tmp;
        } else {
            log.info("getReferenceListForOrganization ORGANIZATION A PINYO FIX: "
                    + ganttForm.getGantt().getOrganization() + "!!!");
            List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
            String org = ganttForm.getGantt().getOrganization();
            __tmp.add(new StringKeyValue(org, org));
            return __tmp;
        }

    }

    public List<StringKeyValue> getReferenceListForRepository(HttpServletRequest request, ModelAndView mav,
            GanttForm ganttForm, Where where) throws I18NException {
        if (ganttForm.isHiddenField(REPOSITORY)) {
            return EMPTY_STRINGKEYVALUE_LIST;
        }

        if (ganttForm.isNou() && !ganttForm.isReadOnlyField(REPOSITORY)) {
            log.info("getReferenceListForRepository CALCULAM REPOSITORY de ACCOUNT "
                    + ganttForm.getGantt().getAccountID() + "!!!");

            GitHubGantt ghg = getGitHubGantt(ganttForm.getGantt().getAccountID());

            Set<String> values = ghg.getRepositories(ganttForm.getGantt().getOrganization());

            List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
            for (String val : values) {
                __tmp.add(new StringKeyValue(val, val));
            }

            return __tmp;
        } else {
            String value = ganttForm.getGantt().getRepository();
            log.info("getReferenceListForRepositoryn REPOSITORY A PINYO FIX: " + value + "!!!");
            List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
            __tmp.add(new StringKeyValue(value, value));
            return __tmp;
        }
    }

    @Override
    public List<StringKeyValue> getReferenceListForProject(HttpServletRequest request, ModelAndView mav,
            GanttForm ganttForm, Where where) throws I18NException {
        if (ganttForm.isHiddenField(PROJECT)) {
            return EMPTY_STRINGKEYVALUE_LIST;
        }

        GitHubGantt ghg = getGitHubGantt(ganttForm.getGantt().getAccountID());

        if (ganttForm.isNou() && !ganttForm.isReadOnlyField(PROJECT)) {
            log.info("getReferenceListForProject CALCULAM PROJECT de ACCOUNT " + ganttForm.getGantt().getAccountID()
                    + "!!!");

            Map<Long, String> values = ghg.getProjects(ganttForm.getGantt().getOrganization(),
                    ganttForm.getGantt().getRepository());

            List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
            for (Long pID : values.keySet()) {
                __tmp.add(new StringKeyValue(String.valueOf(pID), values.get(pID)));
            }

            return __tmp;
        } else {
            String value = ganttForm.getGantt().getProject();
            log.info("getReferenceListForProject  PROJECT A PINYO FIX: " + value + "!!!");

            GHProject pro = ghg.getProject(ganttForm.getGantt().getOrganization(), ganttForm.getGantt().getRepository(),
                    Long.parseLong(value));

            List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
            __tmp.add(new StringKeyValue(value, pro.getName()));
            return __tmp;
        }

    }

    /*
     * protected List<StringKeyValue>
     * getReferenceListForOrganization(HttpServletRequest request, Long accountID)
     * throws I18NException {
     * 
     * GitHubGantt ghg = getGitHubGantt(accountID);
     * 
     * Set<String> orgs = ghg.getOrganizations();
     * 
     * List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>(); for
     * (String org : orgs) { __tmp.add(new StringKeyValue(org, org)); }
     * 
     * return __tmp; }
     * 
     * 
     * 
     * @Override public List<StringKeyValue>
     * getReferenceListForOrganization(HttpServletRequest request, ModelAndView mav,
     * GanttFilterForm ganttFilterForm, List<Gantt> list, Map<Field<?>, GroupByItem>
     * _groupByItemsMap, Where where) throws I18NException {
     * 
     * if (ganttFilterForm.isHiddenField(ORGANIZATION) &&
     * !ganttFilterForm.isGroupByField(ORGANIZATION)) { return
     * EMPTY_STRINGKEYVALUE_LIST; }
     * 
     * throw new I18NException("genapp.comodi",
     * "Aquest mètode no es pot cridar !!!!!"); // Where _w = null; // return
     * getReferenceListForOrganization(request, mav, Where.AND(where,_w)); }
     * 
     * 
     * @Override public List<StringKeyValue>
     * getReferenceListForOrganization(HttpServletRequest request, ModelAndView mav,
     * Where where) throws I18NException {
     * 
     * throw new I18NException("genapp.comodi",
     * "Aquest mètode no es pot cridar !!!!!");
     * 
     * }
     */

}
