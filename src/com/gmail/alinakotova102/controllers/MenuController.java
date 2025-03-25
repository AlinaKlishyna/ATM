package com.gmail.alinakotova102.controllers;

import com.gmail.alinakotova102.database.DatabaseHandler;
import com.gmail.alinakotova102.database.account.Account;
import com.gmail.alinakotova102.utils.StageUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.math.BigDecimal;

public class MenuController {

    @FXML
    private Label balanceLabel;

    @FXML
    private Button closeApp;

    @FXML
    private Button depositAmount;

    @FXML
    private Button displayPassword;

    @FXML
    private ImageView viewEye;

    @FXML
    private ImageView view_exit;

    @FXML
    private Label userLabel;

    @FXML
    private Label userLabel11;

    @FXML
    private Button withdrawAmount;

    BigDecimal balance = Account.getBalance();

    @FXML
    void initialize() {
        Image iconEyeClose = new Image(getClass().getResourceAsStream("/image/eye_close.png"));
        Image iconEyeOpen = new Image(getClass().getResourceAsStream("/image/eye_open.png"));
        Image iconExit = new Image(getClass().getResourceAsStream("/image/icon_exit.png"));

        balanceLabel.setText(String.valueOf(balance));

        view_exit.setImage(iconExit);

        viewEye.setImage(iconEyeOpen);
        hideBalance(balanceLabel);

        displayPassword.setOnAction(actionEvent -> {
            if (viewEye.getImage() == iconEyeClose) {
                viewEye.setImage(iconEyeOpen);
                hideBalance(balanceLabel);
            } else {
                viewEye.setImage(iconEyeClose);
                viewBalance(balanceLabel);
            }
        });
        userLabel.setText("Welcome, " + viewFirstLastName() + "!");

        openWithdraw();
    }
    public void openWithdraw() {
        withdrawAmount.setOnAction(event -> {
            StageUtil.hideWindow(withdrawAmount);
            StageUtil.openWindow("/form/withdraw.fxml");
        });
    }

    private String viewFirstLastName() {
        return DatabaseHandler.client.toString();
    }

    void hideBalance(Label label) {
        String textHide = "";
        Byte countNumbersBalance = (byte) String.valueOf(balance).length();
        for (int i = 0; i < countNumbersBalance; i++) {
            textHide += "*";
        }
        label.setText(textHide);
    }

    void viewBalance(Label label) {
        label.setText(String.valueOf(balance));
    }
}
