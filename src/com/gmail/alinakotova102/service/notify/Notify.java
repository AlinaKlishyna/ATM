package com.gmail.alinakotova102.service.notify;


import com.gmail.alinakotova102.service.notify.ISender;
import javafx.scene.image.Image;
import javafx.util.Duration;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;

public class Notify implements ISender {
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
    public void send(Notifications nType) {
        TrayNotification tray = new TrayNotification();

        tray.setTitle(title);      // твой заголовок
        tray.setMessage(message);  // твоё сообщение

        // Устанавливаем картинку в зависимости от типа уведомления
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
            case WARNING:
                // если нужно, можно добавить картинку для WARNING
                break;
        }

        tray.setNotification(nType);            // тип уведомления (SUCCESS, ERROR, WARNING, INFORMATION)
        tray.showAndDismiss(Duration.millis(2000));  // показываем на 2 секунды
    }

    @Override
    public void send(Notifications nType, Image image) {
        TrayNotification tray = new TrayNotification();

        tray.setTitle(title);      // твой заголовок
        tray.setMessage(message);  // твоё сообщение

        if (image != null) {
            tray.setImage(image);  // картинка, если есть
        }

        tray.setNotification(nType);  // тип уведомления (SUCCESS, ERROR, WARNING, INFORMATION)
        tray.showAndDismiss(Duration.millis(2000));  // показываем на 2 секунды
    }
}
