package com.gmail.alinakotova102.controllers;

import com.gmail.alinakotova102.Sound;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.util.Duration;

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
    private Sound keyboardSound = new Sound("src/main/resources/sound/keyboard_sound.mp3");
    private Sound buttonSound = new Sound("src/main/resources/sound/button_sound.mp3");

    @FXML
    void initialize() {
        printLineByLine("Pin Code", startLabel);

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
                    buttonSound.play();
                }
            });
        }
    }

    public void printLineByLine(String text, Label output) {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(300), new EventHandler<ActionEvent>() {
            int i = 0;

            @Override
            public void handle(ActionEvent actionEvent) {
                if (i < text.length()) {
                    keyboardSound.play();
                    output.setText(output.getText() + text.charAt(i));
                    keyboardSound.stop();
                    i++;
                } else {
                    timeline.stop();
                }
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    boolean checkSizeLimit(int size) {
        return authSingField.getCharacters().length() < size;
    }
}