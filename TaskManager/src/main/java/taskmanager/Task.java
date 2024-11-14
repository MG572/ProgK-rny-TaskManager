package taskmanager;

import java.time.LocalDate;

public class Task {
    private String name;
    private String description;
    private LocalDate dueDate;
    private int priority; // 1 = high, 2 = medium, 3 = low
    private boolean isCompleted;
    public Task(String name, LocalDate dueDate, int priority) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = false;
        this.description = "";
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    @Override
    public String toString() {

        String done;
        if (isCompleted){
            done ="DONE";
        }
        else {
            done ="TO DO";
        }
        return name + " (Due: " + dueDate + ", Priority: " + priority + ", " + done + ")";
    }
}

