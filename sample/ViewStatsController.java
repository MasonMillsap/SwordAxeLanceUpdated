package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by Mason Millsap on 3/8/2017.
 */
public class ViewStatsController {
    @FXML
    Label name;
    @FXML
    Label level;
    @FXML
    Label experience;
    @FXML
    Label attack;
    @FXML
    Label defense;
    @FXML
    Label speed;
    @FXML
    Label vitality;
    @FXML
    Label fighterclass;

    @FXML
    void initialize() {
        name.setText("Name: " + NameController.player.getName());
        level.setText("Level: " + NameController.player.getLevel());
        experience.setText("Experience: " + NameController.player.getExp());
        attack.setText("Attack: " + NameController.player.getStats().get("Attack"));
        defense.setText("Defense: " + NameController.player.getStats().get("Defense"));
        speed.setText("Speed: " + NameController.player.getStats().get("Speed"));
        vitality.setText("Vitality: " + NameController.player.getStats().get("Vitality"));
        fighterclass.setText("Class: " + NameController.player.getFighterClass());
    }
}
