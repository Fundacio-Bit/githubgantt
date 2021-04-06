package org.fundaciobit.githubgantt.back.gantt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.fundaciobit.genapp.common.KeyValue;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueSearchBuilder;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHLabel;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GHProject;
import org.kohsuke.github.GHProjectColumn;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.PagedIterable;
import org.kohsuke.github.PagedSearchIterable;

/**
 * gantt
 *
 */
public class GitHubGantt {

    protected static final Logger log = Logger.getLogger(GitHubGantt.class);

    public static final long ESTIMACIO_MIG_D = 12L;
    public static final long ESTIMACIO_1D = 24L;
    public static final long ESTIMACIO_2D = 2 * ESTIMACIO_1D;
    public static final long ESTIMACIO_3D = 3 * ESTIMACIO_1D;
    public static final long ESTIMACIO_4D = 4 * ESTIMACIO_1D;
    public static final long ESTIMACIO_5D = 5 * ESTIMACIO_1D;

    public static final String DEPEN_DE = "Depèn de ";
    public static final String DATA_INICI = "Data Inici:";
    
    public static final int ROOT_ISSUE = 1;
    

    protected final GitHub githubConnection;

    protected final String username;
    protected final String token;

    protected final Map<String, GHOrganization> myOrganizations;

    public GitHubGantt(String username, String token) throws Exception {
        super();
        this.username = username;
        this.token = token;
        this.githubConnection = new GitHubBuilder()
                // .withPassword("anadal-fundaciobit", "").build();
                .withOAuthToken(token, username).build();

        myOrganizations = githubConnection.getMyOrganizations();
    }

    public void printOrganizationsRepositoriesProjectsOfUser() throws IOException {

        for (String org : myOrganizations.keySet()) {

            log.info("Organization: " + org);

            for (GHRepository repos : myOrganizations.get(org).getRepositories().values()) {
                log.info("     + Repos: " + repos.getName());
                PagedIterable<GHProject> projectsPI = repos.listProjects();

                List<GHProject> projects = projectsPI.toList();

                for (GHProject p : projects) {
                    log.info("         * Project" + p.getId() + " - " + p.getName() + "  " + p.getNumber());
                }
            }
        }

    }

    public Set<String> getOrganizations() {
        return new TreeSet<String>(this.myOrganizations.keySet());
    }

    public Set<String> getRepositories(String organization) throws Exception {
        return new TreeSet<String>(this.myOrganizations.get(organization).getRepositories().keySet());
    }

    public GHProject getProject(String organization, String repository, long projectID) throws Exception {
        return githubConnection.getProject(projectID);
    }

    public Map<Long, String> getProjects(String organization, String repository) throws Exception {
        GHRepository repos = this.myOrganizations.get(organization).getRepositories().get(repository);
        PagedIterable<GHProject> projectsPI = repos.listProjects();

        List<GHProject> projects = projectsPI.toList();

        Map<Long, String> allProjects = new HashMap<Long, String>();
        for (GHProject p : projects) {

            allProjects.put(p.getId(), p.getName());
        }
        return allProjects;
    }

