package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BattleClientController {
    @FXML
    TextField host;
    @FXML
    Button connect;

    ArrayBlockingQueue<String> messages = new ArrayBlockingQueue<>(20);

    @FXML
    void initialize() {
        connect.setOnAction(event -> send());
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
        Alert badNum = new Alert(AlertType.ERROR);
        badNum.setContentText(what);
        badNum.show();
    }

    void send() {
        try {
            sendTo(host.getText(), 8880, NameController.player.toString());
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
}