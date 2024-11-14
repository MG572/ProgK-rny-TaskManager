package taskmanager;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class TaskDetailsWindowController {

    @FXML
    private TextField nameField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private DatePicker dueDatePicker;
    @FXML
    private ComboBox<Integer> priorityBox;
    @FXML
    private CheckBox completedCheckBox;

    private Task task;

    public void setTask(Task task) {
        this.task = task;
        nameField.setText(task.getName());
        descriptionArea.setText(task.getDescription());
        dueDatePicker.setValue(task.getDueDate());
        priorityBox.setValue(task.getPriority());
        completedCheckBox.setSelected(task.isCompleted());
    }

    @FXML
    private void handleSave() {
        task.setName(nameField.getText());
        task.setDescription(descriptionArea.getText());
        task.setDueDate(dueDatePicker.getValue());
        task.setPriority(priorityBox.getValue());
        task.setCompleted(completedCheckBox.isSelected());
        ((Stage) nameField.getScene().getWindow()).close();
    }
}