    public static void main(String[] args) {
        log.info("Hello World!");

        long start = System.currentTimeMillis();
        
        
        ConsoleAppender console = new ConsoleAppender(); //create appender
        //configure the appender
        String PATTERN = "%d [%p|%c|%C{1}] %m%n";
        console.setLayout(new PatternLayout(PATTERN)); 
        console.setThreshold(Level.DEBUG);
        console.activateOptions();
        //add appender to any Logger (here is root)
        Logger.getRootLogger().addAppender(console);
       

        try {
            
            
            
            Properties props = new Properties();
            
            props.load(new FileInputStream(new File("github.properties")));
            
            
            
            

            // If the token has access to an organization, you can specify it here.
            String username = props.getProperty("username");
            String token = props.getProperty("token");
            
            
            
            
            String organization = "Fundacio-Bit";
            String repository = "githubgantt";
            long projectID = 12046952; // githubgantt-1.0.0
            int programadors = 1;
            
            //String organization = "GovernIB";
            //String repository = "carpeta";

            // Calendar startDate = Calendar.getInstance();
            // startDate.set(2020, Calendar.NOVEMBER, 26, 0, 0, 0);

            // long projectID = 5569144; // 1.1.5
            // long projectID = 5728183; // 1.1.6
            // long projectID = 5973701; // 1.1.7
            // long projectID =10002179; // carpeta-1.1.8
            //long projectID = 11478633; // carpeta-1.1.9

            //int programadors = 3;
            
            System.out.println("username: ]" + username + "[");
            System.out.println("token: ]" + token + "[");
            

            GitHubGantt ghg = new GitHubGantt(username, token);
            
            
            
            /*
            KeyValue<Long> selectedProject = ghg.consoleProjectSelection();
            
            
            System.out.println(" Projecte Seleccionat: " + selectedProject.getValue() + " (" + selectedProject.getKey() + ")" );
            */

//            ghg.getOrganizations();
//            
//            ghg.getRepositories(organization);

            /*
            {
                Map<Long, String> projects = ghg.getProjects(organization, repository);
                projects.forEach((k, v) -> log.info("long projectID = " + k + "; // " + v));
            }

            GHProject project = ghg.getProject(organization, repository, projectID);

            log.info("Project:: Body => " + project.getBody());
            log.info("Project::  => " + project);
            */

            // ghg.printOrganizationsRepositoriesProjectsOfUser();

            /*
             * GHRepository carpeta = githubConnection.getRepository( organization + "/" +
             * repository);
             * 
             * PagedIterable<GHProject> projectsPI = carpeta.listProjects();
             * 
             * List<GHProject> projects = projectsPI.toList();
             * 
             * for (GHProject p : projects) { log.info("" + p.getId() + " - " + p.getName()
             * + "  " + p.getUpdatedAt()); }
             */

//           GHProject p = ghg.getProject(organization, repository, projectID);
//           log.info(p.getId() + " - " + p.getName() + " - " + p.getNumber());
//           log.info("]" + p.getBody() + "[");

            
            Date startDate = new Date();

            Map<String, Object> parameters = ghg.generateGanttData(organization, repository, projectID, programadors,
                    startDate);

            
            
            
              
              byte[] html = FileUtils.readFileToByteArray(new File("./tester/gantt-dhtmlx.html"));
              
              String htmlOut = TemplateEngine.processExpressionLanguage(new String(html,
              "utf8"), parameters);
              
              FileWriter fos = new FileWriter("./tester/index.html");
              
              fos.write(htmlOut);
              
              fos.close();
             

            System.out.println("FINAL " + (System.currentTimeMillis() - start) + " ms");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public KeyValue<Long> consoleProjectSelection() throws Exception  {
    

       Set<String> orgs =  this.getOrganizations();
       
       if (orgs.size() == 0) {
           throw new Exception("L'usuari " + this.username + " no te organitzacions associades !!!");
       }
       
       String organization = consoleSelection(orgs, "Seleccioni organitzacio:");
       
       System.out.println("Seleccionada organitzacio " + organization + ". Esperi ...");
       
       Set<String> repos= this.getRepositories(organization);
       
       String repository = consoleSelection(repos, "Seleccioni repositori:");
       
       
       System.out.println("Selecciont repositoriitzacio " + organization + ". Esperi ...");
  
      Map<Long, String> projects = this.getProjects(organization, repository);
            
      String projectName = consoleSelection(projects.values(), "Seleccioni projecte:");
      
      for(Map.Entry<Long, String> entry : projects.entrySet())
      {
          if(entry.getValue().equals(projectName)) {
              return new KeyValue<Long>(entry.getKey(), entry.getValue());
          }
      }
      
      return null;
  }

    public String consoleSelection(Collection<String> llista, String msg) {
        
        Map<Integer, String> map = new HashMap<Integer, String>();
        int i = 1;
        for (String item : llista) {
            map.put(i++, item);
        }

        Scanner choose = new Scanner(System.in);
        String choice;

        do {
            System.out.println();
            System.out.println(msg);
            
            for(Map.Entry<Integer, String> entry : map.entrySet()) {
               System.out.println("  " + entry.getKey() + ".- " + entry.getValue());
            }

            try {
              choice = choose.nextLine();
            } catch(java.util.NoSuchElementException e) {
                choice = choose.nextLine();
            }
            

            try {
                int opcio = Integer.parseInt(choice);

                String value = map.get(opcio);

                if (value == null) {
                    throw new Exception("Numero no valid");
                }

                //choose.reset();
                //choose.close();
                return value;

            } catch (Exception e) {
                System.err.println("ERROR: " + e.getMessage());                
            }
        } while (true);
    }

    public Map<String, Object> generateGanttData(String organization, String repository, long projectID,
            int programadors, Date startDate) throws IOException, Exception {

        GHProject project = githubConnection.getProject(projectID);

        log.error(" ----------------   ENTRA A generateGanttData ------------------");

        PagedIterable<GHProjectColumn> hola = project.listColumns();

        List<GHProjectColumn> columnList = hola.asList();

        for (GHProjectColumn ghProjectColumn : columnList) {
            log.info("Column: " + ghProjectColumn.getId());
            log.info("Column: " + ghProjectColumn.getName());
        }

        int projectNumber = project.getNumber();
        String projectName = project.getName();

        log.info(project.getId() + " - " + projectName + " - " + projectNumber);

        GHIssueSearchBuilder ibuilder = githubConnection.searchIssues()
                .q("is:issue project:" + organization + "/" + repository + "/" + projectNumber);

        PagedSearchIterable<GHIssue> issues = ibuilder.list();

        List<GanttItem> gantts = new ArrayList<GanttItem>();

        Map<Integer, Long> durationsEpicCAIB = new HashMap<Integer, Long>();

        List<String> errors = new ArrayList<String>();
        List<String> warnings = new ArrayList<String>();

        /*
         * Calendar startDate = Calendar.getInstance(); { // Data Inici del Projecte
         * 
         * String pBody = project.getBody(); if (pBody.startsWith(DATA_INICI)) { try {
         * 
         * 
         * String line1 = pBody.substring(DATA_INICI.length() + 1); int pos =
         * line1.indexOf('\n'); String dataStr; if (pos == -1) { dataStr = line1; } else
         * { dataStr = line1.substring(0, pos); } dataStr =dataStr.trim();
         * SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); Date d =
         * sdf.parse(dataStr);
         * 
         * startDate.setTimeInMillis(d.getTime());
         * 
         * 
         * } catch (Exception e) { // TODO: handle exception errors.
         * add("Error parsejant la data d'inici del camp descripció del projecte: " +
         * e.getMessage()); e.printStackTrace(); // XYZ ZZZ } } else {
         * errors.add("Ha de definir una data d'inici en el projecte." +
         * " En la primera linia de la descripció del projecte s'ha afegir '" +
         * DATA_INICI + " dd/mm/aaaa'"); }
         * 
         * 
         * 
         * }
         */

        boolean afegidaIssueRoot = false;

        for (GHIssue i : issues) {
            /*
             * PagedIterable<GHReaction> ev = i.listReactions(); List<GHReaction> list =
             * ev.asList(); for (GHReaction event : list) { log.info(" REACTION: " +
             * event.getContent().getContent()); }
             */
            // log.info(" BODY: " + );

            log.info(" ======== ISSUE # " + i.getNumber() + " (State : " + i.getState().name() + ")");
            
            if (i.getNumber() == 1) {
                errors.add(encodeHtml("La tasca " + i.getNumber() 
                  + " s'ha d'esborar que interfereix amb el sistema de representació. Tanqui la issue "
                  + i.getNumber() + " desvinculi-la del project"));
            }
            

            int percentCompleted = GHIssueState.OPEN.equals(i.getState()) ? 0 : 100;

            Collection<GHLabel> labels = i.getLabels();
            boolean isCAIB = false;
            boolean isEPIC = false;
            Long expectedTime = null;

            for (GHLabel l : labels) {
                System.out.print(" - " + l.getName());
                if ("CAIB".equals(l.getName())) {
                    isCAIB = true;
                    continue;
                }
                if ("Estimació: EPIC".equals(l.getName())) {
                    isEPIC = true;
                    continue;
                }
                if ("Estimació: pendent".equals(l.getName())) {
                    expectedTime = ESTIMACIO_1D;
                    warnings.add(encodeHtml(
                            "La tasca #" + i.getNumber() + " té estimació pendent. Li posam temporalment 1D."));
                    continue;
                }
                if ("Estimació: 0.5D".equals(l.getName())) {
                    expectedTime = ESTIMACIO_MIG_D;
                    continue;
                }
                if ("Estimació: 1D".equals(l.getName())) {
                    expectedTime = ESTIMACIO_1D;
                    continue;
                }
                if ("Estimació: 2D".equals(l.getName())) {
                    expectedTime = ESTIMACIO_2D;
                    continue;
                }
                if ("Estimació: 3D".equals(l.getName())) {
                    expectedTime = ESTIMACIO_3D;
                    continue;
                }
                if ("Estimació: 4D".equals(l.getName())) {
                    expectedTime = ESTIMACIO_4D;
                    continue;
                }
                if ("Estimació: 5D".equals(l.getName())) {
                    expectedTime = ESTIMACIO_5D;
                    continue;
                }

            }

            log.info("\n");

            // List<GHIssueComment> comments = i.getComments();

            String body = i.getBody();
            String dependency = null;
            
            

            if (body.trim().startsWith(DEPEN_DE)) {

                int pos1 = body.indexOf(DEPEN_DE);
                int pos2 = body.indexOf('\n');

                String cadena = body.substring(pos1 + DEPEN_DE.length(), pos2).trim();

                log.info("DEPEN DE ]" + cadena + "[");

                if ("PROJECT".equals(cadena.trim())) {
                    dependency = "" + ROOT_ISSUE;
                    
                    if (afegidaIssueRoot == false) {
                        gantts.add(new GanttItem(ROOT_ISSUE, " P R O J E C T   " + projectName, null,
                                new Date(startDate.getTime() - 2 * 60 * 60 * 1000), null, null, 0,  null, true, true));
                    }
                    
                    afegidaIssueRoot = true;

                } else {

                    int pos = cadena.lastIndexOf('#');

                    log.info(" POS # => " + pos);

                    if (pos != -1) {
                        int startPos = pos + 1;
                        do {
                            pos = pos + 1;

                        } while (pos < cadena.length() && Character.isDigit(cadena.charAt(pos)));

                        if (pos <= cadena.length()) {
                            dependency = cadena.substring(startPos, pos).trim();
                            log.info("\t\t DEP ]]]" + dependency + "[[");
                        }
                    }
                }
            }

            if (isCAIB && isEPIC) {
                errors.add(encodeHtml("La tasca " + i.getNumber() + " te marcat CAIB i EPIC alhora !!!")
                        + "<a target=\"_blank\" href=\"https://github.com/" + organization + "/" + repository
                        + "/issues/" + i.getNumber() + "\">"
                                + " #" + i.getNumber() 
                                + "</a> - "
                        + (i.getAssignees().isEmpty() ? "" : i.getAssignees().get(0).getLogin()));
                continue;
            }

            if ((isCAIB || isEPIC) && expectedTime != null) {
                warnings.add(encodeHtml("La tasca " + i.getNumber() + " te marcat CAIB o EPIC i no pot tenir definida "
                        + " cap estimació !!!") + "<a target=\"_blank\" href=\"https://github.com/" + organization + "/"
                        + repository + "/issues/" + i.getNumber() + "\"> #" + i.getNumber() + "</a> - "
                        + (i.getAssignees().isEmpty() ? "" : i.getAssignees().get(0).getLogin()));
                continue;
            }

            if (!isCAIB && !isEPIC && expectedTime == null) {
                warnings.add(encodeHtml("La tasca " + i.getNumber()
                        + " necessita una estimació definida en Dies. Per defecte  li assignam 1 dia.")
                        + "<a target=\"_blank\" href=\"https://github.com/" + organization + "/" + repository
                        + "/issues/" + i.getNumber() + "\"> #" + i.getNumber() + "</a> - "
                        + (i.getAssignees().isEmpty() ? "" : i.getAssignees().get(0).getLogin()));
                continue;
            }

            if ((!isCAIB && !isEPIC) && dependency == null) {
                errors.add(encodeHtml("La tasca " + i.getNumber() + " no depen de cap Issue. "
                        + "Sinó depèn de cap issue ha d´incloure el text \"Depèn de PROJECT\". Si depen d'un issue EPIC o CAIB llavors ha d´incloure el text 'Depèn de [text_issue] #[num issue]'")
                        + "<a target=\"_blank\" href=\"https://github.com/" + organization + "/" + repository
                        + "/issues/" + i.getNumber() + "\"> #" + i.getNumber() + "</a> - "
                        + (i.getAssignees().isEmpty() ? "" : i.getAssignees().get(0).getLogin()));
                continue;
            }

            int taskID = i.getNumber();
            String taskName = i.getTitle();
            String resource = null;
            Date startDate2 = null;
            Date endDate = i.getClosedAt();
            Long duration = expectedTime;

            // TODO definir la duració obligatoria

            Integer dependencyGantt = null;
            if (dependency == null) {
                resource = "" + taskID;
            } else {
                resource = "" + dependency;
                dependencyGantt = Integer.parseInt(dependency);
            }

            if (dependencyGantt != null && duration != null) {
                addDuration(durationsEpicCAIB, dependencyGantt, duration);
            }

            // log.info("\t\t" + i.getBody()); //comments.get(0).getBody());
            GanttItem item = new GanttItem(taskID, taskName, resource, startDate2, endDate, duration, percentCompleted,
                    dependencyGantt, isCAIB, isEPIC);
            gantts.add(item);
        }
        
        
        


        
        
        

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("startDate", startDate.getTime());
        parameters.put("startDate2", startDate);
        parameters.put("organization", organization);
        parameters.put("repository", repository);
        parameters.put("projectID", projectID);
        parameters.put("projectNumber", projectNumber);
        parameters.put("projectName", projectName);
        parameters.put("urlBase", "https://github.com/");
        parameters.put("programadors", programadors);
        parameters.put("errors", errors);
        parameters.put("warnings", warnings);


        {
            Calendar now = Calendar.getInstance();
            parameters.put("now_day_month", now.get(Calendar.DAY_OF_MONTH));
            parameters.put("now_month", now.get(Calendar.MONTH));
            parameters.put("now_year", now.get(Calendar.YEAR));
        }

        DecimalFormat df = new DecimalFormat("#.##");
        String step = df.format((1.0f / programadors));
        parameters.put("step", step.replace(',', '.'));

        if (errors.size() == 0) {

            

            generateDhtmlxGanttData(parameters, startDate, gantts, durationsEpicCAIB, programadors, errors, warnings,
                    repository, organization);
        }

        return parameters;
    }

    protected void generateDhtmlxGanttData(Map<String, Object> parameters, Date startDate, List<GanttItem> gantts,
            Map<Integer, Long> durationsEpicCAIB, int programadors, List<String> errors, List<String> warnings,
            String repository, String organization) throws IOException {

        Map<Integer, GanttItem> map = new HashMap<Integer, GanttItem>();

        for (GanttItem gi : gantts) {
            map.put(gi.getTaskID(), gi);
        }

        List<Integer> tasquesCAIB = new ArrayList<Integer>();
        // Definir Fills, tasques CAIB
        for (GanttItem gi : gantts) {

            if (gi.getTaskID() == ROOT_ISSUE) {
                // tasca de Projecte Arrel
                continue;
            }

            Integer pare = gi.getDependency();

            if (pare == null || pare == ROOT_ISSUE) {
                // Tasca CAIB
                gi.setDependency(ROOT_ISSUE);
                map.get(ROOT_ISSUE).addFill(gi);
            } else {
                // tasca normal o EPIC
                log.info("Dep: " + gi.getDependency());
                log.info("task: " + gi.getTaskID());

                log.info("MAP: " + map.get(gi.getDependency()));

                if (map.get(gi.getDependency()) == null) {
                    errors.add(encodeHtml("La tasca " + gi.getTaskID() + "  depen d'una tasca (" + gi.getDependency()
                            + ") que no existeix dins del projecte'")
                            + "<a target=\"_blank\" href=\"https://github.com/" + organization + "/" + repository
                            + "/issues/" + gi.getTaskID() + "\"> #" + gi.getTaskID()
                            + "</a> - <a target=\"_blank\" href=\"https://github.com/" + organization + "/" + repository
                            + "/issues/" + gi.getDependency() + "\"> #" + gi.getDependency() + "</a> ");
                    continue;
                } else {
                    
                    GanttItem parent =map.get(gi.getDependency()); 
                    
                    
                    if (parent.isCaib() || parent.isEpic()) {
                       parent.addFill(gi);
                    } else {
                        // Totes les dependency han de ser CAIB o EPIC
                        errors.add(encodeHtml("La tasca " + gi.getTaskID() + " depèn d'una tasca (" + gi.getDependency()
                        + ") que no és ni EPIC ni CAIB'")
                        + "<a target=\"_blank\" href=\"https://github.com/" + organization + "/" + repository
                        + "/issues/" + gi.getTaskID() + "\"> #" + gi.getTaskID()
                        + "</a> - <a target=\"_blank\" href=\"https://github.com/" + organization + "/" + repository
                        + "/issues/" + gi.getDependency() + "\"> #" + gi.getDependency() + "</a> ");
                    }
                    
                }
            }

            if (gi.isCaib() || (gi.getDependency() != null && gi.getDependency() == ROOT_ISSUE)) {
                tasquesCAIB.add(gi.getTaskID());
            }
        }

        // Inici del mes o data actual
        Calendar currentDate = Calendar.getInstance();

        long currentDateMs = Math.max(currentDate.getTimeInMillis(), startDate.getTime());

        currentDate.setTimeInMillis(currentDateMs);

        String[] colors = { "008000", "FFA500", "808000", "0000FF", "00FF00", "FF00FF", "008080", "FF0000", "800000",
                "800080", "00EEEE" };

        int count = 0;
        int percent = 0;
        List<GanttLink> links = new ArrayList<GanttLink>();

        if (errors.size() == 0) {

            // Calcular Start Date de cada tasca

            // les tasques acabades es posen segons la data de finalitzacio
            // i despres les tasques pendents es calculen a partir d'avui

            for (Integer caibID : tasquesCAIB) {

                log.info(" TAXC CAIB[" + caibID + "]");

                GanttItem parent = map.get(caibID);

                String color = colors[count++ % colors.length];

                links.add(new GanttLink(ROOT_ISSUE, parent.getTaskID(), GanttLink.START_TO_START));

                if (parent.isCaib() && parent.getFills().size() == 0) {
                    warnings.add(
                            encodeHtml("La tasca " + caibID + ", que és de tipus CAIB, no té cap subtasca associada") 
                            +  "</a> - <a target=\"_blank\" href=\"https://github.com/" + organization + "/" + repository
                            + "/issues/" + caibID + "\"> #" +caibID + "</a> ");
                }

                // Map<Integer, GanttItem> map, Calendar currentDate
                percent = percent + processarIssue(parent, map, currentDate, color, links, programadors);
            }
        }

        if (tasquesCAIB.size() == 0) {
            map.get(ROOT_ISSUE).setPercentCompleted(0);
        } else {
            map.get(ROOT_ISSUE).setPercentCompleted(percent / tasquesCAIB.size());
        }

        StringBuffer str = new StringBuffer();

        /*
         * 
         * {id: 1, text: "Project #1", start_date: null, duration: null, parent:0,
         * progress: 0, open: true}, {id: 2, text: "Task #1", start_date:
         * "2019-08-01 00:00", duration:5, parent:1, progress: 1}, {id: 3, text:
         * "Task #2", start_date: "2019-08-06 00:00", duration:2, parent:1, progress:
         * 0.5}, {id: 4, text: "Task #3", start_date: null, duration: null, parent:1,
         * progress: 0.8, open: true}, {id: 5, text: "Task #3.1", start_date:
         * "2019-08-09 00:00", duration:2, parent:4, progress: 0.2}, {id: 6, text:
         * "Task #3.2", start_date: "2019-08-11 00:00", duration:1, parent:4, progress:
         * 0}
         * 
         */

        Collections.sort(gantts);

        for (GanttItem gi : gantts) {
            /*
             * String date; if (gi.isCaib() || gi.isEpic()) { date = "null"; } else { date =
             * dateToDhtmlx(gi.getStart()); }
             */
            if (str.length() != 0) {
                str.append(',');
            }

            final String parent;
            if (gi.getDependency() == null /*|| ( gi.getDependency() == ROOT_ISSUE && (gi.isCaib() || gi.isEpic()))*/) {
                parent = "";
            } else {
                parent = ", parent:" + String.valueOf(gi.getDependency());
            }
            
            str.append("{id:" + gi.getTaskID() + ", text:\""
                    + encodeHtml(gi.getTaskName().replace('"', '\'')
                            + ((gi.getTaskID() == ROOT_ISSUE) ? "" : (" #" + gi.getTaskID())))
                    + "\"" + ", start_date:" + dateToDhtmlx(gi.getStart()) + ", duration:"
                    + (gi.getDuration() == null ? "null" : (gi.getDuration()))  
                    + parent
                    + ", progress:" + (gi.getPercentCompleted() / 100f) 
                    + ", color: \"#" + gi.getColor() + "\"}\n");

        }

        StringBuffer linksStr = new StringBuffer();
        for (GanttLink l : links) {
            if (linksStr.length() != 0) {
                linksStr.append(",");
            }

            linksStr.append("{id:" + l.getId() + ", source: " + l.getOrigen() + ", target:" + l.getDesti() + ", type:\""
                    + l.getType() + "\"" + ((l.getType() == GanttLink.START_TO_START) ? ", color :\"green\"" : "")
                    + "   }\n");
        }

        log.info(str.toString());

        parameters.put("addRows", str.toString());
        parameters.put("addLinks", linksStr.toString());
    }

    public static class ProcessIssuesInfo {

        int percent;

    }

    protected int processarIssue(GanttItem parent, Map<Integer, GanttItem> map, Calendar currentDate, String color,
            List<GanttLink> links, int programadors) {

        parent.setColor(color);
        if (parent.getFills().size() == 0) {

            if (parent.getDuration() == null) {
                // CAIB
                parent.setStart(currentDate.getTime());
                parent.setDuration(ESTIMACIO_3D); // 3 DIA
                currentDate.add(Calendar.HOUR, (int) (long) (parent.getDuration() / (float) programadors));
            } else {
                // Tasca normal root
                if (parent.isClosed()) {
                    parent.setStart(new Date(parent.getEnd().getTime() - durationToMs(parent.getDuration())));
                } else {
                    parent.setStart(currentDate.getTime());
                    currentDate.add(Calendar.HOUR, (int) (long) (parent.getDuration() / (float) programadors));
                }
            }

            return 0;
        } else {

            parent.setStart(currentDate.getTime());
            int percentCompletat = 0;

            int lastLink = parent.getTaskID();

            Collections.sort(parent.getFills());

            for (GanttItem fill : parent.getFills()) {

                // GanttItem fill = map.get(fillID);

                if (fill.isEpic()) {
                    percentCompletat = percentCompletat
                            + processarIssue(fill, map, currentDate, color, links, programadors);
                } else {
                    if (fill.isClosed()) {
                        long start = fill.getEnd().getTime() - durationToMs(fill.getDuration());
                        if (start < parent.getStart().getTime()) {
                            parent.getStart().setTime(start);
                        }
                        fill.setStart(new Date(start));
                    } else {
                        fill.setStart(currentDate.getTime());
                    }
                    fill.setColor(color);
                    percentCompletat = percentCompletat + fill.getPercentCompleted();

                    log.info("fill.getDuration() => " + fill.getDuration());
                    if (fill.getDuration() == null) {
                        System.err.println("La tasca amb ID " + fill.getTaskID() + " no te duracio definida");
                    }
                    if (!fill.isClosed()) {
                        currentDate.add(Calendar.HOUR, (int) (long) (fill.getDuration() / (float) programadors));

                    }
                }
                links.add(new GanttLink(lastLink, fill.getTaskID(), GanttLink.FINAL_TO_START));
                lastLink = fill.getTaskID();
            }
            parent.setPercentCompleted(percentCompletat / parent.getFills().size());
            return parent.getPercentCompleted();
        }

    }

    protected String encodeHtml(String str) {
        return StringEscapeUtils.escapeHtml4(str);
    }

    protected long durationToMs(Long hours) {
        return hours * 60 * 60 * 1000;
    }

    protected static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    protected String dateToDhtmlx(Date date) {
        if (date == null) {
            return "null";
        } else {
            // "2019-08-11 00:00"
            // return "\"" + (date.getYear() + 1900) + "-" + date.getMonth() + "-" +
            // date.getDate() + " 00:00\"";
            return "\"" + SDF.format(date) + "\"";
        }
    }

    protected void addDuration(Map<Integer, Long> durationsEpicCAIB, Integer dependencyGantt, Long duration) {

        Long durationOrig = durationsEpicCAIB.get(dependencyGantt);

        if (durationOrig == null) {
            durationOrig = 0L;
        }

        durationsEpicCAIB.put(dependencyGantt, durationOrig + duration);

    }

}
