package taskmanager;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class FileHandler {
    public static void saveTasks(TaskList taskList, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Task task : taskList.getTasks()) {
                writer.write(task.getName() + "," +
                        task.getDescription() + "," +
                        task.getDueDate() + "," +
                        task.getPriority() + "," +
                        task.isCompleted());
                writer.newLine();
            }
        }
    }

    public static void loadTasks(TaskList taskList, String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String description = parts[1];
                LocalDate dueDate = LocalDate.parse(parts[2]);
                int priority = Integer.parseInt(parts[3]);
                boolean isCompleted = Boolean.parseBoolean(parts[4]);

                Task task = new Task(name, dueDate, priority);
                task.setDescription(description);
                task.setCompleted(isCompleted);
                taskList.addTask(task);
            }
        }
    }
}
