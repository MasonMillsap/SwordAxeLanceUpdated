package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Mason Millsap on 2/27/2017.
 */
public class StartController {
    @FXML
    Button begin;

    @FXML
    TextArea message;

    @FXML
    void begin() {
        message.getScene().getWindow().hide();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Name.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            NameController second = (NameController)loader.getController();

            Stage secondStage = new Stage();
            Scene scene = new Scene(root);
            secondStage.setScene(scene);
            secondStage.show();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    void initialize() {
    }
}
