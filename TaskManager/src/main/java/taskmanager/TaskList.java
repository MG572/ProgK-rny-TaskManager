package taskmanager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) { tasks.add(task); }
    public void removeTask(Task task) { tasks.remove(task); }
    public List<Task> getTasks() { return tasks; }

    public void sortTasksByDueDate() {
        tasks.sort(Comparator.comparing(Task::getDueDate));
    }

    public void sortTasksByPriority() {
        tasks.sort(Comparator.comparing(Task::getPriority));
    }
    public void clearAllTasks() {
        tasks.clear();
    }

}
