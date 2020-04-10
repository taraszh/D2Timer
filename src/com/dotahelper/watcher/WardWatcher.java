package com.dotahelper.watcher;

import com.dotahelper.json.Map;
import com.dotahelper.json.Mapper;
import com.dotahelper.settings.General;
import com.ui.AudioController;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class WardWatcher extends WatcherAbstract {

    private String audioFile;

    public WardWatcher(String audioFile, JLabel label, String labelName) {
        this.audioFile = audioFile;
        this.label = label;
        this.label.setName(labelName);
    }

    public void watch() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (Map.getInstance().getWardPurchaseCooldown() == General.getAlertBeforeSpawn()) {
            AudioController audioController = new AudioController(audioFile);
            audioController.play();
        }
    }

    @Override
    public int getTimer() {
            return Map.getInstance().getWardPurchaseCooldown();
    }

}

