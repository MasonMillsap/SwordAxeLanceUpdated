package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RoundResultsController {
	
	@FXML
	Label roundNumber;
	
	@FXML
	Label chosenWeaponP1;
	
	@FXML
	Label chosenWeaponP2;
	
	@FXML
	Label P1dmgtaken;
	
	@FXML
	Label P2dmgtaken;
	
	@FXML
	Label P1HP;
	
	@FXML
	Label P2HP;

	@FXML
	Button nextRound;

	@FXML
	Label gameOver;
	
	@FXML
	void nextRound() {
		roundNumber.getScene().getWindow().hide();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ChooseWeapon.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			ChooseWeaponController second = (ChooseWeaponController)loader.getController();

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);
			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	@FXML
	void initialize(){
		roundNumber.setText("ROUND 1");
		chosenWeaponP1.setText(NameController.player.getName() + " attacks with a " + String.valueOf(NameController.player.getWeapon()));
		chosenWeaponP2.setText(ChooseOpponentController.battle.enemy.getName() + " attacks with a " + String.valueOf(ChooseOpponentController.battle.enemy.getWeapon()));
		System.out.println("Enemy attacks with a " + String.valueOf(ChooseOpponentController.battle.enemy.getWeapon()));
		P1dmgtaken.setText("You took " + Integer.toString(ChooseOpponentController.battle.playerDmgTaken) + " damage!");
		P2dmgtaken.setText(ChooseOpponentController.battle.enemy.getName() + " took " + Integer.toString(ChooseOpponentController.battle.enemyDmgTaken) + " damage!");
		P1HP.setText("You now have " + Integer.toString(NameController.player.getHP()) + " HP!");
		P2HP.setText(ChooseOpponentController.battle.enemy.getName() + " now has " + Integer.toString(ChooseOpponentController.battle.enemy.getHP()) + " HP!");
		if (Battle.isOver) {
			gameOver.setText(Battle.winner.getName() + " won the battle!");
			gameOver.setVisible(true);
			nextRound.setText("Proceed");
			nextRound.setOnAction(event ->
					{loadChooseFight();
		});
			System.out.println("Game OVER");
		}
	}
	void loadChooseFight() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ChooseOpponent.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			ChooseOpponentController second = (ChooseOpponentController)loader.getController();

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);
			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
