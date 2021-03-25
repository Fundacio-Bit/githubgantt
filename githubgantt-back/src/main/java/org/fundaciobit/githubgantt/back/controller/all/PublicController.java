package org.fundaciobit.githubgantt.back.controller.all;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.githubgantt.back.gantt.GitHubGantt;
import org.fundaciobit.githubgantt.model.entity.Account;
import org.fundaciobit.githubgantt.model.entity.Gantt;
import org.fundaciobit.githubgantt.model.fields.AccountFields;
import org.fundaciobit.githubgantt.model.fields.GanttFields;
import org.kohsuke.github.GHProject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @autor anadal
 * 
 */
@Controller
public class PublicController {

    protected final Logger log = Logger.getLogger(getClass());

    @EJB(mappedName = org.fundaciobit.githubgantt.ejb.GanttService.JNDI_NAME)
    protected org.fundaciobit.githubgantt.ejb.GanttService ganttEjb;
    
    
    @EJB(mappedName = org.fundaciobit.githubgantt.ejb.AccountService.JNDI_NAME)
    protected org.fundaciobit.githubgantt.ejb.AccountService accountEjb;

    @RequestMapping(value = "/public/index.html")
    public ModelAndView principal(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Boolean initialized = (Boolean) session.getAttribute("inicialitzat");

        if (initialized == null) {
            HtmlUtils.saveMessageInfo(request, "MessageInfo : Benvingut a GithubGantt");
            session.setAttribute("inicialitzat", true);
        }
        
        
        List<Gantt> gantts = ganttEjb.select(new OrderBy(GanttFields.NOM, OrderType.DESC));
        
        ModelAndView mav = new ModelAndView("homepublic"); 
        

        
        mav.addObject("gantts", gantts);

        return mav;

    }
    
    @RequestMapping(value = "/public/project/{ganttID}", method = RequestMethod.GET)
    public String project(HttpSession session, HttpServletRequest request, 
            HttpServletResponse response, @PathVariable Long ganttID) throws Exception {
        
        Long accountID = ganttEjb.executeQueryOne(GanttFields.ACCOUNTID,GanttFields.GANTTID.equal(ganttID));
        
       
        Gantt gantt;
        Account account;
        {
           List<Gantt> gantts = ganttEjb.select(GanttFields.GANTTID.equal(ganttID));
        
        
           gantt = gantts.get(0);
          List<Account> accounts = accountEjb.select(AccountFields.ACCOUNTID.equal(accountID));
          
          account = accounts.get(0);
       }
       
       GitHubGantt githubConnection = new GitHubGantt(account.getUsername(), account.getToken());
       
       GHProject project = githubConnection.getProject(gantt.getOrganization(), gantt.getRepository(), Long.parseLong(gantt.getProject()));
       
       
       
       
       return "redirect:https://github.com/" + gantt.getOrganization() + "/" + gantt.getRepository() + "/projects/" + project.getNumber();
    
    }
    
    
    
    @RequestMapping(value = "/public/gantt/{ganttID}", method = RequestMethod.GET)
    public ModelAndView gantt(HttpSession session, HttpServletRequest request, 
            HttpServletResponse response, @PathVariable Long ganttID) throws Exception {


        Gantt gantt;
        Account account;
        {
           List<Gantt> gantts = ganttEjb.select(GanttFields.GANTTID.equal(ganttID));
        
        
           gantt = gantts.get(0);
        
        
           List<Account> accounts = accountEjb.select(AccountFields.ACCOUNTID.equal(gantt.getAccountID()));
           
           account = accounts.get(0);
        }
        
        GitHubGantt ghg = new GitHubGantt(account.getUsername(), account.getToken());
        
        Map<String, Object> parameters = ghg.generateGanttData(gantt.getOrganization(), gantt.getRepository(), Long.parseLong(gantt.getProject()),
                gantt.getNumeroProgramadors(), gantt.getStartDate());

        
        
        
        ModelAndView mav = new ModelAndView("gantt"); 

        mav.addObject("gantt", gantt);
        
        mav.addAllObjects(parameters);

        return mav;
        
        
    }

}
