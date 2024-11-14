package taskmanager;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;

public class TaskManagerApp extends Application {

    @FXML
    private TextField taskInput;
    @FXML
    private ListView<Task> listView;
    private TaskList taskList = new TaskList();
    private TaskFileService fileService = new TaskFileService();

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/taskmanager/taskmanager.fxml"));
        //loader.setController(this);
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("To-Do List Application");

        primaryStage.show();
    }

    @FXML
    public void initialize() {
        listView.setCellFactory(lv -> new TaskCell());
        listView.setOnMouseClicked(this::handleTaskDoubleClick);
        refreshTaskList();
    }

    @FXML
    private void handleAddTask() {
        String taskName = taskInput.getText();
        if (!taskName.isEmpty()) {
            Task task = new Task(taskName, LocalDate.now().plusDays(1), 2);
            taskList.addTask(task);
            refreshTaskList();
            taskInput.clear();
        }
    }

    @FXML
    private void handleRemoveTask() {
        Task selectedTask = listView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            taskList.removeTask(selectedTask);
            refreshTaskList();
        }
    }

    private void handleTaskDoubleClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            Task selectedTask = listView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                TaskDetailsWindow.display(selectedTask);
                refreshTaskList();
            }
        }
    }

    private void refreshTaskList() {
        if (listView != null) {
            ObservableList<Task> tasks = FXCollections.observableArrayList(taskList.getTasks());
            listView.setItems(tasks);
            tasks.sort(Comparator.comparing(Task::getDueDate));
        } else {
            System.out.println("ListView is not initialized yet");
        }
    }

    @FXML
    private void sortByPriority() {
        ObservableList<Task> tasks = listView.getItems();
        tasks.sort((task1, task2) -> {
            if (task1.isCompleted() && !task2.isCompleted()) {
                return 1;
            } else if (!task1.isCompleted() && task2.isCompleted()) {
                return -1;
            }
            return Integer.compare(task1.getPriority(), task2.getPriority()); // Sort by priority
        });
    }

    @FXML
    private void sortByName() {
        ObservableList<Task> tasks = listView.getItems();
        tasks.sort(Comparator.comparing(Task::getName));
    }

    @FXML
    private void sortByDueDate() {
        ObservableList<Task> tasks = listView.getItems();
        tasks.sort(Comparator.comparing(Task::getDueDate));
    }

    @FXML
    private void handleExportTasks() {
        fileService.exportTasks(taskList, (Stage) listView.getScene().getWindow());
    }

    @FXML
    private void handleImportTasks() {
        fileService.importTasks(taskList, (Stage) listView.getScene().getWindow());
        refreshTaskList();
    }

    @FXML
    private void handleDeleteAllTasks() {
        taskList.clearAllTasks();  // Assuming TaskList has a method to clear all tasks
        refreshTaskList();         // Refreshes the ListView display
    }
    public static void main(String[] args) {
        launch(args);
    }
}
