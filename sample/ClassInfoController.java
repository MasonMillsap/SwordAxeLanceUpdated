package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Mason Millsap on 2/28/2017.
 */
public class ClassInfoController {
    @FXML
    Button close;

    @FXML
    void close() {
        close.getScene().getWindow().hide();
    }

    void initialize() {}
}
