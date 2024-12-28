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

    private Integer pinCode;

    private final byte maxLength = 4;

    public Integer getPinCode() {
        return pinCode;
    }

    @FXML
    void initialize() {
        numOne.setOnAction(event -> {
            if (checkTextLimiter()) {
                authSingField.appendText("1");
            }
        });
        numTwo.setOnAction(event -> {
            if (checkTextLimiter()) {
                authSingField.appendText("2");
            }
        });
        numThree.setOnAction(event -> {
            if (checkTextLimiter()) {
                authSingField.appendText("3");
            }
        });
        numFour.setOnAction(event -> {
            if (checkTextLimiter()) {
                authSingField.appendText("4");
            }
        });
        numFive.setOnAction(event -> {
            if (checkTextLimiter()) {
                authSingField.appendText("5");
            }
        });
        numSix.setOnAction(event -> {
            if (checkTextLimiter()) {
                authSingField.appendText("6");
            }
        });
        numSeven.setOnAction(event -> {
            if (checkTextLimiter()) {
                authSingField.appendText("7");
            }
        });
        numEight.setOnAction(event -> {
            if (checkTextLimiter()) {
                authSingField.appendText("8");
            }
        });
        numNine.setOnAction(event -> {
            if (checkTextLimiter()) {
                authSingField.appendText("9");
            }
        });
        numZero.setOnAction(event -> {
            if (checkTextLimiter()) {
                authSingField.appendText("0");
            }
        });
    }

    boolean checkTextLimiter() {
        return authSingField.getCharacters().length() < maxLength;
    }
}