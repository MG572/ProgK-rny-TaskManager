package taskmanager;

import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.time.LocalDate;

public class TaskCell extends ListCell<Task> {
    @Override
    protected void updateItem(Task task, boolean empty) {
        super.updateItem(task, empty);

        if (empty || task == null) {
            setText(null);
            setGraphic(null);
        } else {
            Text nameText = new Text(task.getName() + "\n");
            if (task.isCompleted()) {
                nameText.setFill(Color.GREEN);
            } else {
                nameText.setFill(Color.BLACK);

                if (task.getDueDate().equals(LocalDate.now()) || task.getDueDate().equals(LocalDate.now().plusDays(1))) {
                    nameText.setFill(Color.RED);
                }
            }
            nameText.setStyle("-fx-font-weight: bold;"); // Bold the task name

            Text dueDateText = new Text("Due: " + task.getDueDate() + "\n");
            dueDateText.setFill(Color.BLACK);

            Text priorityText = new Text("Priority: " + getPriorityLabel(task.getPriority()) + "\n");
            priorityText.setFill(Color.BLACK);

            TextFlow taskDisplay = new TextFlow(nameText, dueDateText, priorityText);

            setGraphic(taskDisplay);
        }
    }

    private String getPriorityLabel(int priority) {
        return switch (priority) {
            case 1 -> "High";
            case 2 -> "Medium";
            case 3 -> "Low";
            default -> "Unknown";
        };
    }
}
