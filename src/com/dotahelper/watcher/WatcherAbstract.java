package com.dotahelper.watcher;

import javax.swing.*;

abstract class WatcherAbstract implements Watcher {

    protected JLabel label;
    protected JWindow window;

    public JLabel getLabel() {
        return label;
    }

    @Override
    public void setWindow(JWindow jWindow) {
        this.window = jWindow;
    }

    @Override
    public JWindow getWindow() {
        return window;
    }

}

