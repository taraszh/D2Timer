package com.dotahelper.settings;

public class Power extends General {

    private static String powerRuneRespawnInterval = "power_rune_respawn_interval_seconds";

    public static int getRuneRespawnInterval() {
        return preferences.getInt(powerRuneRespawnInterval, 240);
    }

    /**
     *  todo
     * @param interval
     */
    public static void setPowerRuneRespawnInterval(int interval) {
        preferences.putInt(powerRuneRespawnInterval, interval);
    }

}
