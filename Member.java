import java.util.List;
import java.util.ArrayList;

public abstract class Member {
    
    private String name;
    private String email;
    List<Project> projects;
    Task currentTask;

    public Member() {
        name = "";
        email = "";
        projects = new ArrayList<Project>();
        currentTask = null;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public void joinProject(Project newProject) {

        projects.add(newProject);
    }

    public void leaveProject(Project oldProject) {
        projects.remove(oldProject);
    }

    public Task assignTask() {
        return this.currentTask;
    }

    public void removeTask(Task newTask) {
        currentTask = newTask;
    }

    abstract void work();



}
