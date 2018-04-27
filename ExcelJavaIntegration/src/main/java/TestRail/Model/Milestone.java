package TestRail.Model;

public class Milestone {

    String start_on;
    Boolean is_started;
    String started_on;
    String completed_on;
    String project_id;
    Integer parent_id;
    String name;
    String description;
    Integer id;
    Boolean is_completed;
    String due_on;
    String url;

    public Milestone(String start_on, Boolean is_started, String started_on, String completed_on, String project_id, Integer parent_id, String name, String description, Integer id, Boolean is_completed, String due_on, String url) {
        this.start_on = start_on;
        this.is_started = is_started;
        this.started_on = started_on;
        this.completed_on = completed_on;
        this.project_id = project_id;
        this.parent_id = parent_id;
        this.name = name;
        this.description = description;
        this.id = id;
        this.is_completed = is_completed;
        this.due_on = due_on;
        this.url = url;
    }

    public Milestone() {

    }

    public String getStart_on() {
        return start_on;
    }

    public void setStart_on(String start_on) {
        this.start_on = start_on;
    }

    public Boolean getIs_started() {
        return is_started;
    }

    public void setIs_started(Boolean is_started) {
        this.is_started = is_started;
    }

    public String getStarted_on() {
        return started_on;
    }

    public void setStarted_on(String started_on) {
        this.started_on = started_on;
    }

    public String getCompleted_on() {
        return completed_on;
    }

    public void setCompleted_on(String completed_on) {
        this.completed_on = completed_on;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIs_completed() {
        return is_completed;
    }

    public void setIs_completed(Boolean is_completed) {
        this.is_completed = is_completed;
    }

    public String getDue_on() {
        return due_on;
    }

    public void setDue_on(String due_on) {
        this.due_on = due_on;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
