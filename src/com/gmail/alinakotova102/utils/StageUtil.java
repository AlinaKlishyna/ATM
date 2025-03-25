package com.gmail.alinakotova102.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class StageUtil {

    public static void openWindow(String path) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StageUtil.class.getResource(path));
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

    public static void hideWindow(TextField textField) {
        textField.getScene().getWindow().hide();
    }

    public static void hideWindow(Button button) {
        button.getScene().getWindow().hide();
    }
}
