package com.gmail.alinakotova102;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

import java.io.File;

public class Sound {

    private String nameFile;
    private MediaPlayer mediaPlayer;

    public Sound(String nameFile) {
        this.nameFile = nameFile;
    }

    public void chooseFile() {
        File file = new File(nameFile);
        if (file.isFile()) {
            Media media = new Media(new File(nameFile).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
        }
    }

    public void play() {
        chooseFile();
        mediaPlayer.play();
    }

    public void stop() {
        chooseFile();
        mediaPlayer.stop();
    }
}
