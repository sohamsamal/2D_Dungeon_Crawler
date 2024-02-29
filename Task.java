public abstract class Task {
    private String title;
    private String description;
    private String dueDate;
    private String status;
    private String priority;

    public Task(String title, String description, String dueDate, String status, String priority) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.priority = priority;
    }

    public Task() {
        title = "";
        description = "";
        dueDate = "";
        priority = "";
        start();
    }

    public void markAsComplete() {
        this.status = "COMPLETED";
    }

    public void start() {
        this.status = "IN PROGRESS";
    }


}
