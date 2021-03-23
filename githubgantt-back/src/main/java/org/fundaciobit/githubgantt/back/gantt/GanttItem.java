package org.fundaciobit.githubgantt.back.gantt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 
 * @author anadal
 *
 */
public class GanttItem implements Comparable<GanttItem>{

    protected int taskID;
    protected String taskName;
    protected String resource;
    protected Date start;
    protected Date end;
    protected Long duration;
    protected int percentCompleted;
    protected Integer dependency;

    protected boolean caib;
    protected boolean epic;
    
    
    protected String color;
    
    final boolean closed;
    
    
    protected List<GanttItem> fills = new ArrayList<GanttItem>();


    public GanttItem(int taskID, String taskName, String resource, Date start, Date end, Long duration,
            int percentCompleted, Integer dependency, boolean caib, boolean epic) {
        super();
        this.taskID = taskID;
        this.taskName = taskName;
        this.resource = resource;
        this.start = start;
        this.end = end;
        this.duration = duration;
        this.percentCompleted = percentCompleted;
        this.dependency = dependency;
        this.caib = caib;
        this.epic = epic;
        this.closed = ((end == null)? false: true);
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Date getStart() {
        return start;
    }

    public String getStartGantt() {
        return getGanttDate(start);
    }

    @SuppressWarnings("deprecation")
    public static String getGanttDate(Date date) {
        if (date == null) {
            return "null";
        } else {
            return "new Date(" + (date.getYear() + 1900) + "," + date.getMonth() + "," + date.getDate() + ")";
        }
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public String getEndGantt() {
        return getGanttDate(end);
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public int getPercentCompleted() {
        return percentCompleted;
    }

    public void setPercentCompleted(int percentCompleted) {
        this.percentCompleted = percentCompleted;
    }

    public Integer getDependency() {
        return dependency;
    }

    public void setDependency(Integer dependency) {
        this.dependency = dependency;
    }

    public String getDependencyGantt() {
        if (this.dependency == null) {
            return "null";
        } else {
            // TODO
            return "'" + this.dependency + "'";
        }
    }

    public boolean isCaib() {
        return caib;
    }

    public void setCaib(boolean caib) {
        this.caib = caib;
    }

    public boolean isEpic() {
        return epic;
    }

    public void setEpic(boolean epic) {
        this.epic = epic;
    }

    public List<GanttItem> getFills() {
        return fills;
    }

    public void setFills(List<GanttItem> fills) {
        this.fills = fills;
    }

    public void addFill(GanttItem taskID) {
        this.fills.add(taskID);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isClosed() {
        return closed;
    }

    
    public int compareTo(GanttItem gi) {
        
        System.out.println("Compare #" + this.getTaskID() + " with #" + gi.getTaskID());
        
        if (this.isClosed()) {
            if (!gi.isClosed()) {
               return -1; 
            }
        } else {
            if (gi.isClosed()) {
                return +1; 
            }
        }
        
        boolean thisEPIC = this.isCaib() || this.isEpic();
        boolean giEPIC = gi.isCaib() || gi.isEpic();
        
        if (thisEPIC) {
            if (!giEPIC) {
               return -1; 
            }
        } else {
            if (giEPIC) {
                return +1; 
            }
        }
        
        
        return (int)(valoration(this) - valoration(gi));
    }
    
    
    private static long valoration(GanttItem gi) {
        
        if (gi.isCaib() || gi.isEpic()) {
            return 0;
        }
        
        if (gi.getEnd() == null) {
           //System.err.println("     COMPARE NULL: " + this.taskName + " #"+ this.getTaskID());
           return Long.MAX_VALUE;
        } else {
            if (gi.isClosed()) {
              return gi.getEnd().getTime();
            } else {
              return gi.getEnd().getTime();
            }
        }
    }

    
}
