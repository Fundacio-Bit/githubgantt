package org.fundaciobit.githubgantt.back.gantt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.fundaciobit.genapp.common.KeyValue;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

/**
 * 
 * @author anadal
 *
 */
public class GitHubGanttTest {

    
    
    public static void main(String[] args) {
        System.out.println("Inici ...");

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
            
            
            
            
            //String organization = "Fundacio-Bit";
            //String repository = "githubgantt";
            //long projectID = 12046952; // githubgantt-1.0.0
            int programadors = 1;
            
            
            String organization = "GovernIB";
            String repository = "pluginsib-scanweb()";
            long projectID = 12166863; // pluginsib-scanweb-4.0.0 (12166863)";
            
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

            
            
            
              
              byte[] html = org.apache.commons.io.FileUtils.readFileToByteArray(new File("./tester/gantt-dhtmlx.html"));
              
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
}
