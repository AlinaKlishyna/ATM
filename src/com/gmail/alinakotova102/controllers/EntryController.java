package com.gmail.alinakotova102.controllers;

import com.gmail.alinakotova102.database.client.Client;
import com.gmail.alinakotova102.database.DatabaseHandler;
import com.gmail.alinakotova102.service.Notify;
import com.gmail.alinakotova102.service.Sound;
import com.gmail.alinakotova102.service.Movement;
import com.gmail.alinakotova102.utils.StageUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import tray.notification.NotificationType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntryController {

    @FXML
    private TextField authSingField;

    @FXML
    private Button numOne;

    @FXML
    private Button numTwo;

    @FXML
    private Button numThree;

    @FXML
    private Button numFour;

    @FXML
    private Button numFive;

    @FXML
    private Button numSix;

    @FXML
    private Button numSeven;

    @FXML
    private Button numEight;

    @FXML
    private Button numNine;

    @FXML
    private Button numZero;

    @FXML
    private Label startLabel;

    private final byte maxLength = 3;

    private Sound buttonSound = new Sound(Sound.BUTTON);
    private int attempt = 1;

    @FXML
    void initialize() {
        Movement.printLineByLine("Pin Code", startLabel);

        Button[] numbers = {
                numZero, numOne, numTwo, numThree, numFour, numFive, numSix, numSeven, numEight, numNine};

        buttonAction(numbers);
    }

    @FXML
    private void clickErase() {
        if (!authSingField.getText().isEmpty())
        authSingField.setText(authSingField.getText().substring(0, authSingField.getText().length()-1));
    }

    @FXML
    private void clickCancel() {
        authSingField.setText("");
    }

    public void buttonAction(Button[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            int num = i;
            numbers[i].setOnAction(event -> {
                authSingField.appendText(String.valueOf(num));
                buttonSound.play();
                if (!checkSizeLimit(maxLength, authSingField)) {
                    entrySystem();
                }
            });
        }
    }

    public void entrySystem() {
        if (!authSingField.getText().isEmpty()) {
            if (attempt != 3) {
                short pincode = Short.parseShort(authSingField.getText().trim());
                loginUser(pincode);
            } else {
                System.exit(-1);
            }
        }
    }

    private void loginUser(short pincode) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Client client = new Client();
        ResultSet resultSet = dbHandler.findClientDB(client, pincode);

        int count = 0;
        try {
            while (resultSet.next()) {
                count++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (count >= 1) {
            System.out.println("Success! Entered to system");
            Notify notify = new Notify("Success!", "You have successfully logged in!");
            notify.send(NotificationType.SUCCESS);
            StageUtil.hideWindow(authSingField);
            StageUtil.openWindow("/form/menu.fxml");
        } else {
            System.out.println("ERROR NON CORRECT PASSWORD!");
            Notify notify = new Notify("Error! Attempts used 3/" + attempt, "Login failed! Please try again.");
            notify.send(NotificationType.ERROR);
            authSingField.setText("");
            attempt++;
            System.out.println(attempt);
            entrySystem();
        }
    }

    boolean checkSizeLimit(int size, TextField textField) {
        return textField.getCharacters().length() <= size;
    }
}