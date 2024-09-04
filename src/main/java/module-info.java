module doggenregistry {
    requires javafx.controls;
    requires javafx.fxml;

    opens doggenregistry to javafx.fxml;
    exports doggenregistry;
}
