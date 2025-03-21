package com.gmail.alinakotova102.service;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class Movement {
    private static Sound keyboardSound = new Sound(Sound.KEYBOARD);

    public static void shake(TextField textField) {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
            int i = 0;

            @Override
            public void handle(ActionEvent actionEvent) {
                if (i <= 5) {
                    if (textField.getRotate() > 0) {
                        textField.setRotate(-2);
                    } else {
                        textField.setRotate(2);
                    }
                    i++;
                } else {
                    textField.setRotate(0);
                    timeline.stop();
                }
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
        timeline.play();
    }

    public static void printLineByLine(String text, Label output) {
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
}
