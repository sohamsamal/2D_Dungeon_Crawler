import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class Project {
    Collection<Task> tasks = new ArrayList<>();
    Collection<Member> members = new ArrayList<>();
    String name;
    String description;
    String startDate;
    String endDate;


    // Constructor for a Project with a pre-defined list of tasks and members
    public Project(Collection<Task> tasks, Collection<Member> members, String name, String description, String startDate, String endDate) {
        this.tasks = tasks;
        this.members = members;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    // Constructor for a Project with a pre-defined list of tasks
    public Project(Collection<Task> tasks, String name, String description, String startDate, String endDate) {
        this(tasks, null, name, description, startDate, endDate);
    }

    // Constructor for a Project with a pre-defined list of members
    public Project(Collection<Member> members, String name, String description, String startDate, String endDate) {
        this(null, members, name, description, startDate, endDate);
    }

    // Default Constructor for a Project (needs to include name, description, startDate, and endDate)
    public Project(Collection<Task> tasks, String name, String description, String startDate, String endDate) {
        this(null, null, name, description, startDate, endDate);
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void removeTask(Task t) {
        tasks.remove(t);
    }

    public void addMember(Member m) {
        members.add(m);
    }

    public void removeMember(Member m) {
        members.remove(m);
    }
    
}
