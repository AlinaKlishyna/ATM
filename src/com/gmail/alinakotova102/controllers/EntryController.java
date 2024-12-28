package com.gmail.alinakotova102.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    private final byte maxLength = 4;

    @FXML
    void initialize() {
        Button[] numbers = {
                numZero, numOne, numTwo, numThree, numFour, numFive, numSix, numSeven, numEight, numNine};

        for (int i = 0; i < numbers.length; i++) {
            int num = i;
            numbers[i].setOnAction(event -> {
                if (checkTextLimiter())
                    authSingField.appendText(String.valueOf(num));
            });
        }
    }

    boolean checkTextLimiter() {
        return authSingField.getCharacters().length() < maxLength;
    }

    @FXML
    void submit(ActionEvent event) {

    }
}