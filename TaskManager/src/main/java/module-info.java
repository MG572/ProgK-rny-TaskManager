module com.example.taskmanager {
    requires javafx.controls;
    requires javafx.fxml;

    opens taskmanager to javafx.fxml;
    exports taskmanager;
}
