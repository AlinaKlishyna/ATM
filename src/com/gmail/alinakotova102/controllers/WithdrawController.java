package com.gmail.alinakotova102.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.gmail.alinakotova102.database.DatabaseHandler;
import com.gmail.alinakotova102.database.account.Account;
import com.gmail.alinakotova102.utils.ImageUtil;
import com.gmail.alinakotova102.utils.StageUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WithdrawController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField amountField;

    @FXML
    private Button amount_100;

    @FXML
    private Button amount_200;

    @FXML
    private Button amount_50;


    @FXML
    private Button backButton;

    @FXML
    private Button closeApp;

    @FXML
    private ImageView imageBack;

    @FXML
    private Label startLabel;

    @FXML
    private ImageView view_exit;

    @FXML
    private Button withdraw;

    @FXML
    void initialize() {
        setAmount(amount_50, 50);
        setAmount(amount_100, 100);
        setAmount(amount_200, 200);

        moveBack(backButton, "/form/menu.fxml");

        ImageUtil.displayImage("/image/icon_back.png", imageBack);
        ImageUtil.displayImage("/image/icon_exit.png", view_exit);
        withdrawAmount();
    }

    public void withdrawAmount(){
        Account account = new DatabaseHandler().account;
        System.out.println("Current balance: " + account.getBalance());

    }

    public void setAmount(Button button, int amount) {
        button.setOnAction(event -> {
            amountField.setText(String.valueOf(amount));
        });
    }

    public void moveBack(Button button, String path) {
        System.out.println("Withdraw --> Menu");
        button.setOnAction(event -> {
            StageUtil.hideWindow(button);
            StageUtil.openWindow(path);
        });
    }
}