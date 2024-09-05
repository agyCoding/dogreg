module doggenregistry {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens doggenregistry.controllers to javafx.fxml;
    opens doggenregistry to javafx.fxml;
    exports doggenregistry;
}
