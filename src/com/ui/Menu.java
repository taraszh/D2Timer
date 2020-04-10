package com.ui;

import com.dotahelper.settings.General;
import com.dotahelper.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Menu {

    private static Font font;

    public static void setMenu(JFrame frame) {
        font = new Font("Verdana", Font.PLAIN, 15);

        JMenuBar menuBar = new JMenuBar();

        JMenu settingsMenuItem = new JMenu("Settings");
        settingsMenuItem.setFont(font);
        settingsMenuItem.setBorderPainted(true);

        warningsMenu(settingsMenuItem);
//        autoManaMenu(settingsMenuItem, frame);

        menuBar.add(settingsMenuItem);


        frame.setJMenuBar(menuBar);
    }

    private static void warningsMenu(JMenu settingsMenuItem) {
        JMenu warningsMenu = new JMenu("Warnings");
        warningsMenu.setFont(font);

        JCheckBoxMenuItem menuItemSeconds_20 = new JCheckBoxMenuItem("20 Seconds");
        JCheckBoxMenuItem menuItemSeconds_15 = new JCheckBoxMenuItem("15 Seconds");
        JCheckBoxMenuItem menuItemSeconds_10 = new JCheckBoxMenuItem("10 Seconds");

        ButtonGroup buttonGroupWarning = new ButtonGroup();
        buttonGroupWarning.add(menuItemSeconds_10);
        buttonGroupWarning.add(menuItemSeconds_15);
        buttonGroupWarning.add(menuItemSeconds_20);

        menuItemSeconds_20.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                checkBox(buttonGroupWarning, menuItemSeconds_20, General.ALERT_20_SECONDS);
            }
        });

        menuItemSeconds_15.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBox(buttonGroupWarning, menuItemSeconds_15, General.ALERT_15_SECONDS);
            }
        });

        menuItemSeconds_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBox(buttonGroupWarning, menuItemSeconds_10, General.ALERT_10_SECONDS);
            }
        });

        menuItemSeconds_20.setFont(font);
        menuItemSeconds_15.setFont(font);
        menuItemSeconds_10.setFont(font);

        warningsMenu.add(menuItemSeconds_20);
        warningsMenu.add(menuItemSeconds_15);
        warningsMenu.add(menuItemSeconds_10);

        settingsMenuItem.add(warningsMenu);
    }

    private static void autoManaMenu(JMenu settingsMenuItem, JFrame frame) {
        JMenu autoManaMenu = new JMenu("Auto Arcane Boots");
        autoManaMenu.setFont(font);

        JMenuItem enable = new JMenuItem("on/off");
        enable.setFont(font);
        enable.setSelected(General.getAutoManaSettings());

        enable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                General.setAutoManaSettings(!General.getAutoManaSettings());
            }
        });

        JMenuItem bindAutoManaKey = new JMenuItem("Bind key");
        bindAutoManaKey.setFont(font);

        bindAutoManaKey.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autoManaKeyAction(frame);
            }
        });

        autoManaMenu.add(bindAutoManaKey);
        autoManaMenu.add(enable);
        settingsMenuItem.add(autoManaMenu);
    }


    private static void checkBox(ButtonGroup group, JCheckBoxMenuItem checkBoxMenuItem, int sec) {
        group.clearSelection();
        checkBoxMenuItem.setSelected(true);
        General.setAlertBeforeSpawn(sec);
    }

    private static void autoManaKeyAction(JFrame frame) {
        String key = JOptionPane.showInputDialog(frame, "Enter the key, that will be pressed\n" + "(Example: 'space')", null);

        if (key == null) {
            General.setAutoArcaneBootsKey(0);
            return;
        }

        int converted = Utils.convertToKeyEvent(key);
        if (converted != 0) {
            General.setAutoArcaneBootsKey(converted);
        } else {
            JOptionPane.showMessageDialog(frame, "Please try another key");

            autoManaKeyAction(frame);
        }
    }

}
