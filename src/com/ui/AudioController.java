package com.ui;

import com.dotahelper.global.Audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class AudioController {

    AudioInputStream audioInputStream;
    Clip clip;

    public AudioController(String audioFile)
            throws IOException,
                   UnsupportedAudioFileException,
                   LineUnavailableException
    {
        URL controller = AudioController.class.getResource(Audio.DIR.getSetting());

        if (!"file".equalsIgnoreCase(controller.getProtocol())) {
            throw new IllegalStateException("AudioController class is not stored in a file.");
        }

        audioInputStream = AudioSystem.getAudioInputStream(new File(controller.getPath() + "/" + audioFile));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        FloatControl vc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        vc.setValue(1);
    }

    public void play() {
        try {
            Thread audioThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    clip.start();

                    while (!clip.isRunning()) try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (clip.isRunning()) try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clip.close();
                }
            });
            audioThread.start();
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
    }

    //for testing
//    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
//        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/com/ui/audio/bounty.wav"));
//        Clip clip = AudioSystem.getClip();
//        clip.open(audioInputStream);
//        FloatControl vc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//        vc.setValue(1);
//
//        Thread audioThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                clip.start();
//
//                while (!clip.isRunning())
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                while (clip.isRunning())
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                clip.close();
//            }
//        });
//        audioThread.start();
//    }

}
