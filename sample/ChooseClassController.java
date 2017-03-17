package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Mason Millsap on 2/27/2017.
 */
public class ChooseClassController {
    private FighterClass chosenClass;

    @FXML
    Label confirmText;

    @FXML
    Button confirm;

    @FXML
    void warrior() {
        chosenClass = FighterClass.WARRIOR;
        confirmText.setText("You have selected the warrior class.");
        confirmText.setVisible(true);
        confirm.setVisible(true);
    }

    @FXML
    void assassin() {
        chosenClass = FighterClass.ASSASSIN;
        confirmText.setText("You have selected the assassin class.");
        confirmText.setVisible(true);
        confirm.setVisible(true);
    }

    @FXML
    void knight() {
        chosenClass = FighterClass.KNIGHT;
        confirmText.setText("You have selected the knight class.");
        confirmText.setVisible(true);
        confirm.setVisible(true);
    }

    @FXML
    void confirm(){
        NameController.player.setFighterClass(chosenClass);
        confirmText.getScene().getWindow().hide();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("HomeScreen.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            HomeScreenController second = (HomeScreenController) loader.getController();

            Stage secondStage = new Stage();
            Scene scene = new Scene(root);
            secondStage.setScene(scene);
            secondStage.show();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    @FXML
    void classInfo() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ClassInfo.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            ClassInfoController second = (ClassInfoController) loader.getController();

            Stage secondStage = new Stage();
            Scene scene = new Scene(root);
            secondStage.setScene(scene);
            secondStage.show();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
