package taskmanager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import taskmanager.Task;
import taskmanager.TaskDetailsWindowController;

import java.io.IOException;

public class TaskDetailsWindow {

    public static void display(Task task) {
        try {
            FXMLLoader loader = new FXMLLoader(TaskDetailsWindow.class.getResource("/taskmanager/taskdetailswindow.fxml"));
            Parent root = loader.load();

            TaskDetailsWindowController controller = loader.getController();
            controller.setTask(task);

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Task Details");
            window.setScene(new Scene(root));
            window.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
