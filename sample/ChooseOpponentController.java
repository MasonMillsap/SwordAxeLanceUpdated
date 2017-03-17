package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.lang.model.element.Name;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Mason Millsap on 3/1/2017.
 */
public class ChooseOpponentController {
    static Battle battle;
    static String opponentIP;
    static boolean isHumanOpponent = false;
    ArrayBlockingQueue<String> messages = new ArrayBlockingQueue<>(20);
    @FXML
    Pane enterIPWindow;

    @FXML
    TextField destinationIP;

    @FXML
    Button acceptIP;

    @FXML
    Button computerOpponent;

    @FXML
    Button humanOpponent;

    void initialize() {
        new Thread(() -> {
            for (;;) {
                try {
                    String msg = messages.take();
                    Platform.runLater(() -> {System.out.println(msg);});
                } catch (Exception e) {
                    badNews(e.getMessage());
                }
            }
        }).start();
    }
    void badNews(String what) {
        Alert badNum = new Alert(Alert.AlertType.ERROR);
        badNum.setContentText(what);
        badNum.show();
    }

    void send() {
        try {
            sendTo(opponentIP, 8880, NameController.player.toString());
        } catch (NumberFormatException nfe) {
            badNews("Invalid IP address");
        }
    }

    void sendTo(String host, int port, String message) {
        new Thread(() -> {
            try {
                Socket target = new Socket(host, port);
                send(target, message);
                receive(target);
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

    void receive(Socket target) throws IOException {
        BufferedReader sockin = new BufferedReader(new InputStreamReader(target.getInputStream()));
        while (!sockin.ready()) {}
        while (sockin.ready()) {
            try {
                String msg = sockin.readLine();
                messages.put(msg);
            } catch (Exception e) {
                Platform.runLater(() -> badNews(e.getMessage()));
                e.printStackTrace();
            }
        }
    }

    @FXML
    void computerOpponent() {
        isHumanOpponent = false;
        loadFight();
        battle = new Battle(NameController.player, null);
    }
    @FXML
    void humanOpponent() {
        isHumanOpponent = true;
        enterIPWindow.setVisible(true);
        computerOpponent.setVisible(false);
        computerOpponent.setDisable(true);
        humanOpponent.setVisible(false);
        humanOpponent.setDisable(true);
        acceptIP.setOnAction(event -> {
            opponentIP = destinationIP.getText();
            //10.253.199.75
            sendTo(opponentIP, 8880, NameController.player.toString());
            try {
                HomeScreenController.myserver.listen();
            } catch (IOException e) {
                e.printStackTrace();
            }
                    loadFight();
            });
        battle = new Battle(NameController.player, HomeScreenController.myserver.receivedPlayer);
    }

    void loadFight() {
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
}
