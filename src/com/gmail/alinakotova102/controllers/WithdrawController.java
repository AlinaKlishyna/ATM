package com.gmail.alinakotova102.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

import com.gmail.alinakotova102.database.DatabaseHandler;
import com.gmail.alinakotova102.database.account.Account;
import com.gmail.alinakotova102.service.notify.Notify;
import com.gmail.alinakotova102.utils.ImageUtil;
import com.gmail.alinakotova102.utils.StageUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tray.notification.NotificationType;

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

    Account account = new DatabaseHandler().account;

    @FXML
    void initialize() {
        setField(amount_50, 50);
        setField(amount_100, 100);
        setField(amount_200, 200);

        ImageUtil.displayImage("/image/icon_back.png", imageBack);
        ImageUtil.displayImage("/image/icon_exit.png", view_exit);
        setAmountDB();
    }

    @FXML
    private void clickBack() {
        moveBack(backButton, "/form/menu.fxml");
    }

    public void setAmountDB() {
        withdraw.setOnAction(event -> {
            int amount = Integer.parseInt(amountField.getText());
            BigDecimal difference = account.getBalance().subtract(BigDecimal.valueOf(amount)).
                    setScale(2, RoundingMode.HALF_UP);
            if (!(difference.signum() > 0 || difference.signum() == 0)) {
                System.out.println("Error! On balance not enough funds!");
                Notify notify = new Notify("Error!", "On balance not enough funds!");
                notify.send(NotificationType.ERROR);
            } else {
                account.setBalance(difference);
                moveBack(withdraw, "/form/menu.fxml");
                Notify notify = new Notify("Success! $" + difference + " has been withdrawn.",
                        "The money was successfully withdrawn from the account.");
                notify.send(NotificationType.SUCCESS, new Image("/image/icon_withdrawn.png"));
                System.out.println("Success! Was withdrawn from the account: " + difference +
                        ".\nRemaining on account: " + account.getBalance());
            }
        });
    }

    public void setField(Button button, int amount) {
        button.setOnAction(event -> {
            amountField.setText(String.valueOf(amount));
        });
    }

    public void moveBack(Button button, String path) {
        System.out.println("Withdraw --> Menu");
        StageUtil.hideWindow(button);
        StageUtil.openWindow(path);
    }

    @FXML
    private void clickExit() {
        System.exit(-1);
    }
}