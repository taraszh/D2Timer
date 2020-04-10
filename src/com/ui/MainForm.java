package com.ui;

import com.dotahelper.http.Server;
import com.dotahelper.global.Audio;
import com.dotahelper.json.Mapper;
import com.dotahelper.settings.*;
import com.dotahelper.utils.Utils;
import com.dotahelper.watcher.EntityWatcher;
import com.dotahelper.clicker.AutoArcaneBoots;
import com.dotahelper.watcher.WardWatcher;
import com.dotahelper.watcher.Watcher;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * todo logger
 */
public class MainForm {
    private JPanel mainPanel;
    private JPanel rootPanel;
    private JButton buttonExit;

    private JLabel bountyTimer;
    private JLabel powerTimer;
    private JLabel bookTimer;
    private JLabel campTimer;
    private JLabel wardTimer;

    private JLabel autoManaLabel;
    private JLabel arcaneLabel;
    private AutoArcaneBoots autoArcaneBoots;

    public static final String bounty = "bounty";
    public static final String power = "power";
    public static final String book = "book";
    public static final String ward = "ward";
    public static final String camp = "camp";

    private Watcher[] watchers;

    public static void main(String[] args) throws Exception {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MainForm();
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "Class" + e.getClass());
                }
            }
        });
    }

    public MainForm() throws Exception {
        new CreateGSI();
        new Server(4000);

        this.watchers = new Watcher[] {
                new EntityWatcher(Book.getBookRestockInterval(), Audio.BOOK.getSetting(), bookTimer, book),
                new EntityWatcher(Bounty.getRuneRespawnInterval(), Audio.BOUNTY.getSetting(), bountyTimer, bounty),
                new EntityWatcher(Power.getRuneRespawnInterval(), Audio.POWER.getSetting(), powerTimer, power),
                new EntityWatcher(Camp.getCampRespawnInterval(), Audio.CAMP.getSetting(), campTimer, camp),
                new WardWatcher(Audio.WARD.getSetting(), wardTimer, ward)
        };

        /**
         * disabled
         * autoArcaneBoots = new AutoArcaneBoots();
         */

        draw();
        dynamicLabels();
    }

    private void draw() {
        JFrame frame = new JFrame("dota2 timers");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setContentPane(mainPanel);

        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        Menu.setMenu(frame);
    }

    private void dynamicLabels() {
        Thread timers = new Thread(() -> {
            while (true) {
                try {
                    watch();
                    click();

                    Thread.sleep(700);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        timers.start();
    }

    private void watch() throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        for (Watcher watcher : this.watchers) {
            int timer = watcher.getTimer();
            JLabel label = watcher.getLabel();

            if (timer <= General.getAlertBeforeSpawn() && Mapper.isMatchStarted) {
                label.setForeground(new Color(128, 0, 0));
                Notification.notify(watcher);
            } else {
                label.setForeground(new Color(234, 242, 255));
                if (watcher.getWindow() != null) watcher.getWindow().dispose();
            }

            label.setText(Utils.formatTimer(timer));

            watcher.watch();
        }
    }

    private void click() {
        //auto arcane boots disabled
        arcaneLabel.setVisible(false);
        autoManaLabel.setVisible(false);
//        autoManaLabel.setText(
//            General.getAutoManaSettings()
//                    ? "ON" + (General.getAutoArcaneBootsKey() == 0 ? " (key not bind)" : "")
//                    : "OFF"
//            );
//
//        autoArcaneBoots.click();
    }

}
