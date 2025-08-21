package com.gmail.alinakotova102.service.notify;

import com.github.plushaze.traynotification.notification.Notifications;
import javafx.scene.image.Image;

import java.awt.*;

public interface ISender {
    void send(Notifications nType);

    void send(Notifications nType, Image image);
}
