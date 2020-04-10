package com.dotahelper.watcher;

import com.dotahelper.json.Map;
import com.dotahelper.json.Mapper;
import com.dotahelper.settings.General;
import com.ui.AudioController;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
import java.util.stream.IntStream;

public class EntityWatcher extends WatcherAbstract {

    protected int spawnInterval;
    protected int spawnTime = 0;
    protected int spawnAlertTime = 0;

    private static boolean mute = false;
    public static int lastClockTime;

    private String audioFile;

    public EntityWatcher(int interval, String audioFile, JLabel label, String labelName) {
        spawnInterval = interval;
        this.audioFile = audioFile;
        this.label = label;
        this.label.setName(labelName);
    }

    public void watch() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        int clockTime = Map.getInstance().getClockTime();

        if (!Mapper.isMatchStarted) {
            resetSpawnTimestamps();
            return;
        }

        if (spawnTime == 0) {
            setSpawnTime(clockTime);
            spawnAlertTime = spawnTime - General.getAlertBeforeSpawn();
        }

        if (IntStream.of(clockTime - 1, clockTime, clockTime + 1).anyMatch(x -> x == spawnAlertTime)) {
            spawnAlertTime += spawnInterval;
            if (mute) {
                mute = clockTime < lastClockTime + 5;
            } else {
                AudioController audioController = new AudioController(audioFile);
                audioController.play();
                mute = true;
                lastClockTime = clockTime;
            }
        }

        if (IntStream.of(clockTime - 1, clockTime, clockTime + 1).anyMatch(x -> x == spawnTime))
            spawnTime += spawnInterval;
    }

    private void resetSpawnTimestamps() {
        spawnTime = 0;
        spawnAlertTime = 0;
    }

    private void setSpawnTime(int clockTime) {
        if (clockTime < spawnInterval) spawnTime = spawnInterval;
        else spawnTime = (clockTime / spawnInterval) * spawnInterval + spawnInterval;
    }

    public int getTimer() {
        if (spawnTime == 0) return 0;

        return spawnTime - Map.getInstance().getClockTime();
    }

}

