package com.dotahelper.settings;

public class Bounty extends General {

    private static String bountyRuneRespawnInterval = "bounty_rune_respawn_interval_seconds";

    public static int getRuneRespawnInterval() {
        return preferences.getInt(bountyRuneRespawnInterval, 300);
    }

    /**
     * todo
     * @param interval
     */
    public static void setRuneRespawnInterval(int interval) {
        preferences.putInt(bountyRuneRespawnInterval, interval);
    }

}
