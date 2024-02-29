public class FrontendDev extends Member {

    public void work() {
        System.out.println("I'm thriving in frontend");
    }

    public Project newProject(Collection<Tasks> tasks, Collection<Member> members, name, description, startDate, endDate) {
        
        Project temp = new Project(tasks, members, name, description, startDate, endDate);
        temp.addMember(this);
        return temp;
    }

    public Project newProject(Collection<Tasks> tasks, name, description, startDate, endDate) {
        Project temp = new Project(tasks, name, description, startDate, endDate);
        temp.addMember(this);
        return temp;
    }

    public Project newProject(Collection<Member> members, name, description, startDate, endDate) {
        Project temp = new Project(members, name, description, startDate, endDate);
        temp.addMember(this);
        return temp;
    }

    public Project newProject(Collection<Tasks> tasks, name, description, startDate, endDate) {
        Project temp = new Project(name, description, startDate, endDate);
        temp.addMember(this);
        return temp;
    }
    
}
