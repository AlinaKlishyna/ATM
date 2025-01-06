package com.gmail.alinakotova102.controllers;

import com.gmail.alinakotova102.service.Sound;
import com.gmail.alinakotova102.service.Movement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

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

    private final byte maxLength = 4;

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
                if (checkSizeLimit(maxLength)) {
                    authSingField.appendText(String.valueOf(num));
                    Movement.shake(authSingField);
                    buttonSound.play();
                }
            });
        }
    }

    boolean checkSizeLimit(int size) {
        return authSingField.getCharacters().length() < size;
    }
}