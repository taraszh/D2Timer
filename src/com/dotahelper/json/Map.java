package com.dotahelper.json;

public class Map {

    private static Map map;

    private boolean nightStalkerNight;
    private boolean daytime;
    private boolean gamePaused;

    private int clockTime;
    private int wardPurchaseCooldown;

    private Map() {};

    public static synchronized Map getInstance() {
        if (map == null)
            map = new Map();

        return map;
    }

    public boolean isNightStalkerNight() {
        return nightStalkerNight;
    }

    public void setNightStalkerNight(boolean nightStalkerNight) {
        this.nightStalkerNight = nightStalkerNight;
    }

    public boolean isDaytime() {
        return daytime;
    }

    void setDaytime(boolean daytime) {
        this.daytime = daytime;
    }

    public int getClockTime() {
        return clockTime;
    }

    void setClockTime(int gameTime) {
        this.clockTime = gameTime;
    }

    public int getWardPurchaseCooldown() {
        return wardPurchaseCooldown;
    }

    void setWardPurchaseCooldown(int wardPurchaseCooldown) {
        this.wardPurchaseCooldown = wardPurchaseCooldown;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }

     void setGamePaused(boolean gamePaused) {
        this.gamePaused = gamePaused;
    }
}
