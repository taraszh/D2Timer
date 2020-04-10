package com.dotahelper.watcher;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public interface Watcher {

    void watch() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException;

    int getTimer() throws InterruptedException;

    JLabel getLabel();

    void setWindow(JWindow jWindow);

    JWindow getWindow();
}
