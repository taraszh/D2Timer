package com.ui;

import com.dotahelper.utils.Utils;
import com.dotahelper.watcher.Watcher;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import javax.swing.*;
import java.awt.*;

class Notification {

    private static Font font = new Font("Verdana", Font.PLAIN, 15);

    public static void notify(Watcher watcher) {
        if (Utils.getForegroundWindowName().equals("dota 2")) {
            switch (watcher.getLabel().getName()) {
            case MainForm.bounty:
                drawNotification(Color.orange, watcher, -30);
                break;
            case MainForm.power:
                drawNotification(Color.CYAN, watcher, -100);
                break;
            case MainForm.camp:
                drawNotification(Color.GREEN, watcher, 90);
                break;
            case MainForm.book:
                drawNotification(Color.YELLOW, watcher, 30);
                break;
            case MainForm.ward:
                drawNotification(Color.PINK, watcher, 150);
                break;
            default:
                System.out.println("Notifications broken");
            }
        } else if (watcher.getWindow() != null) {
            watcher.getWindow().dispose();
        }
    }

    private static void drawNotification(Color color, Watcher watcher, int decreaseX) {
        if (watcher.getWindow() == null) {
            WinDef.RECT rectangle = new WinDef.RECT();

            boolean found = User32.INSTANCE.GetWindowRect(Utils.getForegroundWindow(), rectangle);
            if (!found) {
                System.out.println("Cannot get dota window rectangle");
                return;
            }
            int width = rectangle.right - rectangle.left;

            int x = width / 2 - decreaseX;
            int y = rectangle.top + 56;

                JWindow window = new JWindow() {
                    @Override
                    public void paint(Graphics g) {
                        g.setFont(font);
                        g.setColor(color);
                        g.drawString(watcher.getLabel().getName(), x, y);
                    }

                    @Override
                    public void update(Graphics g) {
                        paint(g);
                    }
                };
                watcher.setWindow(window);
            }

            Window window = watcher.getWindow();
            window.setAlwaysOnTop(true);
            window.setBounds(window.getGraphicsConfiguration().getBounds());
            window.setBackground(new Color(0, true));
            window.setVisible(true);
    }

}
