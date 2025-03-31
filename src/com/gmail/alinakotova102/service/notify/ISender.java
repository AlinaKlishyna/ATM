package com.gmail.alinakotova102.service.notify;

import javafx.scene.image.Image;
import tray.notification.NotificationType;

import java.awt.*;

public interface ISender {
    void send(NotificationType nType);
    void send(NotificationType nType, Image image);
}
