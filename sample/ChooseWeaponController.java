package sample;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

public class ChooseWeaponController {

    @FXML
    Label whichWeapon;

    @FXML
    Label confirmText;

    @FXML
    Button proceed;

    @FXML
    Label axe;

    @FXML
    ImageView axeView;

    @FXML
    Label lance;

    @FXML
    ImageView lanceView;

    @FXML
    Label sword;

    @FXML
    ImageView swordView;


    Image axeIcon = new Image(getClass().getResourceAsStream("axe.jpg"));
    Image swordIcon = new Image(getClass().getResourceAsStream("sword.png"));
    Image lanceIcon = new Image(getClass().getResourceAsStream("lance.jpg"));

    @FXML
    void axe() {
        ChooseOpponentController.battle.player.setWeapon(Weapon.axe);
        confirmChoice();
    }

    @FXML
    void lance() {
        ChooseOpponentController.battle.player.setWeapon(Weapon.lance);
        confirmChoice();
    }

    @FXML
    void sword() {
        ChooseOpponentController.battle.player.setWeapon(Weapon.sword);
        confirmChoice();
    }

    void confirmChoice() {
        confirmText.setVisible(false);
        if (ChooseOpponentController.battle.player.getWeapon() == Weapon.axe) {
            confirmText.setText("Proceed using an " + ChooseOpponentController.battle.player.getWeapon() + "?");
        } else {
            confirmText.setText("Proceed using a " + ChooseOpponentController.battle.player.getWeapon() + "?");
        }
        confirmText.setLayoutX((450 - confirmText.getWidth()) / 2);
        confirmText.setVisible(true);
        proceed.setDisable(false);
        proceed.setVisible(true);

        proceed.setOnAction(event -> {
            if (ChooseOpponentController.isHumanOpponent) {
                sendTo(ChooseOpponentController.opponentIP, 8880, ChooseOpponentController.battle.player.toString());
                try {
                    HomeScreenController.myserver.listen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ChooseOpponentController.battle.enemy = HomeScreenController.myserver.receivedPlayer;
                System.out.println("In chooseWeaponController. Battle enemy: " + ChooseOpponentController.battle.enemy);
            }
            startRound();
        });
    }

    @FXML
    void startRound() {
        proceed.getScene().getWindow().hide();
        ChooseOpponentController.battle.fight();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("RoundResults.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            RoundResultsController second = (RoundResultsController) loader.getController();

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
        //battle = new Battle(NameController.player, null);
        whichWeapon.setText(NameController.player.getName() + ", which weapon will you use?");
        this.axeView.setImage(axeIcon);
        this.lanceView.setImage(lanceIcon);
        this.swordView.setImage(swordIcon);
        this.axeView.setOnMouseClicked(event -> axe());
        this.lanceView.setOnMouseClicked(event -> lance());
        this.swordView.setOnMouseClicked(event -> sword());
        this.confirmText.setVisible(false);
        proceed.setOnAction(event -> send());
    }

    void badNews(String what) {
        Alert badNum = new Alert(Alert.AlertType.ERROR);
        badNum.setContentText(what);
        badNum.show();
    }

    void send() {
        try {
            sendTo(NameController.opponentIP, 8880, NameController.player.toString());
        } catch (NumberFormatException nfe) {
            badNews("Incorrect port number");
        }
    }

    void sendTo(String host, int port, String message) {
        new Thread(() -> {
            try {
                Socket target = new Socket(host, port);
                send(target, message);
//                opponent = receive(target);
                target.close();
            } catch (Exception e) {
                Platform.runLater(() -> badNews(e.getMessage()));
                e.printStackTrace();
            }
        }).start();
    }

    void send(Socket target, String message) throws IOException {
        PrintWriter sockout = new PrintWriter(target.getOutputStream());
        sockout.println(message);
        sockout.flush();
    }

    Fighter receive(Socket target) throws IOException {
        Fighter fighter = null;
        BufferedReader sockin = new BufferedReader(new InputStreamReader(target.getInputStream()));
        while (!sockin.ready()) {
        }
        while (sockin.ready()) {
            try {
                String msg = sockin.readLine();
                System.out.println(msg);
                fighter = Parser.parseFighter(msg);
                System.out.println(fighter.getName());
            } catch (Exception e) {
                Platform.runLater(() -> badNews(e.getMessage()));
                e.printStackTrace();
            }
        }
        return fighter;
    }
    //IPv4 Address: 10.253.196.108

}

	


