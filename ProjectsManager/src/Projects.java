import java.util.ArrayList;

public class Projects{
     private int id;
     private String name;
     private ArrayList<Issues> issues = new ArrayList<>();

     public Projects(int id, String name){ //to assign the id and the name for the project
        this.id = id;
        this.name = name;
     }

     public ArrayList<Issues> getIssues() {return issues;} //return the issues arraylist
     public int getId() {return id;}
     public String getName() {return name;}
}