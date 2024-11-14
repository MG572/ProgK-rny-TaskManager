package taskmanager;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class TaskFileService {

    public void exportTasks(TaskList taskList, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                FileHandler.saveTasks(taskList, file.getAbsolutePath());
                System.out.println("Tasks exported!");
            } catch (IOException e) {
                System.out.println("Error exporting tasks: " + e.getMessage());
            }
        }
    }

    public void importTasks(TaskList taskList, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                FileHandler.loadTasks(taskList, file.getAbsolutePath());
                System.out.println("Tasks imported!");
            } catch (IOException e) {
                System.out.println("Error importing tasks: " + e.getMessage());
            }
        }
    }


}
