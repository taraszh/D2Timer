package com.dotahelper.settings;

public class Camp extends General {

    private static String campRespawnInterval = "camp_respawn_interval_in_seconds";

    public static int getCampRespawnInterval() {
        return preferences.getInt(campRespawnInterval, 60);
    }

    /**
     * todo
     * @param interval
     */
    public static void setCampRespawnInterval(int interval) {
        preferences.putInt(campRespawnInterval, interval);
    }

}
