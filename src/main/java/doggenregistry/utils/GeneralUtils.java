package doggenregistry.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GeneralUtils {
    public static void showAlert(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
