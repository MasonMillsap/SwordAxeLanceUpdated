package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Mason Millsap on 3/7/2017.
 */
public class HomeScreenController {
    static Server myserver;
    @FXML
    Button fight;

    @FXML
    Button viewStats;

    @FXML
    Button shop;

    @FXML
    void fight() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ChooseOpponent.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            ChooseOpponentController second = (ChooseOpponentController) loader.getController();

            Stage secondStage = new Stage();
            Scene scene = new Scene(root);
            secondStage.setScene(scene);
            secondStage.show();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    @FXML
    void viewStats() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ViewStats.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            ViewStatsController second = (ViewStatsController) loader.getController();

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
        try {
            myserver = new Server(8880);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
