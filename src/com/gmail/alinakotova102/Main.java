package com.gmail.alinakotova102;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/form/pin-code.fxml"));
        stage.setTitle("ATM --> Start");
        stage.setScene(new Scene(root));
        stage.show();
    }
}