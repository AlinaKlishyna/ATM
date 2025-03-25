package com.gmail.alinakotova102.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageUtil {

    public static void displayImage(String pathImage, ImageView imageView) {
        Image image = new Image(ImageUtil.class.getResourceAsStream(pathImage));
        imageView.setImage(image);
    }
}
