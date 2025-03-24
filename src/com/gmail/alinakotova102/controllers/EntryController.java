package com.gmail.alinakotova102.controllers;

import com.gmail.alinakotova102.database.client.Client;
import com.gmail.alinakotova102.database.DatabaseHandler;
import com.gmail.alinakotova102.service.Sound;
import com.gmail.alinakotova102.service.Movement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
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

    @FXML
    void initialize() {
        Movement.printLineByLine("Pin Code", startLabel);

        Button[] numbers = {
                numZero, numOne, numTwo, numThree, numFour, numFive, numSix, numSeven, numEight, numNine};

        buttonAction(numbers);
    }

    public void buttonAction(Button[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            int num = i;
            numbers[i].setOnAction(event -> {
                authSingField.appendText(String.valueOf(num));
                buttonSound.play();
                if (!checkSizeLimit(maxLength, authSingField)) {
                    //метод на проверку пароля и потом только открытие меню
                    entrySystem();
                }
            });
        }
    }

    public void entrySystem() {
        short pincode = Short.parseShort(authSingField.getText().trim());
        loginUser(pincode);
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
            hideWindow(authSingField);
            openWindow("/form/menu.fxml");
        } else {
            System.out.println("ERROR NON CORRECT PASSWORD!");
            authSingField.setText("");
            entrySystem();
        }
    }

    boolean checkSizeLimit(int size, TextField textField) {
        return textField.getCharacters().length() <= size;
    }

    public void openWindow(String path) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void hideWindow(TextField textField) {
        textField.getScene().getWindow().hide();
    }
}