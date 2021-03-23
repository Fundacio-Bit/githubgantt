package org.fundaciobit.githubgantt.back.gantt;

public class GanttLink {
    
    public static final int FINAL_TO_START = 0;
    public static final int START_TO_START = 1;
    
    public static int counter = 1;
    
    int id;
    
    int origen;
    
    int desti;
    
    int type;

    public GanttLink(int origen, int desti, int type) {
        super();
        this.id = counter++;
        this.origen = origen;
        this.desti = desti;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getDesti() {
        return desti;
    }

    public void setDesti(int desti) {
        this.desti = desti;
    }

    public static int getCounter() {
        return counter;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
