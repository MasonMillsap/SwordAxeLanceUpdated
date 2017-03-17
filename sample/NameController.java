package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NameController {
    static Fighter player = new Fighter(10);
    static String opponentIP;

    @FXML
    Button accept;

    @FXML
    TextField fighterName;

    @FXML
    void nameFighter() {
        player.setName(fighterName.getText());
        fighterName.setEditable(false);
    }

    @FXML
    void beginFight() {
        //beginFight.getScene().getWindow().hide();
//        System.out.println(player.toString());
//        Parser parser = new Parser();;
//        parser.parseFighter(player.toString());
        accept.getScene().getWindow().hide();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ChooseClass.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            ChooseClassController second = (ChooseClassController)loader.getController();

            Stage secondStage = new Stage();
            Scene scene = new Scene(root);
            secondStage.setScene(scene);
            secondStage.show();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        accept.setOnAction(event ->  {
            nameFighter();
            beginFight();
        });

    }

}



