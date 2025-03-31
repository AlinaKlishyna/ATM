package com.gmail.alinakotova102.service;

import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Notify implements ISender{
    private String title;
    private String message;

    public Notify(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void send(NotificationType nType) {
        TrayNotification tray = new TrayNotification();
        tray.setAnimationType(AnimationType.POPUP);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(nType);
        tray.showAndDismiss(Duration.millis(2000));
        switch (nType) {
            case SUCCESS:
                tray.setImage(new Image("/image/icon_correct.png"));
                break;
            case ERROR:
                tray.setImage(new Image("/image/icon_incorrect.png"));
                break;
            case INFORMATION:
                tray.setImage(new Image("/image/icon_unload.png"));
                break;
        }
    }
}
