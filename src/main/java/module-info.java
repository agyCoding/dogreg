module doggenregistry {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires transitive javafx.graphics;

    exports doggenregistry;
    exports doggenregistry.controllers;
    opens doggenregistry.controllers to javafx.fxml;
    opens doggenregistry to javafx.fxml;
    
}
